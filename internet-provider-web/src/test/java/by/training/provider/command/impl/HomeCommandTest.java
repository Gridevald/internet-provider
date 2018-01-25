package by.training.provider.command.impl;

import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class HomeCommandTest {

    @Mock
    private HttpServletRequest request;
    private HomeCommand command;

    @Before
    public void setup() {
        command = new HomeCommand();
    }

    @Test
    public void testExecute() {
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.HOME, response.getUrl());
    }
}
