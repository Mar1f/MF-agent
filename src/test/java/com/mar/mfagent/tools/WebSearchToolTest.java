package com.mar.mfagent.tools;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class WebSearchToolTest {

    @Value("${search-api.api-key}")
    private String searchApiKey;

    @Test
    public void testSearchWeb() {
        WebSearchTool tool = new WebSearchTool(searchApiKey);
        String query = "吉林师范大学";
        String result = tool.searchWeb(query);
        
        log.info("Search result for '{}': {}", query, result);
        
        assertNotNull(result, "Search result should not be null");
        assertFalse(result.isEmpty(), "Search result should not be empty");
        assertFalse(result.contains("No search results found"), "Should find search results");
        assertTrue(result.contains("吉林师范大学"), "Result should contain the search query");
    }
}
