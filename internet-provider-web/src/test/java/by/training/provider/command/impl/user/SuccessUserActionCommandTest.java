package by.training.provider.command.impl.user;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SuccessUserActionCommandTest {

    private static final int VALID_ID = 1;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    @Mock
    private UserService service;
    @InjectMocks
    private SuccessUserActionCommand command;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ParamNames.PERSON)).thenReturn(user);
        when(user.getId()).thenReturn(VALID_ID);
    }

    @Test
    public void shouldReturnForwardUserWhenNoExceptions() {
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.USER, response.getPageUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataException() throws DataException {
        when(service.getEagerUser(VALID_ID)).thenThrow(new DataException());

        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.ERROR, response.getPageUrl());
    }
}
