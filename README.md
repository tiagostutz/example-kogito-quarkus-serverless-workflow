# Example Kogito Quarkus Serverless Workflow

You will need:

- Maven
- Java 11
- Docker
- Quarkus

To run, first create 3 env files for the services invoked on RapidAPI and bring up the API side cars:

```shell
cd api-auth-side-car
```

Create the following 3 files:

**`cost-living.env`**:

```env
# NODE_ENV=development
MOCK_FILE_PATH=mock_cost_of_living.json
PROXY_BASE_URL=https://cost-of-living-and-prices.p.rapidapi.com
RAPID_API_KEY=<YOUR_RAPID_API_KEY>
RAPID_API_HOST=cost-of-living-and-prices.p.rapidapi.com
NODE_TLS_REJECT_UNAUTHORIZED=0
PORT=3001
```

**`currency-exchange.env`**:

```env
# NODE_ENV=development
PROXY_BASE_URL=https://currency-exchange.p.rapidapi.com
RAPID_API_KEY=<YOUR_RAPID_API_KEY>
RAPID_API_HOST=currency-exchange.p.rapidapi.com
NODE_TLS_REJECT_UNAUTHORIZED=0
PORT=3002
```

**`currency-converter.env`**:

```env
# NODE_ENV=development
PROXY_BASE_URL=https://currency-converter-by-api-ninjas.p.rapidapi.com
RAPID_API_KEY=<YOUR_RAPID_API_KEY>
RAPID_API_HOST=currency-converter-by-api-ninjas.p.rapidapi.com
NODE_TLS_REJECT_UNAUTHORIZED=0
PORT=3003
```

And then bring the side cars up:

```shell
docker-compose up --build
```

Then bring up the sample service `international-greeting-service`:

```shell
cd international-greeting-service
```

```shell
mvn clean && quarkus dev
```

This API will be available at http://localhost:8081

Then bring the **Serverless Workflow** service:

```shell
cd serverless-workflow
```

```shell
mvn clean && quarkus dev
```

Finally, this serverless workflow based API will be available at http://localhost:8080

## Examples

```shell
curl -X POST -H "Content-Type: application/json" -d '{"workflowdata" : {"name" : "Yoda", "country":"United States", "reference_currency":"GBP"}}' http://localhost:8080/greeting
```

The output should be something like:

```json
{
  "id": "e4a07ce7-6634-47f9-8f42-e1f53da7e523",
  "workflowdata": {
    "name": "Yoda",
    "language": "English",
    "avg_food_price": 5.228,
    "city": "New York",
    "state_code": "NY",
    "reference_currency": "GBP",
    "greeting": "Greetings from Serverless Workflow, Yoda!"
  }
}
```

```shell
curl -X POST -H "Content-Type: application/json" -d '{"workflowdata" : {"name" : "Yoda", "country":"United States", "reference_currency":"GBP"}}' http://localhost:8080/greeting
```

The output should be something like:

```json
{
  "id": "559d4b7a-56f3-4377-974c-386cc1e2551f",
  "workflowdata": {
    "name": "Yoda",
    "language": "Spanish",
    "avg_food_price": 112.03943076857142,
    "city": "Barcelona",
    "state_code": "NY",
    "reference_currency": "BRL",
    "greeting": "Saludos desde Serverless Workflow, Yoda!"
  }
}
```
