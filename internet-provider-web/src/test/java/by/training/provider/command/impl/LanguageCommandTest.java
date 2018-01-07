package by.training.provider.command.impl;

import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LanguageCommandTest {

    private static final String LANG = "ru";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(ParamNames.LANG_TO_SET)).thenReturn(LANG);
    }

    @Test
    public void shouldReturnForwardHome() {
        LanguageCommand command = new LanguageCommand();
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.HOME, response.getPageUrl());

        verify(request).getParameter(ParamNames.LANG_TO_SET);
        verify(request).getSession();
        verify(session).setAttribute(ParamNames.UI_LANG, request.getParameter(ParamNames.LANG_TO_SET));
    }
}
