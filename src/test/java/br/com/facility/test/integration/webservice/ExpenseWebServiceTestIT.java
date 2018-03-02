package br.com.facility.test.integration.webservice;

import br.com.facility.json.response.ExpenseResponse;
import br.com.facility.json.response.LoginResponse;
import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.test.fixture.ExpenseFixture;
import br.com.facility.test.fixture.UserFixture;
import br.com.facility.test.integration.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExpenseWebServiceTestIT extends BaseIntegrationTest {

	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	public void giveAValidIdOfAExpenseThenReturnThisOne() {
		User userFixture = UserFixture.BRUNO_ARAUJO;
		String token = authenticateUser(userFixture.getUsername(), userFixture.getPassword());

		Expense cellPhoneFixure = ExpenseFixture.CELL_PHONE_BRUNO;

		HttpHeaders headers = new HttpHeaders();
		headers.set("token", token);

		HttpEntity<Object> entity = new HttpEntity<>(headers);
		String url = getBaseUrl() + "/expenses/" + cellPhoneFixure.getId();

		ResponseEntity<ExpenseResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, ExpenseResponse.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());

		ExpenseResponse responseExpense = response.getBody();

		Assert.assertEquals(cellPhoneFixure.getExpirationDate(), responseExpense.getExpirationDate());
		Assert.assertEquals(cellPhoneFixure.getPaymentDate(), responseExpense.getPaymentDate());
		Assert.assertEquals(cellPhoneFixure.getPaymentDate(), responseExpense.getPaymentDate());
		Assert.assertEquals(cellPhoneFixure.getDescription(), responseExpense.getDescription());
		Assert.assertEquals(cellPhoneFixure.getObservation(), responseExpense.getObservation());
		Assert.assertEquals(cellPhoneFixure.getPaymentType(), responseExpense.getPaymentType());
		Assert.assertEquals(cellPhoneFixure.getStatus(), responseExpense.getStatus());
		Assert.assertEquals(cellPhoneFixure.getReleaseDate(), responseExpense.getReleaseDate());
		Assert.assertTrue(cellPhoneFixure.getValue().compareTo(responseExpense.getValue()) == 0);
	}
}
