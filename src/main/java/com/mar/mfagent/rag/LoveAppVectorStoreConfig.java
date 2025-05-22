package com.mar.mfagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description；向量数据库配置
 * @author:mar1
 * @data:2025/05/05
 **/
@Configuration
public class LoveAppVectorStoreConfig {

    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

    @Resource
    private MyTokenTextSplitter myTokenTextSplitter;

    @Resource
    private MyKeywordEnricher myKeywordEnricher;
    @Bean
    VectorStore loveAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel)
                .build();
        // 加载文档
        List<Document> documentsList = loveAppDocumentLoader.loadMarkdowns();
        //自主切分文档
//        List<Document> splitDocument= myTokenTextSplitter.splitCustomized(documentsList);
//        simpleVectorStore.add(splitDocument);
        // 自动补充关键词元信息
        List<Document> enrichedDocument = myKeywordEnricher.enrichDocuments(documentsList);
        simpleVectorStore.add(enrichedDocument);
//        simpleVectorStore.add(documentsList);
        return simpleVectorStore;
    }
}
