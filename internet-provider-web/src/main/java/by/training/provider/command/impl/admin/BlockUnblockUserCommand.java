package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.User;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BlockUnblockUserCommand implements Command {

    private UserService service;

    public BlockUnblockUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String userIdStr = request.getParameter("userToBlock");
        Integer userId = Integer.valueOf(userIdStr);

        try {
            User user = service.getUserById(userId);
            String block = request.getParameter("block");

            if ("1".equals(block)) {
                user.setIsBlocked((byte) 1);
            } else {
                user.setIsBlocked((byte) 0);
            }

            service.updateUser(user);
        } catch (DataException e) {
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
