package by.training.provider.command;

import by.training.provider.controller.UrlResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Command executes appropriate actions and returns url
 * to forward/redirect.
 */
public interface Command {

    UrlResponse execute(HttpServletRequest request);
}
