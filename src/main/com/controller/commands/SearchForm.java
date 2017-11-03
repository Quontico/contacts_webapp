package controller.commands;

import controller.ActionCommand;
import controller.PageURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchForm implements ActionCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageURL.SEARCH;
    }
}
