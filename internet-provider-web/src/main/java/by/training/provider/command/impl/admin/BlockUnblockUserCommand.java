package by.training.provider.command.impl.admin;

import by.training.provider.command.Command;
import by.training.provider.command.ParamNames;
import by.training.provider.command.enums.PageEnum;
import by.training.provider.dao.exception.DataException;
import by.training.provider.dto.PageResponse;
import by.training.provider.dto.ResponseMethod;
import by.training.provider.entity.User;
import by.training.provider.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BlockUnblockUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BLOCK_STATUS = "1";
    private UserService service;

    public BlockUnblockUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public PageResponse execute(HttpServletRequest request) {

        String userIdStr = request.getParameter(ParamNames.USER_TO_BLOCK);
        Integer userId = Integer.valueOf(userIdStr);

        try {
            User user = service.getUserById(userId);
            String block = request.getParameter(ParamNames.BLOCK);

            if (BLOCK_STATUS.equals(block)) {
                user.setIsBlocked((byte) 1);
            } else {
                user.setIsBlocked((byte) 0);
            }

            service.updateUser(user);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new PageResponse(ResponseMethod.FORWARD, PageEnum.ERROR);
        }

        return new PageResponse(ResponseMethod.REDIRECT, PageEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
