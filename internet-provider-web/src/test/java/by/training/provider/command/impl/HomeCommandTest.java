package by.training.provider.command.impl;

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

@RunWith(MockitoJUnitRunner.class)
public class HomeCommandTest {

    @Mock
    HttpServletRequest request;

    HomeCommand command;

    @Before
    public void setUp() {
        command = new HomeCommand();
    }

    @Test
    public void testExecute() {
        PageResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(PageEnum.HOME, response.getPageUrl());
    }
}
