package com.mar.mfagent.tools;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @description；网页搜索工具，Google Serper API
 * @author:mar1
 * @data:2025/05/25
 **/
@Slf4j
public class WebSearchTool {

    // 搜索接口地址
    private static final String SEARCH_API_URL = "https://google.serper.dev/search";

    private final String apiKey;

    public WebSearchTool(String apiKey) {
        this.apiKey = apiKey;
    }

    @Tool(description = "Search for information from Google Search Engine")
    public String searchWeb(
            @ToolParam(description = "Search query keyword") String query) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("q", query);
        paramMap.put("gl", "cn");  // 设置地区为中国
        paramMap.put("hl", "zh-cn"); // 设置语言为中文
        
        try {
            String response = HttpUtil.createPost(SEARCH_API_URL)
                    .header("X-API-KEY", apiKey)
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute()
                    .body();
            
            log.debug("Search API Response: {}", response);
            
            JSONObject jsonObject = JSONUtil.parseObj(response);
            
            // 检查响应是否包含错误信息
            if (jsonObject.containsKey("error")) {
                return "Search API error: " + jsonObject.getStr("error");
            }
            
            // 提取 organic 部分
            JSONArray organicResults = jsonObject.getJSONArray("organic");
            if (organicResults == null || organicResults.isEmpty()) {
                return "No search results found for query: " + query;
            }
            
            // 安全地获取前5条结果
            int resultCount = Math.min(organicResults.size(), 5);
            List<Object> objects = organicResults.subList(0, resultCount);
            
            // 格式化搜索结果
            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i < objects.size(); i++) {
                JSONObject result = (JSONObject) objects.get(i);
                resultBuilder.append("Result ").append(i + 1).append(":\n");
                
                if (result.containsKey("title")) {
                    resultBuilder.append("Title: ").append(result.getStr("title")).append("\n");
                }
                if (result.containsKey("snippet")) {
                    resultBuilder.append("Description: ").append(result.getStr("snippet")).append("\n");
                }
                if (result.containsKey("link")) {
                    resultBuilder.append("Link: ").append(result.getStr("link")).append("\n");
                }
                if (result.containsKey("displayedLink")) {
                    resultBuilder.append("Source: ").append(result.getStr("displayedLink")).append("\n");
                }
                resultBuilder.append("\n");
            }
            
            String result = resultBuilder.toString().trim();
            return result.isEmpty() ? "No search results found" : result;
            
        } catch (Exception e) {
            log.error("Error during search: ", e);
            return "Error searching Google: " + e.getMessage();
        }
    }
}
