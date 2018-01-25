package by.training.provider.command.impl;

import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.service.PlanService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private PlanService service;
    @InjectMocks
    private RegisterCommand command;

    @Test
    public void shouldReturnForwardPlans() {
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.REGISTER, response.getUrl());
    }

    @Test
    public void shouldReturnForwardErrorWhenDataError() throws DataException {
        when(service.getAllPlans()).thenThrow(new DataException());
        UrlResponse response = command.execute(request);

        Assert.assertEquals(ResponseMethod.FORWARD, response.getMethod());
        Assert.assertEquals(UrlEnum.ERROR, response.getUrl());
    }
}
