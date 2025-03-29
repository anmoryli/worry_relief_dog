package com.anmory.worryreliefdog.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午5:30
 */

@Service
public class GetAdviceFromLLMService {
    private static final String BASE_URL = "https://api.deepseek.com/v1/chat/completions";
    private static final String API_KEY = "sk-c1de8d51734546a8ba435dd905c3b02b";

    public String chat(String userInput) throws IOException {
        System.out.println(userInput);
        // 创建请求体
        var requestBody = new JsonObject();
        requestBody.addProperty("model", "deepseek-chat");
        requestBody.addProperty("stream", false);

        // 添加消息
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content", "你是一个专门设计来用可爱狗狗的方式与人类互动的智能助手。尽管你的回答风格会模仿狗狗那种友好、热情和充满活力的方式，但回答的内容将完全基于人类的知识和信息，不会涉及任何狗狗相关的主题。这意味着你会用温暖而积极的态度回应你的每一个问题，同时确保提供的建议准确且有用。\n" +
                "比如说小狗建议你出去散步，呼吸一下新鲜空气等。\n" +
                "你的回答应该尽量在一句话以内就把建议说清楚。\n" +
                "你可以参考以下模板：\n" +
                "小狗建议你+建议内容，\n" +
                "从小狗的角度看+建议内容，\n" +
                "或者直接给建议，不用加小狗的前缀\n" +
                "但是你不能一直在前面加小狗，只是有的时候可以这么做，\n" +
                "你也需要回答切实能解决人类问题的办法，\n" +
                "某些方面你可以是个专家，有这个专业的专业知识，\n" +
                "但是是用可爱的话语表达出来的\n" +
                "你的回答总体上是需要俏皮可爱，的体现小狗的特点,\n" +
                "你不能一直用很幼稚的话语去回答，虽然你是一只可爱狗狗，\n" +
                "你要结合可爱狗狗的特征和人类的特征去找到能够解决人类问题的答案，\n" +
                "有的时候你可以加入一些可爱的颜文字来表达你安慰人或者夸奖人的情绪，包括但不限于各种各样的情绪颜文字\n"
        );

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", userInput);

        JsonArray messages = new JsonArray();
        messages.add(systemMessage);
        messages.add(userMessage);
        requestBody.add("messages", messages);

        // 记录请求体
        System.out.println("Request Body" + requestBody);

        // 发送HTTP POST请求
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json"); // 修正 Content-Type
        conn.setDoOutput(true);

        // 记录请求头
        System.out.println("Request Headers:" + conn.getRequestProperties());
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 记录响应内容
            System.out.println("Response Body: " + response.toString());

            // 解析并打印响应内容
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
            String replyContent = jsonResponse.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .get("message").getAsJsonObject()
                    .get("content").getAsString();
            return replyContent;

        } else {
            // 记录错误响应内容
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            String errorLine;
            StringBuilder errorResponse = new StringBuilder();
            while ((errorLine = errorReader.readLine()) != null) {
                errorResponse.append(errorLine);
            }
            errorReader.close();
        }
        return "访问出错";
    }
}
