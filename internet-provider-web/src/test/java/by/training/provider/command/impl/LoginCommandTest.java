package by.training.provider.command.impl;


import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.command.enums.RoleEnum;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void shouldReturnForwardLoginWhenGuest() {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(RoleEnum.GUEST.getRole());

        LoginCommand command = new LoginCommand();
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.LOGIN, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardUserWhenUser() {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(RoleEnum.USER.getRole());

        LoginCommand command = new LoginCommand();
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.USER, response.getPageUrl());
    }

    @Test
    public void shouldReturnRedirectSuccessAdminWhenAdmin() {
        when(session.getAttribute(ParamNames.ROLE)).thenReturn(RoleEnum.ADMIN.getRole());

        LoginCommand command = new LoginCommand();
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(PageEnum.SUCCESS_ADMIN_ACTION_COMMAND, response.getPageUrl());
    }
}
