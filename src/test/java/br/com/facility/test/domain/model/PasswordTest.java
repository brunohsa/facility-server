package br.com.facility.test.domain.model;

import br.com.facility.domain.model.Password;
import org.junit.Assert;
import org.junit.Test;

public class PasswordTest {

    @Test
    public void giveValidPasswordThenReturnNewPassword() {
        String password = "teste";
        Password newPassword = new Password(password);

        Assert.assertNotNull(newPassword);
        Assert.assertEquals(password, newPassword.getPassword());
    }

    @Test(expected = RuntimeException.class)
    public void giveEmptyPasswordThenThrowAnException() {
        String password = "";
        new Password(password);
    }

    @Test(expected = RuntimeException.class)
    public void giveNullPasswordThenThrowAnException() {
        String password = null;
        new Password(password);
    }
}
