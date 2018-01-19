package by.training.provider.command.impl;

import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class SuccessOrderCommandTest {

    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnForwardSuccessOrder() {
        SuccessOrderCommand command = new SuccessOrderCommand();
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.SUCCESS_ORDER, response.getUrl());
    }
}
