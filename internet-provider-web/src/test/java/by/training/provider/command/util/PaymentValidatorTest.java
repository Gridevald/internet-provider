package by.training.provider.command.util;

import by.training.provider.entity.Payment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentValidatorTest {

    private static final BigDecimal LESS_THAN_ZERO = new BigDecimal(-1);
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal MORE_THAN_ZERO = BigDecimal.ONE;

    @Mock
    private Payment payment;

    @Test
    public void shouldReturnTrueWhenSumMoreThenZero() {
        when(payment.getSum()).thenReturn(MORE_THAN_ZERO);
        Assert.assertTrue(PaymentValidator.isValidPayment(payment));
    }

    @Test
    public void shouldReturnFalseWhenSumLessThenZero() {
        when(payment.getSum()).thenReturn(LESS_THAN_ZERO);
        Assert.assertFalse(PaymentValidator.isValidPayment(payment));
    }

    @Test
    public void shouldReturnFalseWhenSumIsZero() {
        when(payment.getSum()).thenReturn(ZERO);
        Assert.assertFalse(PaymentValidator.isValidPayment(payment));
    }
}
