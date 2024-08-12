package com.xuzhongjian.code;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;


public class JsonPeerIdChecker {

    public static void main(String[] args) throws IOException {
        checkPeerIdInJson();
//         checkPeerIdHttp();
    }

    public static Set<String> getPeerIdsFromJson(String fileName) {
        HashSet<String> peerIdSet = new HashSet<>();
        // 读取 JSON 文件内容
        InputStream jsonInputStream = JsonPeerIdChecker.class.getClassLoader().getResourceAsStream(fileName);
        if (jsonInputStream == null) {
            System.err.println("JSON file not found!");
            return new HashSet<>();
        }
        String jsonContent = new BufferedReader(new InputStreamReader(jsonInputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        JSONObject jsonObject = new JSONObject(jsonContent);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject peerObject = jsonArray.getJSONObject(i);
                peerIdSet.add(peerObject.getString("peer_id"));
            }
        }
        return peerIdSet;
    }

    public static void checkPeerIdInJson() {
        String[] jsonFileNames = {
                "network-info-1.json",
//                "network-info-2.json",
//                "network-info-3.json",
//                "network-info-4.json",
//                "network-info-5.json",
//                "network-info-6.json"
        };
        String peerIdFileName = "peer_ids.txt";

        try {
            Set<String> hashSet = new HashSet<>();
            for (String jsonFileName : jsonFileNames) {
                Set<String> peerIdsFromJson = getPeerIdsFromJson(jsonFileName);
                System.out.println("Peer IDs in " + jsonFileName + ": " + peerIdsFromJson.size());
                hashSet.addAll(peerIdsFromJson);
            }
            System.out.println("Total peer IDs in JSON: " + hashSet.size());
            InputStream peerIdInputStream = JsonPeerIdChecker.class.getClassLoader().getResourceAsStream(peerIdFileName);
            if (peerIdInputStream == null) {
                System.err.println("Peer ID file not found!");
                return;
            }
            int count = 0;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(peerIdInputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!hashSet.contains(line.trim())) {
                        count++;
                    } else {
                        System.out.println("Peer ID: " + line + " found in JSON");
                    }
                }
            }
            System.out.println("Total peer IDs not found in JSON: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkPeerIdHttp() throws IOException {
        String base58FileName = "base58.txt";
        InputStream base58InputStream = JsonPeerIdChecker.class.getClassLoader().getResourceAsStream(base58FileName);
        if (base58InputStream == null) {
            System.err.println("base58 not found!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(base58InputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String response = sendGetRequest(line);
                System.out.println("Peer ID: " + line + ", Response: " + response);
            }
        }
    }

    private static String sendGetRequest(String peerId) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("https://dashboard-api.quilibrium.com/peer-test?peerId=" + peerId);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
            connection.setRequestProperty("origin", "https://dashboard.quilibrium.com");
            connection.setRequestProperty("priority", "u=1, i");
            connection.setRequestProperty("referer", "https://dashboard.quilibrium.com/");
            connection.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"124\", \"Google Chrome\";v=\"124\", \"Not-A.Brand\";v=\"99\"");
            connection.setRequestProperty("sec-ch-ua-mobile", "?0");
            connection.setRequestProperty("sec-ch-ua-platform", "\"macOS\"");
            connection.setRequestProperty("sec-fetch-dest", "empty");
            connection.setRequestProperty("sec-fetch-mode", "cors");
            connection.setRequestProperty("sec-fetch-site", "same-site");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } else {
                response.append("GET request failed with response code: ").append(responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response.toString();
    }
}
