package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.UrlEnum;
import by.training.provider.controller.ResponseMethod;
import by.training.provider.controller.UrlResponse;
import by.training.provider.dao.exception.DataException;
import by.training.provider.entity.User;
import by.training.provider.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SuccessUserActionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private UserService service;

    public SuccessUserActionCommand(UserService service) {
        this.service = service;
    }

    /**
     * Sets updated user to session and returns main user page url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParamNames.PERSON);
        Integer id = user.getId();

        try {
            user = service.getEagerUser(id);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        session.setAttribute(ParamNames.PERSON, user);

        return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.USER);
    }
}
