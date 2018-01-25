package by.training.provider.command.impl.user;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.Payment;
import by.training.provider.entity.User;
import by.training.provider.service.PaymentService;
import by.training.provider.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MakePaymentCommandTest {

    private static final String VALID_SUM = "1.00";
    private static final BigDecimal VALID_BALANCE = BigDecimal.TEN;
    private static final int VALID_ID = 1;
    private static final String INVALID_SUM = "0";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private User user;
    @Mock
    private Payment payment;
    @InjectMocks
    private MakePaymentCommand command;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParamNames.PERSON)).thenReturn(user);
        when(request.getParameter(ParamNames.SUM)).thenReturn(VALID_SUM);
        when(user.getBalance()).thenReturn(VALID_BALANCE);
        when(user.getId()).thenReturn(VALID_ID);
    }

    @Test
    public void shouldReturnForwardSetPaymentWhenInvalidPayment() {
        when(request.getParameter(ParamNames.SUM)).thenReturn(INVALID_SUM);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.SET_PAYMENT, response.getUrl());
    }

    @Test
    public void shouldReturnRedirectSuccessUserWhenValidData() throws DataException {
        doNothing().when(userService).updateUser(user);
        doNothing().when(paymentService).addPayment(payment);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(UrlEnum.SUCCESS_USER_ACTION_COMMAND, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnUserService() throws DataException {
        doThrow(new DataException()).when(userService).updateUser(user);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataExceptionOnPaymentService() throws DataException {
        doNothing().when(userService).updateUser(user);
        doThrow(new DataException()).when(paymentService).addPayment(any());

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }
}
