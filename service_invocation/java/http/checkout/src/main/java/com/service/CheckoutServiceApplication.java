package com.service;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.InvokeBindingRequest;
import io.dapr.utils.TypeRef;
import reactor.core.publisher.Mono;

public class CheckoutServiceApplication {
	// private static final HttpClient httpClient = HttpClient.newBuilder()
	// 		.version(HttpClient.Version.HTTP_2)
	// 		.connectTimeout(Duration.ofSeconds(10))
	// 		.build();

	//private static final String DAPR_HTTP_PORT = System.getenv().getOrDefault("DAPR_HTTP_PORT", "3500");
	private static final String httpBindingName = "http-trace";

	public static void main(String[] args) throws InterruptedException, IOException {

		String jsonText = "{\"orderId\": 10}";

		// JSONObject j = new JSONObject();
		// //data?
		// j.put("orderId", "10");
		
		System.out.println("Before invoke binding");
		InvokeBindingRequest request = new InvokeBindingRequest(httpBindingName, "post");
		request.setData(jsonText);

		Map<String, String> metadata = new HashMap<String, String>();
    metadata.put("Content-Type", "application/json");
		request.setMetadata(metadata);

		try (DaprClient client = new DaprClientBuilder().build()) {

			System.out.println("Trying to call binding");
			client.invokeBinding(request, TypeRef.get(String.class)).block();
			//System.out.println("After calling binding: " + ret.toString());
		
		} catch (Exception e) {
			System.out.println("Exception: "+ e);
			//throw e;
		}
		// String dapr_url = "http://localhost:" + DAPR_HTTP_PORT + "/orders";
		// for (int i=1; i<=20; i++) {
		// 	int orderId = i;
		// 	JSONObject obj = new JSONObject();
		// 	obj.put("orderId", orderId);

		// 	HttpRequest request = HttpRequest.newBuilder()
		// 			.POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
		// 			.uri(URI.create(dapr_url))
		// 			.header("Content-Type", "application/json")
		// 			.header("dapr-app-id", "order-processor")
		// 			.build();

		// 	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		// 	System.out.println("Order passed: "+ orderId);
		// 	TimeUnit.MILLISECONDS.sleep(1000);
		//}
	}
}
