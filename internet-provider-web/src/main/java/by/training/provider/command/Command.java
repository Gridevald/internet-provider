package by.training.provider.command;

import by.training.provider.dto.PageResponse;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    PageResponse execute(HttpServletRequest request);
}
