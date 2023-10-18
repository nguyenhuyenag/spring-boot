package com;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Tạo một WebClient để thực hiện yêu cầu HTTP
		WebClient webClient = WebClient.builder() //
				.baseUrl("http://localhost:8080") //
				.build();

		// Thực hiện yêu cầu HTTP và tái thử khi có lỗi
		int maxRetries = 3;

		Retry retrySpec = Retry.backoff(maxRetries, Duration.ofSeconds(5)).doBeforeRetry(retrySignal -> {
			// Log retry information here
			System.out.println("Retrying... (" + retrySignal.totalRetries() + ")");
		});

		Mono<String> response = webClient.get() //
				.uri("/slow-service") //
				.retrieve() //
				.bodyToMono(String.class) //
				// .retry(maxRetries)
				.retryWhen(retrySpec);

		// Subscribe để thực hiện yêu cầu
		response.subscribe( //
				result -> System.out.println("Kết quả: " + result), //
				error -> System.err.println("Lỗi: " + error.getMessage()) //
		);
	}

}
