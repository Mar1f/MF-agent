<template>
  <div class="home-container">
    <div class="ai-particles">
      <div v-for="n in 20" :key="n" class="particle"></div>
    </div>

    <div class="hero-section">
      <div class="hero-content">
        <div class="ai-brain">
          <div class="brain-circle">
            <div class="brain-content">
              <div class="circuit-container">
                <div class="circuit-line line1"></div>
                <div class="circuit-line line2"></div>
                <div class="circuit-line line3"></div>
                <div class="circuit-dot dot1"></div>
                <div class="circuit-dot dot2"></div>
                <div class="circuit-dot dot3"></div>
              </div>
              <div class="brain-icon">🧠</div>
            </div>
          </div>
          <div class="brain-waves"></div>
        </div>
        <h1 class="glitch-text">AI 智能助手平台</h1>
        <p class="typing-text">探索 AI 的无限可能，让智能科技服务于生活</p>
      </div>
    </div>

    <div class="services-section">
      <div class="service-card" @click="$router.push('/love-master')">
        <div class="card-content">
          <div class="card-icon">
            <div class="pulse-circle"></div>
            <span class="icon">💝</span>
          </div>
          <h2>AI 恋爱大师</h2>
          <p>智能情感顾问，为您解答情感困惑</p>
          <div class="features">
            <div class="feature">
              <span>✨专业情感分析</span>
            </div>
            <div class="feature">
              <span>🤝贴心建议指导</span>
            </div>
            <div class="feature">
              <span>💡个性化方案</span>
            </div>
          </div>
          <div class="data-stream">
            <div v-for="n in 10" :key="n" class="data-bit"></div>
          </div>
        </div>
      </div>

      <div class="service-card" @click="$router.push('/super-agent')">
        <div class="card-content">
          <div class="card-icon">
            <div class="pulse-circle"></div>
            <span class="icon">🤖</span>
          </div>
          <h2>AI 超级智能体</h2>
          <p>全能AI助手，解决各类问题</p>
          <div class="features">
            <div class="feature">
              <span>🎯精准解答</span>
            </div>
            <div class="feature">
              <span>📚知识支持</span>
            </div>
            <div class="feature">
              <span>⚡实时响应</span>
            </div>
          </div>
          <div class="data-stream">
            <div v-for="n in 10" :key="n" class="data-bit"></div>
          </div>
        </div>
      </div>
    </div>

    <div class="features-section">
      <div class="tech-features">
        <div class="tech-feature">
          <div class="feature-icon-container">
            <div class="feature-icon-bg"></div>
            <span class="feature-icon">💬</span>
          </div>
          <div class="feature-text">
            <h3>实时对话</h3>
            <p>流式响应，即时交互</p>
            <div class="typing-dots">
              <span></span><span></span><span></span>
            </div>
          </div>
        </div>
        <div class="tech-feature">
          <div class="feature-icon-container">
            <div class="feature-icon-bg"></div>
            <span class="feature-icon">🎯</span>
          </div>
          <div class="feature-text">
            <h3>深度理解</h3>
            <p>智能分析，精准回应</p>
            <div class="pulse-line">
              <span></span><span></span><span></span>
            </div>
          </div>
        </div>
        <div class="tech-feature">
          <div class="feature-icon-container">
            <div class="feature-icon-bg"></div>
            <span class="feature-icon">🚀</span>
          </div>
          <div class="feature-text">
            <h3>持续进化</h3>
            <p>自我学习，不断升级</p>
            <div class="progress-bar">
              <div class="progress-inner"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="tech-background">
      <div class="tech-grid">
        <div v-for="n in 50" :key="n" class="grid-item"></div>
      </div>
    </div>

    <footer class="footer">
      <div class="footer-waves">
        <div class="wave wave1"></div>
        <div class="wave wave2"></div>
        <div class="wave wave3"></div>
      </div>
      <div class="footer-content">
        <p>© 2024 AI 智能助手平台 - 让AI服务更懂你</p>
      </div>
    </footer>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'

onMounted(() => {
  // 添加粒子动画
  const particles = document.querySelectorAll('.particle')
  particles.forEach(particle => {
    setParticlePosition(particle)
  })
})

function setParticlePosition(particle) {
  const x = Math.random() * 100
  const y = Math.random() * 100
  const delay = Math.random() * 2
  const duration = 2 + Math.random() * 2

  particle.style.left = `${x}%`
  particle.style.top = `${y}%`
  particle.style.animationDelay = `${delay}s`
  particle.style.animationDuration = `${duration}s`
}
</script>

<style scoped>
.home-container {
  min-height: 100%;
  height: 100%;
  background: #0a0a1f;
  color: white;
  overflow-x: hidden;
  overflow-y: auto;
  position: relative;
}

/* AI 粒子背景 */
.ai-particles {
  position: fixed;
  width: 100%;
  height: 100%;
  pointer-events: none;
}

.particle {
  position: absolute;
  width: 4px;
  height: 4px;
  background: rgba(64, 158, 255, 0.5);
  border-radius: 50%;
  animation: float 3s infinite linear;
}

@keyframes float {
  0% {
    transform: translateY(0) scale(1);
    opacity: 0;
  }
  50% {
    opacity: 0.8;
    transform: translateY(-100px) scale(1.2);
  }
  100% {
    transform: translateY(-200px) scale(1);
    opacity: 0;
  }
}

/* Hero 区域 */
.hero-section {
  padding: 4rem 2rem 1rem 2rem;
  position: relative;
  z-index: 1;
}

.hero-content {
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
}

.ai-brain {
  position: relative;
  width: 120px;
  height: 120px;
  margin: 0 auto 2rem;
}

.brain-circle {
  position: absolute;
  width: 100%;
  height: 100%;
  border: 2px solid #409EFF;
  border-radius: 50%;
  animation: pulse 2s infinite;
  background: rgba(64, 158, 255, 0.1);
  backdrop-filter: blur(5px);
}

.brain-content {
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.circuit-container {
  position: absolute;
  width: 100%;
  height: 100%;
  animation: rotate 10s linear infinite;
}

.circuit-line {
  position: absolute;
  background: rgba(64, 158, 255, 0.5);
}

.line1 {
  width: 40%;
  height: 2px;
  top: 30%;
  left: 30%;
  transform: rotate(45deg);
}

.line2 {
  width: 30%;
  height: 2px;
  top: 50%;
  left: 35%;
  transform: rotate(-30deg);
}

.line3 {
  width: 35%;
  height: 2px;
  top: 70%;
  left: 32%;
  transform: rotate(60deg);
}

.circuit-dot {
  position: absolute;
  width: 6px;
  height: 6px;
  background: #409EFF;
  border-radius: 50%;
  animation: glow 1.5s ease-in-out infinite alternate;
}

.dot1 {
  top: 30%;
  left: 30%;
}

.dot2 {
  top: 50%;
  left: 60%;
}

.dot3 {
  top: 70%;
  left: 40%;
}

.brain-icon {
  font-size: 2.5rem;
  z-index: 2;
  animation: float 3s ease-in-out infinite;
}

@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

@keyframes glow {
  from {
    transform: scale(1);
    opacity: 0.5;
  }
  to {
    transform: scale(1.5);
    opacity: 1;
  }
}

@keyframes float {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-5px);
  }
}

.brain-waves {
  position: absolute;
  width: 140%;
  height: 140%;
  left: -20%;
  top: -20%;
  border: 2px solid rgba(64, 158, 255, 0.5);
  border-radius: 50%;
  animation: waves 2s infinite;
}

.glitch-text {
  font-size: 3rem;
  font-weight: bold;
  color: #409EFF;
  text-shadow: 2px 2px 4px rgba(64, 158, 255, 0.5);
  margin-bottom: 1rem;
  position: relative;
}

.typing-text {
  font-size: 1.2rem;
  opacity: 0.9;
  margin-bottom: 1rem;
}

.tech-features {
  display: flex;
  justify-content: center;
  gap: 2rem;
  margin-top: 3rem;
  flex-wrap: wrap;
}

.tech-feature {
  background: rgba(255, 255, 255, 0.05);
  border-radius: 15px;
  padding: 1.5rem;
  width: 280px;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  position: relative;
  overflow: hidden;
}

.tech-feature:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
  background: rgba(255, 255, 255, 0.08);
}

.feature-icon-container {
  position: relative;
  width: 50px;
  height: 50px;
  margin-bottom: 1rem;
}

.feature-icon-bg {
  position: absolute;
  width: 100%;
  height: 100%;
  background: linear-gradient(135deg, #409EFF 0%, #36D1DC 100%);
  border-radius: 12px;
  opacity: 0.2;
  animation: rotate 4s linear infinite;
}

.feature-icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 1.8rem;
  z-index: 1;
}

.feature-text {
  color: white;
}

.feature-text h3 {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
  background: linear-gradient(135deg, #409EFF 0%, #36D1DC 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.feature-text p {
  opacity: 0.8;
  font-size: 0.9rem;
  margin-bottom: 1rem;
}

.typing-dots {
  display: flex;
  gap: 5px;
}

.typing-dots span {
  width: 6px;
  height: 6px;
  background: #409EFF;
  border-radius: 50%;
  animation: typing 1s infinite;
}

.typing-dots span:nth-child(2) {
  animation-delay: 0.2s;
}

.typing-dots span:nth-child(3) {
  animation-delay: 0.4s;
}

@keyframes typing {
  0%, 100% {
    transform: translateY(0);
    opacity: 0.3;
  }
  50% {
    transform: translateY(-5px);
    opacity: 1;
  }
}

.pulse-line {
  display: flex;
  gap: 5px;
  align-items: center;
}

.pulse-line span {
  height: 2px;
  background: #409EFF;
  animation: pulse-width 2s infinite;
}

.pulse-line span:nth-child(1) {
  width: 15px;
}

.pulse-line span:nth-child(2) {
  width: 25px;
  animation-delay: 0.2s;
}

.pulse-line span:nth-child(3) {
  width: 10px;
  animation-delay: 0.4s;
}

@keyframes pulse-width {
  0%, 100% {
    opacity: 0.3;
    transform: scaleX(1);
  }
  50% {
    opacity: 1;
    transform: scaleX(1.2);
  }
}

.progress-bar {
  width: 100%;
  height: 4px;
  background: rgba(64, 158, 255, 0.2);
  border-radius: 2px;
  overflow: hidden;
}

.progress-inner {
  width: 100%;
  height: 100%;
  background: #409EFF;
  animation: progress 3s infinite;
  transform-origin: left;
}

@keyframes progress {
  0% {
    transform: scaleX(0);
  }
  50% {
    transform: scaleX(1);
  }
  100% {
    transform: scaleX(0);
  }
}

/* 服务卡片区域 */
.services-section {
  padding: 1rem 2rem 2rem 2rem;
  display: flex;
  justify-content: center;
  gap: 2rem;
  flex-wrap: wrap;
  position: relative;
  z-index: 1;
}

.service-card {
  background: rgba(255, 255, 255, 0.05);
  border-radius: 20px;
  padding: 2rem;
  width: 340px;
  cursor: pointer;
  transition: all 0.3s ease;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
  position: relative;
  overflow: hidden;
}

.service-card:hover {
  transform: translateY(-5px);
  background: rgba(255, 255, 255, 0.08);
  box-shadow: 0 10px 30px rgba(64, 158, 255, 0.2);
}

.card-content {
  position: relative;
  z-index: 2;
}

.card-icon {
  position: relative;
  width: 80px;
  height: 80px;
  margin: 0 auto 1.5rem;
}

.pulse-circle {
  position: absolute;
  width: 100%;
  height: 100%;
  border: 2px solid #409EFF;
  border-radius: 50%;
  animation: pulse 2s infinite;
}

.icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 2.5rem;
}

.features {
  margin-top: 2rem;
}

.feature {
  display: flex;
  align-items: center;
  margin: 1rem 0;
  gap: 1rem;
}

.feature-icon {
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(64, 158, 255, 0.2);
  border-radius: 50%;
}

/* 神经网络动画 */
.neural-network {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.neuron {
  width: 6px;
  height: 6px;
  background: #409EFF;
  border-radius: 50%;
  animation: neuron-pulse 1.5s infinite;
}

@keyframes neuron-pulse {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  50% {
    transform: scale(1.5);
    opacity: 0.5;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

/* 数据流动画 */
.data-stream {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: space-around;
}

.data-bit {
  width: 4px;
  height: 10px;
  background: #409EFF;
  animation: data-flow 2s infinite linear;
}

@keyframes data-flow {
  0% {
    transform: translateY(0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    transform: translateY(-30px);
    opacity: 0;
  }
}

/* 科技网格背景 */
.tech-background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0;
  opacity: 0.1;
}

.tech-grid {
  display: grid;
  grid-template-columns: repeat(10, 1fr);
  grid-template-rows: repeat(10, 1fr);
  width: 100%;
  height: 100%;
}

.grid-item {
  border: 1px solid rgba(64, 158, 255, 0.2);
}

/* 页脚波浪动画 */
.footer {
  position: relative;
  padding: 3rem 0 1rem;
  text-align: center;
}

.footer-waves {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100px;
  overflow: hidden;
}

.wave {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 200%;
  height: 100%;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 800 88.7'%3E%3Cpath d='M800 56.9c-155.5 0-204.9-50-405.5-49.9-200 0-250 49.9-394.5 49.9v31.8h800v-.2-31.6z' fill='%23409EFF' fill-opacity='0.2'/%3E%3C/svg%3E");
  animation: wave 10s linear infinite;
}

.wave2 {
  animation: wave 8s linear infinite;
  opacity: 0.5;
}

.wave3 {
  animation: wave 6s linear infinite;
  opacity: 0.2;
}

@keyframes wave {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-50%);
  }
}

.footer-content {
  position: relative;
  z-index: 1;
}

.features-section {
  padding: 4rem 2rem;
  background: rgba(255, 255, 255, 0.02);
  position: relative;
  z-index: 1;
}

@media (max-width: 768px) {
  .hero-content {
    flex-direction: column;
    align-items: center;
  }

  .service-card {
    width: 100%;
    max-width: 400px;
  }

  .glitch-text {
    font-size: 2rem;
  }

  .tech-features {
    flex-direction: column;
    align-items: center;
  }

  .tech-feature {
    width: 100%;
    max-width: 280px;
  }
}
</style> 