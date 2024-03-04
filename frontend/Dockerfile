FROM node:16 AS node
WORKDIR /app
COPY src .
RUN npm install
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node /app/dist/frontend /usr/share/nginx/html
EXPOSE 4200
