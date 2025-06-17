# MF-Agent 部署指南

## 概述
本指南介绍如何在服务器上部署 MF-Agent 应用，不使用 Docker 和 PostgreSQL 数据库。

## 系统要求

### 基础环境
- **Java**: JDK 21 或更高版本
- **Maven**: 3.6+ (项目包含 Maven Wrapper，可选)
- **操作系统**: Linux/Unix/Windows
- **内存**: 建议 2GB 以上
- **磁盘空间**: 建议 1GB 以上

### 网络要求
- 能够访问阿里云 DashScope API
- 能够访问搜索 API 服务
- 开放应用端口（默认 8123）

## 部署步骤

### 1. 环境准备

#### 安装 Java 21
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-21-jdk

# CentOS/RHEL
sudo yum install java-21-openjdk-devel

# 验证安装
java -version
```

#### 设置环境变量
```bash
# 编辑环境变量文件
vim ~/.bashrc

# 添加以下内容
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export PATH=$PATH:$JAVA_HOME/bin

# 重新加载环境变量
source ~/.bashrc
```

### 2. 获取应用代码
```bash
# 克隆代码仓库
git clone <your-repository-url>
cd MF-agent
```

### 3. 配置应用

#### 修改部署脚本中的环境变量
编辑 `deploy.sh` (Linux) 或 `deploy.bat` (Windows)：

```bash
# Linux 部署脚本 (deploy.sh)
export DASHSCOPE_API_KEY="your-actual-dashscope-api-key"
export SEARCH_API_KEY="your-actual-search-api-key"
export SERVER_PORT=8123
```

```batch
REM Windows 部署脚本 (deploy.bat)
set DASHSCOPE_API_KEY=your-actual-dashscope-api-key
set SEARCH_API_KEY=your-actual-search-api-key
set SERVER_PORT=8123
```

#### 或者设置系统环境变量
```bash
# Linux/Unix
export DASHSCOPE_API_KEY="your-actual-dashscope-api-key"
export SEARCH_API_KEY="your-actual-search-api-key"
export SERVER_PORT=8123
```

### 4. 部署应用

#### Linux/Unix 系统
```bash
# 给脚本执行权限
chmod +x deploy.sh

# 启动应用
./deploy.sh start

# 查看状态
./deploy.sh status

# 停止应用
./deploy.sh stop

# 重启应用
./deploy.sh restart
```

#### Windows 系统
```batch
# 启动应用
deploy.bat start

# 查看状态
deploy.bat status

# 停止应用
deploy.bat stop

# 重启应用
deploy.bat restart
```

### 5. 验证部署

#### 检查应用状态
- 访问 API 文档: `http://your-server:8123/api/swagger-ui.html`
- 检查健康状态: `http://your-server:8123/api/actuator/health` (如果启用)
- 查看日志: `tail -f logs/application.log`

#### 测试 API 接口
```bash
# 测试基础聊天接口
curl -X POST "http://localhost:8123/api/love/chat" \
  -H "Content-Type: application/json" \
  -d '{"message": "你好", "chatId": "test-001"}'
```

## 配置说明

### 应用配置文件
- `application-prod.yml`: 生产环境配置
- 主要配置项：
  - `spring.ai.dashscope.api-key`: DashScope API 密钥
  - `search-api.api-key`: 搜索 API 密钥
  - `server.port`: 应用端口
  - `logging`: 日志配置

### 日志配置
- 日志文件位置: `logs/mf-agent.log`
- 日志级别: INFO (生产环境)
- 日志轮转: 10MB 单文件，保留 30 天

## 监控和维护

### 日志监控
```bash
# 实时查看日志
tail -f logs/mf-agent.log

# 查看错误日志
grep ERROR logs/mf-agent.log

# 查看最近的日志
tail -n 100 logs/mf-agent.log
```

### 进程监控
```bash
# 查看 Java 进程
ps aux | grep java

# 查看端口占用
netstat -tlnp | grep 8123
```

### 性能监控
```bash
# 查看内存使用
free -h

# 查看磁盘使用
df -h

# 查看 CPU 使用
top
```

## 故障排除

### 常见问题

#### 1. 应用启动失败
- 检查 Java 版本是否为 21+
- 检查端口是否被占用
- 查看启动日志中的错误信息

#### 2. API 调用失败
- 检查 DashScope API 密钥是否正确
- 检查网络连接是否正常
- 查看应用日志中的错误信息

#### 3. 内存不足
- 调整 JVM 内存参数
- 在启动脚本中添加: `-Xmx2g -Xms1g`

### 日志分析
```bash
# 查看启动日志
grep "Started MfAgentApplication" logs/mf-agent.log

# 查看错误日志
grep -i error logs/mf-agent.log

# 查看 API 调用日志
grep "ChatResponse" logs/mf-agent.log
```

## 安全建议

1. **API 密钥安全**
   - 不要在代码中硬编码 API 密钥
   - 使用环境变量或配置文件管理密钥
   - 定期轮换 API 密钥

2. **网络安全**
   - 配置防火墙规则
   - 使用 HTTPS (配置反向代理)
   - 限制访问 IP 范围

3. **系统安全**
   - 定期更新系统和 Java 版本
   - 使用非 root 用户运行应用
   - 配置日志审计

## 扩展配置

### 反向代理配置 (Nginx)
```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    location /api/ {
        proxy_pass http://localhost:8123/api/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

### 系统服务配置 (systemd)
```ini
[Unit]
Description=MF-Agent Application
After=network.target

[Service]
Type=forking
User=mfagent
WorkingDirectory=/opt/mf-agent
ExecStart=/opt/mf-agent/deploy.sh start
ExecStop=/opt/mf-agent/deploy.sh stop
Restart=always

[Install]
WantedBy=multi-user.target
```

## 联系支持
如遇到部署问题，请查看日志文件并联系技术支持团队。 