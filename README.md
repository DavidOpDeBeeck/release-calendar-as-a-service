# Release calendar as a service

"Release calendar as a service" is a cloud-based software that simplifies software release management. Plan, manage, and
visualize release schedules with an intuitive calendar interface. Customize release details, collaborate seamlessly, and
integrate effortlessly. Stay organized and focused on delivering top-notch software, all in a convenient and scalable
cloud environment. Start by creating your own release calendar at [releasecalendar.app](https://releasecalendar.app).

## Development

### Database

```shell
docker-compose up -d database
```

### API

```shell
cd rcaas-api
./gradlew bootRun
```

### UI

```shell
cd rcaas-ui
pnpm install
pnpm run dev
```
