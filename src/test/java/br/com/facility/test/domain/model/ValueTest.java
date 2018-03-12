package br.com.facility.test.domain.model;

import br.com.facility.domain.model.Value;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ValueTest {

    @Test
    public void giveValidValueThenReturnANewValue() {
        BigDecimal value = BigDecimal.TEN;
        Value newValue = new Value(value);
        Assert.assertNotNull(newValue);
        Assert.assertTrue(value.compareTo(newValue.getValue()) == 0);
    }

    @Test(expected = RuntimeException.class)
    public void giveZeroValueThenThrowAnException() {
        new Value(BigDecimal.ZERO);
    }

    @Test(expected = RuntimeException.class)
    public void givenNegativeValueThenThrowAnException() {
        BigDecimal value = new BigDecimal(-250.00);
        new Value(value);
    }
}
