package by.training.provider.command;

import by.training.provider.command.impl.*;
import by.training.provider.command.impl.admin.*;
import by.training.provider.command.impl.user.*;
import by.training.provider.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, Command> actions = new HashMap<String, Command>() {
        {
            put("/home.do", new HomeCommand());
            put("/changeLang.do", new LanguageCommand());
            put("/plans.do", new PlansCommand(new PlanService()));
            put("/login.do", new LoginCommand());
            put("/register.do", new RegisterCommand(new PlanService()));
            put("/main.do", new MainCommand(new AdminService(),
                    new UserService(),
                    new CustomerService(),
                    new PlanService()));
            put("/makeOrder.do", new MakeOrderCommand(new CustomerService(),
                    new PlanService()));
            put("/successOrder.do", new SuccessOrderCommand());
            put("/logout.do", new LogoutCommand());

            //user
            put("/user/choosePlan.do", new ChoosePlanCommand(new PlanService()));
            put("/user/changePlan.do", new ChangePlanCommand(new UserService()));
            put("/user/setPayment.do", new SetPaymentCommand());
            put("/user/makePayment.do", new MakePaymentCommand(new UserService(),
                    new PaymentService()));
            put("/user/successUserAction.do", new SuccessUserActionCommand(new UserService()));

            //admin
            put("/admin/blockUnblockUser.do", new BlockUnblockUserCommand(new UserService()));
            put("/admin/setPlan.do", new SetPlanCommand());
            put("/admin/addPlan.do", new AddPlanCommand(new PlanService()));
            put("/admin/setUser.do", new SetUserCommand(new CustomerService()));
            put("/admin/approveUser.do", new ApproveUserCommand(new ApproveUserService(),
                    new CustomerService()));
            put("/admin/successAdminAction.do", new SuccessAdminActionCommand(new UserService(),
                    new PlanService(),
                    new CustomerService()));
        }
    };

    public Command getAction(String actionName) {
        Command command = actions.get(actionName);
        if (command == null) {
            command = new HomeCommand();
        }
        return command;
    }
}
