package by.training.provider.command.util;

import org.junit.Assert;
import org.junit.Test;

public class ContractValidatorTest {

    @Test
    public void shouldReturnFalseWhenPassingNumberLessThanRange() {
        Assert.assertFalse(ContractValidator.isContractValid(99_999_999));
    }

    @Test
    public void shouldReturnFalseWhenPassingNumberMoreThanRange() {
        Assert.assertFalse(ContractValidator.isContractValid(1_000_000_000));
    }

    @Test
    public void shouldReturnTrueWhenPassingNumberInRange() {
        Assert.assertTrue(ContractValidator.isContractValid(555_555_555));
    }
}
