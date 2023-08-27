package ru.vanjo.qa.uiautotestsstarter.helpers.testrail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import ru.vanjo.qa.uiautotestsstarter.common.Configuration;
import ru.vanjo.qa.uiautotestsstarter.helpers.http.HttpClient;
import ru.vanjo.qa.uiautotestsstarter.helpers.testrail.models.Result;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class TestRailClient {

    private final static String CREDENTIALS = Credentials.basic(
            Configuration.instance().getConfig().getString("testrail.user"),
            Configuration.instance().getConfig().getString("testrail.password")
    );

    private final static String URL = Configuration.instance().getConfig().getString("testrail.url")
            + "index.php?/api/v2/";

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Result addResultForCase(int runId, int caseId, Status status) {
        String requestUrl = URL + "add_result_for_case/" + runId + "/" + caseId;
        Map<String, Object> requestFields = new HashMap<>();
        requestFields.put("status_id", status.getValue());
        var response = post(requestUrl, requestFields);
        try {
            return OBJECT_MAPPER.readValue(response, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String post(String requestUrl, Map<String, Object> requestFields) {
        var mediaType = MediaType.parse("application/json; charset=utf-8");
        String requestBodyString;
        try {
            requestBodyString = OBJECT_MAPPER.writeValueAsString(requestFields);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var requestBody = RequestBody.create(mediaType, requestBodyString);

        var request = new Request.Builder()
                .url(requestUrl)
                .addHeader("Authorization", CREDENTIALS)
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        var retryCounter = 1;
        while (retryCounter >= 0) {
            try {
                var response = HttpClient.instance().getClient().newCall(request).execute();
                return response.body() != null ? response.body().string() : "";
            } catch (SocketTimeoutException t) {
                System.out.printf("Failed execute request %s with SocketTimeout exception%n", requestUrl);
                retryCounter--;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Error while execute request: " + requestUrl);
    }
}
