package controller;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);

    Logger LOGGER = Logger.getRootLogger();
}
