package by.training.provider.controller;

import by.training.provider.command.Command;
import by.training.provider.command.CommandFactory;

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

    /**
     * Reads request URL, finds appropriate command and executes
     * page response (response URL and response method).
     *
     * @param request HttpRequest.
     * @param response HttpResponse.
     * @throws ServletException if an input or output error occurred.
     * @throws IOException if request cannot be handled.
     */
    private void doCommand(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String actionName = request.getServletPath();

        CommandFactory factory = new CommandFactory();
        Command currentCommand = factory.getAction(actionName);

        UrlResponse data = currentCommand.execute(request);
        ResponseMethod method = data.getMethod();
        String url = data.getUrl().getPage();

        if (ResponseMethod.FORWARD.equals(method)) {
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + url);
        }
    }
}
