# Stage 1: Build React App
FROM node:16 AS build


WORKDIR /app


COPY ./front/package.json ./

COPY ./front .

RUN npm install react-dom@18.3.1 react-icons@5.3.0 react@18.3.1

RUN npm run build


FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf


EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]