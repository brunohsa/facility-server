package br.com.facility.test.integration;

import br.com.facility.repository.ExpenseRepository;
import br.com.facility.repository.UserRepository;
import br.com.facility.test.fixture.ExpenseFixture;
import br.com.facility.test.fixture.UserFixture;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class PopulateDatasIT {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected ExpenseRepository expenseRepository;

	@Before
	public void populate() {
		populateUsers();
		populateExpenses();
	}

	private void populateUsers() {
		UserFixture userFixture = new UserFixture();
		userFixture.getAll().forEach(user -> userRepository.save(user));
	}

	private void populateExpenses() {
		ExpenseFixture expenseFixture = new ExpenseFixture();
		expenseFixture.getAll().forEach(expense -> expenseRepository.save(expense));
	}
}
