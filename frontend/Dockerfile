# 使用官方 Node.js 20 运行时作为基础镜像
FROM node:20-alpine as build-stage

# 设置工作目录
WORKDIR /app

# 将 package.json 和 yarn.lock 或 package-lock.json 复制到容器中
COPY package*.json ./

# 安装项目依赖
RUN npm install

# 将整个项目复制到容器的工作目录中
COPY . .

# 构建 Vue 应用
RUN npm run build

# 准备生产环境镜像
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html

# 暴露80端口，以便可以访问应用
EXPOSE 80

# 默认命令启动nginx
CMD ["nginx", "-g", "daemon off;"]
