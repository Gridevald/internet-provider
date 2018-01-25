package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.command.util.PaymentValidator;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Payment;
import by.training.provider.entity.User;
import by.training.provider.service.PaymentService;
import by.training.provider.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class MakePaymentCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ERROR_OCCURS = 1;
    private UserService userService;
    private PaymentService paymentService;

    public MakePaymentCommand(UserService userService,
                              PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    /**
     * If payment is valid, then updates user with new balance,
     * adds new payment and returns success user action command url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParamNames.PERSON);
        String addSumStr = request.getParameter(ParamNames.SUM);
        BigDecimal balance = user.getBalance();
        BigDecimal addSum = new BigDecimal(addSumStr);
        balance = balance.add(addSum);
        user.setBalance(balance);

        Payment payment = new Payment();
        payment.setSum(addSum);
        payment.setDate(new java.util.Date());
        Integer userId = user.getId();
        payment.setUserId(userId);

        if (!PaymentValidator.isValidPayment(payment)) {
            request.setAttribute(ParamNames.SUM_ERROR, ERROR_OCCURS);
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.SET_PAYMENT);
        }

        try {
            userService.updateUser(user);
            paymentService.addPayment(payment);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }
        return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_USER_ACTION_COMMAND);
    }
}
