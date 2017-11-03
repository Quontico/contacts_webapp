package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.ContactDAO;
import util.DbUtil;
import util.EmailTemplates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MailForm implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = DbUtil.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        try {
            connection.commit();

            ArrayList<String> names = new ArrayList<>();
            String[] idsMail = request.getParameterValues("chkbox");
            if (idsMail != null) {
                request.setAttribute("emails", contactDAO.findEmails(idsMail));
                for (String id : idsMail
                        ) {
                    names.add(contactDAO.getContactById(Integer.parseInt(id)).getFirstname());
                }
            }
            request.setAttribute("names", names);
            request.setAttribute("Birthday", EmailTemplates.getBirthday());
            request.setAttribute("NewYear", EmailTemplates.getNewYear());
        } catch (SQLException ex) {
            LOGGER.error("SQLException is here: " + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Connection is not rollbacked: " + e);
            }
        }
        return PageURL.SEND_EMAIL;
    }
}
