package by.training.provider.command.util;

import org.junit.Assert;
import org.junit.Test;

public class ContractValidatorTest {

    private static final int LESS_THAN_RANGE = 99_999_999;
    private static final int MORE_THAN_RANGE = 1_000_000_000;
    private static final int IN_RAGE = 555_555_555;

    @Test
    public void shouldReturnFalseWhenPassingNumberLessThanRange() {
        Assert.assertFalse(ContractValidator.isContractValid(LESS_THAN_RANGE));
    }

    @Test
    public void shouldReturnFalseWhenPassingNumberMoreThanRange() {
        Assert.assertFalse(ContractValidator.isContractValid(MORE_THAN_RANGE));
    }

    @Test
    public void shouldReturnTrueWhenPassingNumberInRange() {
        Assert.assertTrue(ContractValidator.isContractValid(IN_RAGE));
    }

    @Test
    public void shouldReturnFalseWhenPassingNull() {
        Assert.assertFalse(ContractValidator.isContractValid(null));
    }
}
