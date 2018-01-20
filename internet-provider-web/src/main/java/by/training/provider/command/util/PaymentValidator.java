package by.training.provider.command.util;

import by.training.provider.entity.Payment;

import java.math.BigDecimal;

public class PaymentValidator {

    /**
     * Checks if payment sum more then zero.
     *
     * @param payment to validate.
     * @return true if valid, false otherwise.
     */
    public static boolean isValidPayment(Payment payment) {

        BigDecimal sum = payment.getSum();
        return sum.compareTo(BigDecimal.ZERO) > 0;
    }
}
