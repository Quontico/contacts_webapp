package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.ContactDAO;
import util.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchForm implements ActionCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Connection connection = DbUtil.getConnection();

        ContactDAO contactDAO = new ContactDAO(connection);

        try {
            connection.commit();

            List<String> genders = contactDAO.getAllGenders();
            List<String> maritals = contactDAO.getAllMaritals();

            request.setAttribute("genders", genders);
            request.setAttribute("maritals", maritals);

        } catch (SQLException ex) {
            LOGGER.error("SQLException occured" + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Connection is not rollbacked: " + e);
            }
        }
        return PageURL.SEARCH;
    }
}
