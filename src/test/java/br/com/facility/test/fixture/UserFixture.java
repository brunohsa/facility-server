package br.com.facility.test.fixture;

import br.com.facility.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserFixture {

	public static final User JULIAN_EDELMAN = new User("Julian", "Edelman", "je11", "JE11", "julian@pats.com");
	public static final User BRUNO_ARAUJO = new User("Bruno", "Araujo", "bharaujo", "bharaujo", "bharaujo@test.com");

	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		users.add(JULIAN_EDELMAN);
		users.add(BRUNO_ARAUJO);

		return users;
	}
}
