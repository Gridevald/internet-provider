package by.training.provider.command.impl.user;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.User;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SuccessUserActionCommand implements Command {

    private UserService service;

    public SuccessUserActionCommand(UserService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParamNames.PERSON);
        Integer id = user.getId();

        try {
            user = service.getEagerUser(id);
        } catch (DataException e) {
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        session.setAttribute(ParamNames.PERSON, user);

        return new PageResponse(ResponseMethod.FORWARD, PageEnum.USER);
    }
}
