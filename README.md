## Docker Image Creation

In the project directory, execute maven command:

### `mvn clean package spring-boot:build-image -Pnative`

This will generates docker image message-service image.

✅ GraalVM native image  
❌ musl-based compilation, Paketo native buildpacks use glibc, not musl  
✅ distroless base

## Kubernetes Deployment

In the project directory, need to create db resources:

### `kubectl create configmap postgres-init --from-file=./docker/init.sql`

### `kubectl apply -f ./kubernetes/db-presets/.`

To run postgre db & message service.

### `kubectl apply -f ./kubernetes/.`

To run initialize tables and indexes, via 1 time job execution
### `kubectl apply -f ./kubernetes/sql/sql-job.yaml`

To expose application to port 8080
### `kubectl port-forward deployment/message-service 8080:8080`

- It does seems like a bad solution, however it is good workaround to have app on 8080 locally

From now on both message-service & postgre-db are up and running.  App is accessible on port 8080

#### To turn it off use following commands

### `kubectl delete -f ./kubernetes/.`
### `kubectl delete -f ./kubernetes/db-presets/.`

## Docker Deployment

In the project directory, need to create db resources:

### `docker-compose -f docker/docker-compose.yml up -d`

From now on both message-service & postgre-db are up and running. App is accessible on port 8080

#### To turn it off use following commands

### `docker-compose -f docker/docker-compose.yml down`
