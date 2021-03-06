package by.training.provider.command.util;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Customer;
import by.training.provider.service.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidator {

    private UserService userService;

    /**
     * Checks:
     * - email by pattern;
     * - if customer with given email exists;
     *
     * @param userService UserService.
     */
    public CustomerValidator(UserService userService) {
        this.userService = userService;
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^@]+@[^@]+\\.[^@]+");

    public boolean isValidCustomer(Customer customer) throws DataException {

        String email = customer.getEmail();
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        if (!emailMatcher.matches()) {
            return false;
        }

        if (userService.isUserExists(email)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if given passwords are equals.
     *
     * @param pass1 first password.
     * @param pass2 second password.
     * @return true if passwords are equals, false otherwise.
     */
    public boolean isPasswordsEquals(String pass1, String pass2) {
        return pass1 != null && pass2 != null && pass1.equals(pass2);
    }
}
