package by.training.provider.controller;

import by.training.provider.command.Command;
import by.training.provider.command.CommandFactory;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doCommand(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doCommand(request, response);
    }

    private void doCommand(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String actionName = request.getServletPath();

        CommandFactory factory = new CommandFactory();
        Command currentCommand = factory.getAction(actionName);

        PageResponse data = currentCommand.execute(request);
        ResponseMethod method = data.getMethod();
        String pageUrl = data.getPageUrl().getPage();

        if (ResponseMethod.FORWARD.equals(method)) {
            request.getRequestDispatcher(pageUrl).forward(request, response);
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + pageUrl);
        }
    }
}
