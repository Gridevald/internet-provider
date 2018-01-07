package by.training.provider.command.util;

import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Customer;
import by.training.provider.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerValidatorTest {

    private static final String PASS_1 = "good";
    private static final String PASS_2 = "good";
    private static final String PASS_3 = "bad";
    private static final String INCORRECT_EMAIL = "incorrectEmail";
    private static final String CORRECT_EMAIL = "correct@mail.com";

    @Mock
    private UserService userService;
    @Mock
    private Customer customer;
    @InjectMocks
    private CustomerValidator validator;

    @Before
    public void setup() {
        when(customer.getEmail()).thenReturn(CORRECT_EMAIL);
    }

    @Test
    public void shouldReturnTrueWhenPasswordsEquals() {
        Assert.assertTrue(validator.isPasswordsEquals(PASS_1, PASS_2));
    }

    @Test
    public void shouldReturnFalseWhenPasswordsNotEquals() {
        Assert.assertFalse(validator.isPasswordsEquals(PASS_1, PASS_3));
    }

    @Test
    public void shouldReturnFalseWhenPassingNullPasswords() {
        Assert.assertFalse(validator.isPasswordsEquals(PASS_1, null));
        Assert.assertFalse(validator.isPasswordsEquals(null, PASS_3));
    }

    @Test
    public void shouldReturnFalseWhenIncorrectEmail() throws DataException {
        when(customer.getEmail()).thenReturn(INCORRECT_EMAIL);
        Assert.assertFalse(validator.isValidCustomer(customer));
    }

    @Test
    public void shouldReturnFalseWhenEmailExists() throws DataException {
        when(userService.isUserExists(CORRECT_EMAIL)).thenReturn(true);
        Assert.assertFalse(validator.isValidCustomer(customer));
        verify(customer).getEmail();
    }

    @Test
    public void shouldReturnTrueWhenCorrectEmailAndEmailNotRegistered() throws DataException {
        when(userService.isUserExists(CORRECT_EMAIL)).thenReturn(false);
        Assert.assertTrue(validator.isValidCustomer(customer));
        verify(customer).getEmail();
    }
}
