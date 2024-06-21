Checkout (Invoking app)
``` bash
cd checkout
mvn clean install
dapr run --app-id checkout --resources-path ./components --log-level debug -- java -jar target/CheckoutService-0.0.1-SNAPSHOT.jar
```


Order processor (receiving app)
``` bash
cd order-processor
mvn clean install
java -jar target/OrderProcessingService-0.0.1-SNAPSHOT.jar
```

See lack of traces in order processor and then to see traces, use Postman or another client and POST to `http://localhost:3500/v1.0/bindings/http-trace`

Postman:
``` JSON
{
    "operation":"post",
    "metadata": {
        "Content-Type":"application/json"
    },
    "data": {"orderId": 10}
}
```
