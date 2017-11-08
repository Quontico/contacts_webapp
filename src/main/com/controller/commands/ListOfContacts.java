package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.ContactDAO;
import util.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ListOfContacts implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Connection connection = DbUtil.getConnection();

        ContactDAO contactDAO = new ContactDAO(connection);

        int page = 1;
        int number = 10;
        int amount;
        int pages;

        try {
            connection.commit();

            if (request.getParameter("Page") != null)
                page = Integer.parseInt(request.getParameter("Page"));
            if (request.getParameter("Number") != null)
                number = Integer.parseInt(request.getParameter("Number"));

            amount = contactDAO.getNumberOfContacts();
            pages = amount / number;
            if (amount % number != 0) {
                pages++;
            }
            request.setAttribute("number", number);
            request.setAttribute("page", page);
            request.setAttribute("pages", pages);
            request.setAttribute("records", amount);
            request.setAttribute("contacts", contactDAO.getAllContacts((page - 1) * number, page * number));
        } catch (SQLException ex) {
            LOGGER.error("There is SQLException in ListOfContacts class: " + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Connection is not rollbacked in ListOfContacts class: " + e);
            }
        }

        return PageURL.LIST_CONTACT;
    }
}
