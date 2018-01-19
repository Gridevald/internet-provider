package by.training.provider.command.impl.user;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.UrlResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.User;
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

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangePlanCommandTest {

    private static final String VALID_ID = "1";
    private static final User USER = new User();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserService service;
    @InjectMocks
    private ChangePlanCommand command;

    @Before
    public void setup() {
        when(request.getParameter(ParamNames.PLAN_TO_SET)).thenReturn(VALID_ID);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParamNames.PERSON)).thenReturn(USER);
    }

    @Test
    public void shouldReturnRedirectSuccessUserWhenNoException() {
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.REDIRECT, response.getMethod());
        Assert.assertEquals(UrlEnum.SUCCESS_USER_ACTION_COMMAND, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        doThrow(new DataException()).when(service).updateUser(USER);

        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }
}
