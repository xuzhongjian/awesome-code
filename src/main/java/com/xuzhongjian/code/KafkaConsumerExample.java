package com.xuzhongjian.code;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class KafkaConsumerExample {
    public static void main(String[] args) {
        // Kafka 集群连接信息
        String bootstrapServers = "yax-dt2-kafka0-1.exodushk.com:9092";
        String topic = "dormantAccount_e";

        // 创建 Kafka 消费者配置
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 创建 Kafka 消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅 topic
        consumer.subscribe(Collections.singletonList(topic));

        // 获取最近的 10 个消息
        int maxMessages = 10;
        int messageCount = 0;
        while (messageCount < maxMessages) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Topic: " + record.topic() + ", Partition: " + record.partition() + ", Offset: " + record.offset() + ", Key: " + record.key() + ", Value: " + record.value());
                messageCount++;
                if (messageCount >= maxMessages) {
                    break;
                }
            }
        }
    }
}
