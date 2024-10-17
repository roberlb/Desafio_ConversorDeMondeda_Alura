package org.example.api;

import com.google.gson.Gson;
import org.example.models.PairMoney;
import org.example.models.Rate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ExchangeRate {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    HttpResponse<String> response;
    String resultJson;
    Gson gson = new Gson();
    String json;
    String url;

    private String getResponse(String url) {
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resultJson = response.body();
        return resultJson;
    }

    public Map<String, String> getAllRate() {
        url = "https://v6.exchangerate-api.com/v6/df56c26752b842fe9c76d333/codes";
        json = getResponse(url);
        Rate response = gson.fromJson(json, Rate.class);
        Map<String, String> currencyMap = new HashMap<>();
        for (List<String> currency : response.supported_codes()) {
            currencyMap.put(currency.get(0), currency.get(1));
        }
        return currencyMap;
    }

    public PairMoney getPairMoney(String base, String target) {
        url = "https://v6.exchangerate-api.com/v6/df56c26752b842fe9c76d333/pair/" + base + "/" + target;
        json = getResponse(url);
        PairMoney pairMoney = gson.fromJson(json, PairMoney.class);
        return pairMoney;
    }

}
