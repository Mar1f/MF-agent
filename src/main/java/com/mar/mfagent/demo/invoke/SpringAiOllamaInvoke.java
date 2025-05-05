package com.mar.mfagent.demo.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description；
 * @author:mar1
 * @data:2025/04/25
 **/

//@Component
public class SpringAiOllamaInvoke implements CommandLineRunner {

    @Resource
    private ChatModel ollamaChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage output = ollamaChatModel.call(new Prompt("你好，我是Mar1f,你是什么模型"))
                .getResult()
                .getOutput();
        System.out.println(output.getText());
    }
}

