package com.xuzhongjian.code.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class KafkaUtils {
    private static final KafkaConsumer<String, String> CONSUMER;
    private static Queue<ConsumerRecord<String, String>> queue;

    static {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "yax-dt2-kafka0-1.exodushk.com:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        CONSUMER = new KafkaConsumer<>(props);
    }

    private static void getLastNMessages(String topic, int n) {
        // 优先队列，按照timestamp升序排列，构建一个小顶堆
        queue = new PriorityQueue<>((o1, o2) -> (int) (o1.timestamp() - o2.timestamp()));

        // 获取topic的所有partitions
        List<TopicPartition> partitions = CONSUMER.partitionsFor(topic).stream()
                .map(o -> new TopicPartition(topic, o.partition())).collect(Collectors.toList());
        CONSUMER.assign(partitions);

        // 获取每个partition的最新offset，并从该offset开始拉取消息
        for (TopicPartition partition : partitions) {
            CONSUMER.seekToEnd(Collections.singletonList(partition));
            long endOffset = CONSUMER.position(partition);
            long startOffset = Math.max(0, endOffset - n);
            CONSUMER.seek(partition, startOffset);
            ConsumerRecords<String, String> records = CONSUMER.poll(Duration.ofMillis(100));
            log.info("Partition: {}, StartOffset: {}, EndOffset: {}, Records: {}", partition.partition(), startOffset, endOffset, records.count());
            records.forEach(record -> KafkaUtils.addToQueue(n, record));
        }
    }

    private static void addToQueue(int n, ConsumerRecord<String, String> record) {
        queue.add(record);
        while (queue.size() > n) {
            queue.poll();
        }
    }

    /**
     * 打印 topic 的最新 n 条消息
     *
     * @param topic topic名称
     * @param n     最新消息数
     */
    private static void printTopicMessage(String topic, int n) {
        KafkaUtils.getLastNMessages(topic, n);

        while (!queue.isEmpty()) {
            ConsumerRecord<String, String> message = queue.poll();
            log.info("[datetime:{}] partition:{}, offset:{}, message: {}",
                    TimeUtils.datetime(message.timestamp()), message.partition(), message.offset(), message.value());
        }
    }

    public static void main(String[] args) {
        KafkaUtils.printTopicMessage("dormantAccount_e", 20);
    }
}