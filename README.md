# Release calendar as a service

"Rcaas" is a cloud-based software that simplifies software release management. Plan, manage, and
visualize release schedules with an intuitive calendar interface. Start by creating your own release calendar
at [https://releasecalendar.dodb.app](https://releasecalendar.dodb.app).

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
