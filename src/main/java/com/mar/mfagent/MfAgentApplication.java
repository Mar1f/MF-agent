package com.mar.mfagent;

import com.mar.mfagent.rag.PgVectorVectorStoreConfig;
import org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = PgVectorStoreAutoConfiguration.class)
@SpringBootApplication
public class MfAgentApplication {


    public static void main(String[] args) {
        SpringApplication.run(MfAgentApplication.class, args);
    }

}
