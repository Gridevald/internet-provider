package by.training.provider.command.impl;

import by.training.provider.command.enums.PageEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    @Test
    public void shouldReturnForwardHome() {
        when(request.getSession()).thenReturn(session);

        LogoutCommand command = new LogoutCommand();
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.HOME, response.getPageUrl());
    }
}
