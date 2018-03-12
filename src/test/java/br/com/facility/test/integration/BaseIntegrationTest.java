package br.com.facility.test.integration;

import br.com.facility.webservice.model.request.LoginRequest;
import br.com.facility.webservice.model.response.LoginResponse;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class BaseIntegrationTest extends PopulateDatasIT {

	@LocalServerPort
	private int port;

	private final RestTemplate restTemplate = new RestTemplate();

	private final String BASE_URL = "http://localhost:";

	public String authenticateUser(String username, String password) {
		String url = getBaseUrl() + "/login";
		LoginRequest request = new LoginRequest(username, password);
		ResponseEntity<LoginResponse> response = restTemplate.postForEntity(url, request, LoginResponse.class);
		return response.getBody().getToken();
	}

	public String getBaseUrl() {
		return BASE_URL + port;
	}
}
