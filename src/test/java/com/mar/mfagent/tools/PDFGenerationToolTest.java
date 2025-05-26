package com.mar.mfagent.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PDFGenerationToolTest {

    @Test
    public void testGeneratePDF() {
        PDFGenerationTool tool = new PDFGenerationTool();
        String fileName = "jlnu.pdf";
        String content = "吉林师范大学 https://www.jlnu.edu.cn";
        String result = tool.generatePDF(fileName, content);
        assertNotNull(result);
    }
}
