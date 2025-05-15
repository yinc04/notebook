以下是根据您提供的信息整理的标准README.md文件，包含项目结构、环境要求、启动命令和注意事项等内容：


# Notebook 项目文档

## 一、项目结构
```
notebook/
├── backend/             # 后端模块 (Spring Boot)
├── frontend/            # 前端模块 (Vue.js)
├── docker-compose.yml   # 容器化部署配置
└── notebook.sql         # 数据库初始化脚本
```


## 二、环境要求
| 工具      | 版本    | 说明                          |
|-----------|---------|-------------------------------|
| JDK       | 17      | 后端运行环境                  |
| Node.js   | 22.2.0  | 前端构建工具                  |
| Maven     | 3.9.9   | 后端依赖管理                  |
| MySQL     | 8.0+    | 数据库 (可选，支持容器化部署) |


## 三、启动步骤

### 1. 后端服务启动
```bash
cd backend
mvn spring-boot:run
```
- 默认端口：`8080`
- 配置文件：`backend/src/main/resources/application.yml`

### 2. 前端服务启动
```bash
cd frontend
npm install     # 首次启动需要安装依赖
npm run dev     # 启动开发服务器
```
- 默认端口：`5173`
- 访问地址：[http://localhost:5173](http://localhost:5173)


## 四、跨域配置说明
- **后端配置**：已在 `backend/config/CorsConfig.java` 中设置全局跨域规则
  ```java
  @Configuration
  public class CorsConfig implements WebMvcConfigurer {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
              .allowedOrigins("http://localhost:5173") // 前端域名
              .allowedMethods("GET", "POST", "PUT", "DELETE")
              .allowedHeaders("*")
              .allowCredentials(true);
      }
  }
  ```
- **前端注意事项**：若修改后端端口，需同步更新 `frontend/src/utils/axiosUtil.js` 中的 baseURL
  ```javascript
  const service = axios.create({
      baseURL: 'http://localhost:8080', // 后端API地址
      timeout: 5000
  })
  ```


## 五、容器化部署（可选）
```bash
docker-compose up -d  # 启动所有服务
docker-compose down   # 停止并移除容器
```
- 配置文件：`docker-compose.yml`
- 服务映射：
   - 前端：`80` 端口 → `http://localhost`
   - 后端：`8080` 端口 → `http://localhost:8080`


## 六、数据库初始化
1. 创建数据库：`notebook`
2. 执行 `notebook.sql` 脚本初始化表结构
3. 配置数据库连接（`application.yml`）：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/notebook
       username: root
       password: your_password
   ```


## 七、贡献指南
1. 分支规范：
   - `main`：生产环境代码
   - `develop`：开发主分支
   - `feature/*`：功能开发分支
2. 提交规范：使用 [Conventional Commits](https://www.conventionalcommits.org/) 规范
   ```bash
   git commit -m "feat: 添加用户注册功能"
   git commit -m "fix: 修复登录验证漏洞"
   ```


## 八、常见问题
1. **跨域错误**：
   - 检查后端 `CorsConfig` 中的允许域名
   - 确认前端 `axiosUtil.js` 中的 baseURL 正确

2. **依赖安装失败**：
   ```bash
   # 前端清除缓存
   cd frontend
   npm cache clean --force
   npm install

   # 后端强制更新依赖
   cd backend
   mvn clean install -U
   ```

3. **数据库连接问题**：
   - 检查 MySQL 服务是否启动
   - 确认 `application.yml` 中的用户名和密码正确


## 九、许可证
本项目采用 [MIT License](LICENSE)。
