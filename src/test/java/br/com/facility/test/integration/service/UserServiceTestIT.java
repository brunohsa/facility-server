package br.com.facility.test.integration.service;

import br.com.facility.model.User;
import br.com.facility.service.UserService;
import br.com.facility.test.fixture.UserFixture;
import br.com.facility.test.integration.BaseIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTestIT extends BaseIntegrationTest {

	@Autowired
	private UserService userService;

	@Test
	public void findUserTest() {
		User userFixture = UserFixture.JULIAN_EDELMAN;

		User user = userService.findByUserName(userFixture.getUsername());
		Assert.assertNotNull(user);
		Assert.assertEquals(userFixture.getUsername(), user.getUsername());
		Assert.assertEquals(userFixture.getName(), user.getName());
		Assert.assertEquals(userFixture.getEmail(), user.getEmail());
		Assert.assertEquals(userFixture.getLastName(), user.getLastName());
		Assert.assertEquals(userFixture.getPassword(), user.getPassword());
	}

	@Test
	public void createNewUserTest() {
		User newUser = new User("Test", "TestLastname", "testing", "testing", "test@test.com");
		userService.save(newUser);

		User user = userService.findByUserName(newUser.getUsername());
		Assert.assertNotNull(user);
	}
}
