package com.mar.mfagent.rag;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;

/**
 * @description；创建自定义的Rag检索增强顾问工厂
 * @author:mar1
 * @data:2025/05/23
 **/
public class LoveAppRagCustomAdvisorFactory {

    /**
     * 创建自定义的Rag检索增强顾问
     * @param vectorStore
     * @param status
     * @return
     */
    public static Advisor createLoveAppRagCustomAdvisor(VectorStore vectorStore,String status) {


        Filter.Expression expression = new FilterExpressionBuilder()
                .eq("status", status)
                .build();
        VectorStoreDocumentRetriever documentRetriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                // 过滤条件
                .filterExpression(expression)
                // 相似度阈值
                .similarityThreshold(0.5)
                //返回文档数量
                .topK(3)
                .build();

        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .queryAugmenter(LoveAppContextualQueryAugmenterFactory.createInstance())
                .build();
    }
}
