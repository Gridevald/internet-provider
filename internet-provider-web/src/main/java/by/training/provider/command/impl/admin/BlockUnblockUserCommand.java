package by.training.provider.command.impl.admin;

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

public class BlockUnblockUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BLOCK_STATUS = "1";
    private static final byte BLOCKED = 1;
    private static final byte UNBLOCKED = 0;
    private UserService service;

    public BlockUnblockUserCommand(UserService service) {
        this.service = service;
    }

    /**
     * Blocks or unblocks user. Returns admin success action command url.
     *
     * @param request HttpServletRequest.
     * @return UrlResponse.
     */
    @Override
    public UrlResponse execute(HttpServletRequest request) {

        String userIdStr = request.getParameter(ParamNames.USER_TO_BLOCK);
        Integer userId = Integer.valueOf(userIdStr);

        try {
            User user = service.getUserById(userId);
            String block = request.getParameter(ParamNames.BLOCK);

            if (BLOCK_STATUS.equals(block)) {
                user.setIsBlocked(BLOCKED);
            } else {
                user.setIsBlocked(UNBLOCKED);
            }

            service.updateUser(user);
        } catch (DataException e) {
            LOGGER.error(e.getMessage());
            return new UrlResponse(ResponseMethod.FORWARD, UrlEnum.ERROR);
        }

        return new UrlResponse(ResponseMethod.REDIRECT, UrlEnum.SUCCESS_ADMIN_ACTION_COMMAND);
    }
}
