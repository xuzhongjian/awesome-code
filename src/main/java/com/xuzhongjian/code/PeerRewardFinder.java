package com.xuzhongjian.code;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PeerRewardFinder {

    public static void main(String[] args) {
        String peerIdsFile = "peer_ids.txt";
        String postRewardsFile = "post-1.4.18.json";
        String preRewardsFile = "pre-1.4.18.json";
        try {
            System.out.println("peerId,preRewards,postRewards");
            List<String> peerIds = readPeerIdsFromFile(peerIdsFile);
            Map<String, String> postRewardsMap = readRewardsFromJson(postRewardsFile);
            Map<String, String> preRewardsMap = readRewardsFromJson(preRewardsFile);
            for (String peerId : peerIds) {
                String preRewards = "0";
                String postRewards = "0";
                if (preRewardsMap.containsKey(peerId)) {
                    preRewards = preRewardsMap.get(peerId);
                }
                if (postRewardsMap.containsKey(peerId)) {
                    postRewards = postRewardsMap.get(peerId);
                }
                System.out.println(peerId + "," + preRewards + "," + postRewards);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<String> readPeerIdsFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(PeerRewardFinder.class.getClassLoader().getResourceAsStream(filename), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    private static Map<String, String> readRewardsFromJson(String filename) throws IOException {
        Map<String, String> rewardsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(PeerRewardFinder.class.getClassLoader().getResourceAsStream(filename), StandardCharsets.UTF_8))) {
            String content = reader.lines().collect(Collectors.joining());
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String peerId = jsonObject.getString("peerId");
                String reward = jsonObject.getString("reward");
                rewardsMap.put(peerId, reward);
            }
        }

        return rewardsMap;
    }

    private static Map<String, String> readDisqualifiedFromJson(String filename) throws IOException {
        Map<String, String> rewardsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(PeerRewardFinder.class.getClassLoader().getResourceAsStream(filename), StandardCharsets.UTF_8))) {
            String content = reader.lines().collect(Collectors.joining());
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String peerId = jsonObject.getString("peerId");
                String reward = jsonObject.getString("criteria");
                rewardsMap.put(peerId, reward);
            }
        }

        return rewardsMap;
    }
}
