package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.ContactDAO;
import model.Contact;
import org.stringtemplate.v4.ST;
import util.DbUtil;
import util.EmailTemplates;
import util.SendMail;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class SendEmails implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Connection connection = DbUtil.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);

        ResourceBundle bundle = ResourceBundle.getBundle("smtp");

        String host = bundle.getString("host");
        String port = bundle.getString("port");
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");

        String[] mails = request.getParameterValues("mail");
        String name;
        String subject = request.getParameter("Subject");
        String message = request.getParameter("Message");

        try {
            connection.commit();
            for (String mail : mails
                    ) {
                if (message.contains("<name>")) {
                    name = contactDAO.getContactByEmail(mail).getFirstname();
                    ST new_message = EmailTemplates.setTemplate(message);
                    SendMail.send(host, port, user, pass, mail, subject, new_message.add("name", name).render());
                } else {
                    SendMail.send(host, port, user, pass, mail, subject, message);
                }
            }
            request.setAttribute("contacts", contactDAO.getAllContacts(1, contactDAO.getNumberOfContacts()));
            connection.commit();
        } catch (Exception ex) {
            LOGGER.error("There is some error: " + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Connection is not rollbacked: " + e);
            }
        }
        return PageURL.LIST_CONTACT_COMMAND;
    }
}
