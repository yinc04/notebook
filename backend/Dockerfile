# backend/Dockerfile
# 使用包含Maven的官方镜像
FROM maven:3.9.4-eclipse-temurin-17-alpine as build

# 设置工作目录
WORKDIR /app

# 复制Maven配置文件
COPY pom.xml .

# 下载依赖
RUN mvn dependency:go-offline -B

# 复制源代码
COPY src ./src

# 构建应用
RUN mvn package -DskipTests

# 准备生产环境镜像
FROM openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /app

# 从构建阶段复制JAR文件
COPY --from=build /app/target/*.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
