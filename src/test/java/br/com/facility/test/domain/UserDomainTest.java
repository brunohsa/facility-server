package br.com.facility.test.domain;

import br.com.facility.domain.UserDomain;
import br.com.facility.domain.model.Password;
import br.com.facility.domain.model.Username;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class UserDomainTest {

    @Test
    public void giveAUserWithAllFieldsThenReturnNewUser() {

        Username username = EasyMock.createMock(Username.class);
        Password password = EasyMock.createMock(Password.class);
        UserDomain user = new UserDomain(username, password);
        Assert.assertNotNull(user);
    }

    @Test(expected = RuntimeException.class)
    public void giveANullUsernameThenThrowAnException() {

        Username username = null;
        Password password = EasyMock.createMock(Password.class);
        new UserDomain(username, password);
    }

    @Test(expected = RuntimeException.class)
    public void giveANullPasswordThenThrowAnException() {

        Username username = EasyMock.createMock(Username.class);
        Password password = null;
        new UserDomain(username, password);
    }
}
