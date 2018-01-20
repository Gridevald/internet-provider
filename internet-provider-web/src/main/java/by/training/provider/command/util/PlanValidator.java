package by.training.provider.command.util;

import by.training.provider.entity.Plan;

import java.math.BigDecimal;

public class PlanValidator {

    /**
     * Validates Plan:
     * - not empty name;
     * - not empty description;
     * - download and upload speed between 1 and 100;
     * - price more then 0.
     */
    public static boolean isValidPlan(Plan plan) {

        String name = plan.getName();
        if (name == null || name.isEmpty()) {
            return false;
        }

        String description = plan.getDescription();
        if (description == null || description.isEmpty()) {
            return false;
        }

        Integer downloadSpeed = plan.getDownloadSpeed();
        if (downloadSpeed < 1 || downloadSpeed > 100) {
            return false;
        }

        Integer uploadSpeed = plan.getUploadSpeed();
        if (uploadSpeed < 1 || uploadSpeed > 100) {
            return false;
        }

        BigDecimal price = plan.getPrice();
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return true;
    }
}
