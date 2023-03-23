# Example Kogito Quarkus Serverless Workflow

You will need:

- Maven
- Java 11
- Docker
- Quarkus

To run, first bring up the API side cars:

```shell
$ cd api-auth-side-car
$ docker-compose up --build
```

Then bring up the sample service `international-greeting-service`:

```shell
$ cd international-greeting-service
$ mvn clean && quarkus dev
```

This API will be available at http://localhost:8081

Then bring the **Serverless Workflow** service:

```shell
$ cd serverless-workflow
$ mvn clean && quarkus dev
```

Finally, this serverless workflow based API will be available at http://localhost:8080

## Example

```shell
$ curl -X POST -H "Content-Type: application/json" -d '{"workflowdata" : {"name" : "Yoda", "country":"United States", "reference_currency":"GBP"}}' http://localhost:8080/greeting
```
