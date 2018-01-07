package by.training.provider.command.util;

import by.training.provider.entity.Payment;

import java.math.BigDecimal;

public class PaymentValidator {

    public static boolean isValidPayment(Payment payment) {

        BigDecimal sum = payment.getSum();
        return sum.compareTo(BigDecimal.ZERO) <= 0;
    }
}
