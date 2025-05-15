简易记事本项目
项目简介
本项目是一个简易记事本应用，包含前端和后端部分。前端基于 Vue.js 构建用户界面，后端采用 Spring Boot 搭建服务，使用 MyBatis 进行数据库操作，数据库使用 MySQL。项目支持用户注册、登录、更新账户信息、修改密码等功能，同时可以管理事件分类。
项目结构
plaintext
notebook/
├── backend/             # 后端代码
│   ├── src/             # 源代码
│   │   ├── main/        # 主代码目录
│   │   │   ├── java/    # Java 代码
│   │   │   ├── resources/# 资源文件
│   │   │       ├── application.yml # 应用配置文件
│   │   │       └── backend/
│   │   │           └── mapper/    # MyBatis 映射文件
│   │   └── test/        # 测试代码
│   └── pom.xml          # Maven 项目配置文件
├── frontend/            # 前端代码
│   ├── src/             # 源代码
│   │   ├── router/      # 路由配置
│   │   ├── views/       # 视图组件
│   │   └── main.js      # 入口文件
│   ├── index.html       # 前端入口 HTML 文件
│   ├── package.json     # Node.js 项目配置文件
│   └── vite.config.js   # Vite 配置文件
├── .idea/               # IntelliJ IDEA 项目配置文件
├── docker-compose.yml   # Docker 容器编排文件
└── .gitignore           # Git 忽略文件配置

技术栈
前端
Vue.js：用于构建用户界面。
Vite：快速的前端构建工具。
ElementPlus：基于 Vue 3 的 UI 组件库。
Pinia：Vue.js 的状态管理库。
后端
Spring Boot：简化 Spring 应用开发。
MyBatis：持久层框架，用于数据库操作。
MySQL：关系型数据库。
JUnit：单元测试框架。
Mockito：模拟对象框架，用于测试。
环境准备
后端
JDK 17：确保已安装 JDK 17 或更高版本。
Maven：用于项目依赖管理和构建。
MySQL：创建名为 notebook 的数据库，并确保数据库服务已启动。
前端
Node.js：建议使用 Node.js 18 或更高版本。
npm：Node.js 包管理器。
项目部署
后端部署
打开 backend/src/main/resources/application.yml 文件，配置数据库连接信息：

yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/notebook
    username: root
    password: 123456

进入 backend 目录，执行以下命令构建项目：

bash
mvn clean install

启动后端服务：

bash
mvn spring-boot:run
前端部署
进入 frontend 目录，安装项目依赖：

bash
npm install

启动前端开发服务器：

bash
npm run dev
使用 Docker 部署
确保已安装 Docker 和 Docker Compose。
在项目根目录下，执行以下命令启动容器：

bash
docker-compose up -d
接口文档
用户相关接口
注册账户：
URL：/user/register
方法：POST
请求体：User 对象
响应：注册结果
登录：
URL：/user/login
方法：POST
请求体：包含 username 和 password 的对象
响应：用户信息
更新账户信息：
URL：/user/update
方法：POST
请求体：User 对象
响应：更新结果
修改密码：
URL：/user/updatePassword
方法：POST
请求体：User 对象
响应：修改结果
分类相关接口
添加分类：
URL：/category/add
方法：POST
请求体：Category 对象
响应：添加结果
测试
项目中使用 JUnit 和 Mockito 进行单元测试，测试代码位于 backend/src/test 目录下。可以使用以下命令运行测试：

bash
mvn test
贡献
如果你想为项目做出贡献，请遵循以下步骤：

Fork 项目。
创建新的分支：git checkout -b feature/your-feature。
提交代码：git commit -m "Add your feature"。
推送分支：git push origin feature/your-feature。
发起 Pull Request。
许可证
本项目采用 MIT 许可证。
