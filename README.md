# Release calendar as a service

"Rcaas" is a cloud-based software that simplifies software release management. Plan, manage, and
visualize release schedules with an intuitive calendar interface. Start by creating your own release calendar
at [releasecalendar.app](https://releasecalendar.app).

## Diagrams

### Context Diagram

![Context Diagram2.png](diagrams/Context%20Diagram.png)

### Container Diagram

![Container Diagram.png](diagrams/Container%20Diagram.png)

### Module Structure

![Module Structure.png](diagrams/Module%20Structure.png)

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
