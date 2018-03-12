package br.com.facility.test.domain.model;

import br.com.facility.domain.model.Username;
import org.junit.Assert;
import org.junit.Test;

public class UsernameTest {

	@Test
	public void giveAValidUsernameThenReturnANewObject() {
		String strUsername = "bharaujo_1";
		Username username = new Username(strUsername);

		Assert.assertEquals(strUsername, username.getUsername());
	}

	@Test(expected = RuntimeException.class)
	public void giveAInvalidUsernameThenThrowAException() {
		String strUsername = "bhr@ujo";
		new Username(strUsername);
	}

	@Test(expected = RuntimeException.class)
	public void giveAEmptyUsernameThenThrowAException() {
		new Username("");
	}

	@Test(expected = RuntimeException.class)
	public void giveANullUsernameThenThrowAException() {
		new Username(null);
	}
}
