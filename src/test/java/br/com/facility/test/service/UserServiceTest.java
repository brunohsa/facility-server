package br.com.facility.test.service;

import org.junit.Assert;
import org.junit.Test;


public class UserServiceTest {

	private static final String HELLO_WORLD = "helloworld" ;
	
	@Test
	public void helloWorld() {
		Assert.assertEquals(HELLO_WORLD, "helloworld");
	}
}
