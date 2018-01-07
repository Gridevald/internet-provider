package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.util.PaymentValidator;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.Payment;
import by.training.provider.entity.User;
import by.training.provider.service.PaymentService;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class MakePaymentCommand implements Command {

    private UserService userService;
    private PaymentService paymentService;

    public MakePaymentCommand(UserService userService,
                              PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParamNames.PERSON);
        String addSumStr = request.getParameter("sum");
        BigDecimal balance = user.getBalance();
        BigDecimal addSum = new BigDecimal(addSumStr);
        balance = balance.add(addSum);
        user.setBalance(balance);

        Payment payment = new Payment();
        payment.setSum(addSum);
        payment.setDate(new java.util.Date());
        Integer userId = user.getId();
        payment.setUserId(userId);

        if (PaymentValidator.isValidPayment(payment)) {
            try {
                userService.updateUser(user);
                paymentService.addPayment(payment);
            } catch (DataException e) {
                return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
            }
            return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_USER_ACTION_COMMAND);
        }

        request.setAttribute("sumError", 1);
        return new PageResponse(ResponseMethod.FORWARD, PageEnum.SET_PAYMENT);
    }
}
