package com.xuzhongjian.code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class CurrencyService {
    private static final Gson GSON = new GsonBuilder().create();
    private final static OkHttpClient client = new OkHttpClient.Builder().dispatcher(new Dispatcher()).pingInterval(20, TimeUnit.SECONDS).build();
    private final static Set<String> fiatSet = new HashSet<>();

    static {
        String fiatAddr = "https://api-bos-aws1.test.exodushk.com/internal/contracts/currencies?currency_type=FIAT&status=AVAILABLE&limit=1000";
        Request request = new Request.Builder().get().url(fiatAddr).build();
        Call call = client.newCall(request);
        JSONArray fiats = new JSONArray();
        try {
            Response response = call.execute();
            assert response.body() != null;
            fiats = new JSONObject(response.body().string()).getJSONObject("data").getJSONArray("items");
        } catch (IOException e) {
            log.error("fetch fiats from api exception", e);
        }
        for (int i = 0; i < fiats.length(); i++) {
            System.out.println(fiats);
            JSONObject fiat = fiats.getJSONObject(i);
            String symbol = fiat.getString("currency");
            fiatSet.add(symbol);
        }
    }

    public static Set<String> getFiats() {
        return fiatSet;
    }

    private static final String BASE_CURRENCY = "USD.FOREX"; // all fiat to usd

    public static void main(String[] args) throws IOException {


        Map<String, List<String>> paramMap = new HashMap<>();
        List<String> symbols = CurrencyService.getFiats().stream().map(fiat -> fiat + BASE_CURRENCY).collect(Collectors.toList());
        System.out.println(symbols);
        paramMap.put("symbols", symbols);


        String resp = client.newCall(new Request.Builder().post(
                        RequestBody.create(GSON.toJson(paramMap), MediaType.parse("application/json; charset=utf-8")))
                .url("https://hq-dev.tigerfintech.com/api/forex/brief")
                .addHeader("Authorization", "Bearer EiMO1SgAY4I9lJZModlctzzkStkMeJ").build()).execute().body().string();
        log.info("get brief: {}", resp);
        System.out.println(resp);
    }
}
