<template>
  <div class="chat-container">
    <!-- 聊天记录区域 -->
    <div class="chat-messages" ref="messagesContainer">
      <div v-for="(msg, index) in messages" :key="index" class="message-wrapper">
        <!-- AI消息 -->
        <div v-if="!msg.isUser" 
             class="message ai-message" 
             :class="[msg.type]">
          <div class="avatar ai-avatar">
            <AiAvatarFallback :type="aiType" />
          </div>
          <div class="message-bubble">
            <div class="message-content">
              {{ msg.content }}
              <span v-if="connectionStatus === 'connecting' && index === messages.length - 1" class="typing-indicator">▋</span>
            </div>
            <div class="message-time">{{ formatTime(msg.time) }}</div>
          </div>
        </div>
        
        <!-- 用户消息 -->
        <div v-else class="message user-message" :class="[msg.type]">
          <div class="message-bubble">
            <div class="message-content">{{ msg.content }}</div>
            <div class="message-time">{{ formatTime(msg.time) }}</div>
          </div>
          <div class="avatar user-avatar">
            <div class="avatar-placeholder">我</div>
          </div>
        </div>
      </div>
      
      <!-- 空状态提示 -->
      <div v-if="messages.length === 0" class="empty-state">
        <div class="empty-icon">💕</div>
        <div class="empty-text">暂无消息，开始聊天吧！</div>
      </div>
    </div>

    <!-- 输入区域 -->
    <div class="chat-input-container">
      <div class="chat-input">
        <div class="input-wrapper">
          <textarea 
            v-model="inputMessage" 
            @keydown.enter.prevent="sendMessage"
            :placeholder="aiType === 'love' ? '给AI恋爱大师 发送消息' : '给AI超级智能体 发送消息'" 
            class="input-box"
            :disabled="connectionStatus === 'connecting'"
          ></textarea>
          <button 
            @click="sendMessage" 
            class="send-button"
            :disabled="connectionStatus === 'connecting' || !inputMessage.trim()"
          >
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M12 19L19 12L12 5L10.59 6.41L15.17 11H5V13H15.17L10.59 17.59L12 19Z" fill="currentColor"/>
            </svg>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, nextTick, watch, computed } from 'vue'
import AiAvatarFallback from './AiAvatarFallback.vue'

const props = defineProps({
  messages: {
    type: Array,
    default: () => []
  },
  connectionStatus: {
    type: String,
    default: 'disconnected'
  },
  aiType: {
    type: String,
    default: 'default'  // 'love' 或 'super'
  }
})

const emit = defineEmits(['send-message'])

const inputMessage = ref('')
const messagesContainer = ref(null)

// 根据AI类型选择不同头像
const aiAvatar = computed(() => {
  return props.aiType === 'love' 
    ? '/ai-love-avatar.png'  // 恋爱大师头像
    : '/ai-super-avatar.png' // 超级智能体头像
})

// 发送消息
const sendMessage = () => {
  if (!inputMessage.value.trim()) return
  
  emit('send-message', inputMessage.value)
  inputMessage.value = ''
}

// 格式化时间
const formatTime = (timestamp) => {
  const date = new Date(timestamp)
  return date.toLocaleTimeString('zh-CN', { hour: '2-digit', minute: '2-digit' })
}

// 自动滚动到底部
const scrollToBottom = async () => {
  await nextTick()
  if (messagesContainer.value) {
    messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight
  }
}

// 监听消息变化与内容变化，自动滚动
watch(() => props.messages.length, () => {
  scrollToBottom()
})

watch(() => props.messages.map(m => m.content).join(''), () => {
  scrollToBottom()
})

onMounted(() => {
  scrollToBottom()
})
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 70vh;
  min-height: 600px;
  background: linear-gradient(135deg, #ffeef8 0%, #f8e8f0 50%, #f0d8e8 100%);
  border-radius: 16px;
  overflow: hidden;
  position: relative;
  box-shadow: 0 8px 32px rgba(255, 182, 193, 0.3);
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 24px;
  padding-bottom: 80px;
  display: flex;
  flex-direction: column;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 80px;
}

/* 滚动条样式 */
.chat-messages::-webkit-scrollbar {
  width: 6px;
}

.chat-messages::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.1);
  border-radius: 3px;
}

.chat-messages::-webkit-scrollbar-thumb {
  background: rgba(255, 105, 180, 0.3);
  border-radius: 3px;
}

.chat-messages::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 105, 180, 0.5);
}

.message-wrapper {
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  width: 100%;
}

.message {
  display: flex;
  align-items: flex-start;
  max-width: 80%;
  margin-bottom: 8px;
}

.user-message {
  margin-left: auto;
  flex-direction: row;
}

.ai-message {
  margin-right: auto;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.user-avatar {
  margin-left: 12px;
}

.ai-avatar {
  margin-right: 12px;
}

.avatar-placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #ff69b4, #ff1493);
  color: white;
  font-weight: bold;
  font-size: 14px;
}

.message-bubble {
  padding: 16px 20px;
  border-radius: 20px;
  position: relative;
  word-wrap: break-word;
  min-width: 120px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10px);
}

.user-message .message-bubble {
  background: linear-gradient(135deg, #ff69b4, #ff1493);
  color: white;
  border-bottom-right-radius: 8px;
  text-align: left;
}

.ai-message .message-bubble {
  background: rgba(255, 255, 255, 0.9);
  color: #333;
  border-bottom-left-radius: 8px;
  text-align: left;
  border: 1px solid rgba(255, 182, 193, 0.2);
}

.message-content {
  font-size: 16px;
  line-height: 1.6;
  white-space: pre-wrap;
}

.message-time {
  font-size: 12px;
  opacity: 0.7;
  margin-top: 8px;
  text-align: right;
}

.user-message .message-time {
  color: rgba(255, 255, 255, 0.8);
}

.ai-message .message-time {
  color: rgba(51, 51, 51, 0.6);
}

.chat-input-container {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: linear-gradient(135deg, #ffeef8 0%, #f8e8f0 50%, #f0d8e8 100%);
  backdrop-filter: blur(10px);
  border-top: 1px solid rgba(255, 182, 193, 0.2);
  z-index: 100;
  height: 80px;
  box-shadow: 0 -4px 20px rgba(255, 182, 193, 0.1);
}

.chat-input {
  display: flex;
  padding: 16px 20px;
  height: 100%;
  box-sizing: border-box;
  align-items: center;
}

.input-wrapper {
  display: flex;
  align-items: center;
  flex-grow: 1;
  background: white;
  border-radius: 25px;
  padding: 4px 4px 4px 20px;
  box-shadow: 0 4px 16px rgba(255, 182, 193, 0.2);
  border: 2px solid rgba(255, 182, 193, 0.3);
  transition: all 0.3s ease;
}

.input-wrapper:focus-within {
  border-color: #ff69b4;
  box-shadow: 0 4px 20px rgba(255, 105, 180, 0.3);
}

.input-box {
  flex-grow: 1;
  border: none;
  outline: none;
  padding: 12px 0;
  font-size: 16px;
  resize: none;
  min-height: 20px;
  max-height: 40px;
  background: transparent;
  color: #333;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

.input-box::-webkit-scrollbar {
  display: none;
}

.input-box::placeholder {
  color: #999;
}

.send-button {
  margin-left: 12px;
  background: linear-gradient(135deg, #ff69b4, #ff1493);
  color: white;
  border: none;
  border-radius: 50%;
  width: 44px;
  height: 44px;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 12px rgba(255, 105, 180, 0.3);
}

.send-button:hover:not(:disabled) {
  background: linear-gradient(135deg, #ff1493, #dc143c);
  transform: scale(1.05);
  box-shadow: 0 6px 16px rgba(255, 105, 180, 0.4);
}

.send-button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
  transform: none;
}

.typing-indicator {
  display: inline-block;
  animation: blink 0.7s infinite;
  margin-left: 2px;
  color: #ff69b4;
}

@keyframes blink {
  0% { opacity: 0; }
  50% { opacity: 1; }
  100% { opacity: 0; }
}

.input-box:disabled, .send-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* 空状态样式 */
.empty-state {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px 16px;
  text-align: center;
}

.empty-icon {
  font-size: 64px;
  margin-bottom: 20px;
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.1); }
  100% { transform: scale(1); }
}

.empty-text {
  font-size: 18px;
  color: rgba(255, 105, 180, 0.8);
  font-weight: 500;
  text-shadow: 0 2px 4px rgba(255, 255, 255, 0.5);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .message {
    max-width: 90%;
  }
  
  .message-content {
    font-size: 15px;
  }
  
  .chat-input {
    padding: 12px 16px;
  }
  
  .input-wrapper {
    padding: 3px 3px 3px 16px;
  }
  
  .send-button {
    width: 40px;
    height: 40px;
  }
}

@media (max-width: 480px) {
  .avatar {
    width: 36px;
    height: 36px;
  }
  
  .message-bubble {
    padding: 12px 16px;
  }
  
  .message-content {
    font-size: 14px;
  }
  
  .chat-input-container {
    height: 72px;
  }
  
  .chat-messages {
    bottom: 72px;
    padding: 16px;
  }
  
  .empty-icon {
    font-size: 48px;
  }
  
  .empty-text {
    font-size: 16px;
  }
}

/* 消息动画 */
.ai-answer {
  animation: fadeInUp 0.4s ease-out;
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 连续消息样式优化 */
.ai-message + .ai-message {
  margin-top: 4px;
}

.ai-message + .ai-message .avatar {
  visibility: hidden;
}

.ai-message + .ai-message .message-bubble {
  border-top-left-radius: 12px;
}

.user-message + .user-message {
  margin-top: 4px;
}

.user-message + .user-message .avatar {
  visibility: hidden;
}

.user-message + .user-message .message-bubble {
  border-top-right-radius: 12px;
}
</style> 