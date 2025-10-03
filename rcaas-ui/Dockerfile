FROM node:24-alpine as build

WORKDIR /app

COPY . .

RUN npm i -g pnpm
RUN pnpm install
RUN pnpm run build

ENV NODE_ENV production

FROM nginx:stable-alpine

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /app/dist /app

CMD ["nginx", "-g", "daemon off;"]