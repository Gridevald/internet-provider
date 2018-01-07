package by.training.provider.command.util;

public class ContractValidator {

    public static boolean isContractValid(Integer contract) {
        return contract < 999_999_999 && contract > 100_000_000;
    }
}
