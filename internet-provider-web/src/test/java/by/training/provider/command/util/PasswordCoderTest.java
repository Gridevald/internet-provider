package by.training.provider.command.util;

import org.junit.Assert;
import org.junit.Test;

public class PasswordCoderTest {

    private static final String VALUE = "Value to hash";
    private static final String EXPECTED_HASH = "610a49ea53daa33e810c14a06790ae016c615cf2";

    @Test
    public void shouldReturnCorrectHash() {
        String actualHash = PasswordCoder.hashSha(VALUE);
        Assert.assertEquals(EXPECTED_HASH, actualHash);
    }
}
