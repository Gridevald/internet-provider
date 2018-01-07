package by.training.provider.command.enums;

public enum RoleEnum {
    GUEST("guest"),
    USER("user"),
    ADMIN("admin");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
