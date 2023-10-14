FROM node:18-alpine

WORKDIR /app

COPY . .

RUN npm i -g pnpm
RUN pnpm install
RUN pnpm run build

ENV NODE_ENV production

FROM bitnami/nginx:1.23

USER 0

COPY nginx.conf /opt/bitnami/nginx/conf/server_blocks/nginx.conf
COPY --from=0 /app/dist /app

USER 0
