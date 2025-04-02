# НАПИСАНО GPT (ждем от frontend-разработчиков информации о стеке):

# Этап сборки
FROM node:20-alpine AS build

# Установка рабочей директории
WORKDIR /app

# Копирование package.json и package-lock.json
COPY package*.json ./

# Установка зависимостей
RUN npm install

# Копирование исходного кода
COPY . .

# Сборка проекта
RUN npm run build

# Этап выполнения
FROM nginx:alpine

# Копирование собранных файлов в Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Экспозиция порта
EXPOSE 80

# Команда для запуска Nginx
CMD ["nginx", "-g", "daemon off;"]