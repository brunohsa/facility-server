package br.com.facility.test.integration.webservice;

import br.com.facility.model.User;
import br.com.facility.test.fixture.UserFixture;
import br.com.facility.test.integration.BaseIntegrationTest;
import br.com.facility.webservice.model.request.LoginRequest;
import br.com.facility.webservice.model.response.LoginResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthenticationWebServiceTestIT extends BaseIntegrationTest {

	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testAuthenticationUser() {
		String url = getBaseUrl() + "/login";

		User julian = UserFixture.JULIAN_EDELMAN;
		LoginRequest request = new LoginRequest(julian.getUsername(), julian.getPassword());
		ResponseEntity<LoginResponse> response = restTemplate.postForEntity(url, request, LoginResponse.class);

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

		LoginResponse login = response.getBody();
		Assert.assertNotNull(login);
		Assert.assertNotNull(login.getToken());
		Assert.assertNotNull(login.getUser());
		Assert.assertEquals(julian.getName(), login.getUser().getName());
		Assert.assertEquals(julian.getLastName(), login.getUser().getLastName());
		Assert.assertEquals(julian.getUsername(), login.getUser().getUsername());
		Assert.assertEquals(julian.getEmail(), login.getUser().getEmail());
	}
}
