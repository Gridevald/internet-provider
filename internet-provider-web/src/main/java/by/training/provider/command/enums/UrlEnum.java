package by.training.provider.command.enums;

public enum UrlEnum {
    ADMIN("/WEB-INF/jsp/admin/admin.jsp"),
    CHOOSE_PLAN("/WEB-INF/jsp/user/choosePlan.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp"),
    HOME("/WEB-INF/jsp/home.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp"),
    LOGIN_COMMAND("/login.do"),
    PLANS("/WEB-INF/jsp/plans.jsp"),
    REGISTER("/WEB-INF/jsp/register.jsp"),
    SET_PAYMENT("/WEB-INF/jsp/user/setPayment.jsp"),
    SET_PLAN("/WEB-INF/jsp/admin/setPlan.jsp"),
    SET_USER("/WEB-INF/jsp/admin/setUser.jsp"),
    SUCCESS_ADMIN_ACTION_COMMAND("/admin/successAdminAction.do"),
    SUCCESS_ORDER("/WEB-INF/jsp/successOrder.jsp"),
    SUCCESS_ORDER_COMMAND("/successOrder.do"),
    SUCCESS_USER_ACTION_COMMAND("/user/successUserAction.do"),
    USER("/WEB-INF/jsp/user/user.jsp");

    UrlEnum(String page) {
        this.page = page;
    }

    private String page;

    public String getPage() {
        return page;
    }
}
