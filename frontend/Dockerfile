# Этап сборки
FROM node:16-alpine AS build

# Установка рабочей директории
WORKDIR /app

# Копирование файлов зависимостей
COPY the_place/package.json the_place/package-lock.json ./

# Установка зависимостей (включая devDependencies для сборки)
RUN npm install

# Копирование исходного кода
COPY the_place/ .

# Сборка проекта
RUN npm run build

# Этап выполнения
FROM nginx:alpine

# Копирование конфигурации Nginx (если есть)
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# Копирование собранных файлов в Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Экспозиция порта
EXPOSE 80

# Команда для запуска Nginx
CMD ["nginx", "-g", "daemon off;"]