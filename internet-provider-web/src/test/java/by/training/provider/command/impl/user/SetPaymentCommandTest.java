package by.training.provider.command.impl.user;

import by.training.provider.command.enums.PageEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class SetPaymentCommandTest {

    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnForwardSetPayment() {
        SetPaymentCommand command = new SetPaymentCommand();
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.SET_PAYMENT, response.getPageUrl());
    }
}
