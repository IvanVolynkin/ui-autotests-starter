package ru.vanjo.qa.uiautotestsstarter.helpers.http;

import okhttp3.OkHttpClient;

public class HttpClient extends OkHttpClient {

    private final OkHttpClient client;

    private HttpClient(){
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

    private static class HttpClientHolder {
        private final static HttpClient INSTANCE = new HttpClient();
    }

    public static HttpClient instance() {
        return HttpClientHolder.INSTANCE;
    }

    public OkHttpClient getClient() {
        return client;
    }
}
