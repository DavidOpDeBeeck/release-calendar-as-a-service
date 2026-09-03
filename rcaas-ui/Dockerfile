FROM node:24-alpine AS build

WORKDIR /app

RUN npm install --global pnpm@11.1.2

COPY package.json pnpm-lock.yaml pnpm-workspace.yaml ./
RUN pnpm install --frozen-lockfile

COPY . .
RUN pnpm run build

FROM nginx:stable-alpine

COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN nginx -t

COPY --from=build /app/dist /app

EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]
