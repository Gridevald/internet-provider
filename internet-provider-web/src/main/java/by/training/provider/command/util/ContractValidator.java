package by.training.provider.command.util;

public class ContractValidator {

    /**
     * Validates contract number. Should be between 100 000000
     * and 999 999 999.
     */
    public static boolean isContractValid(Integer contract) {
        return contract != null && contract < 999_999_999 && contract > 100_000_000;
    }
}
