package util;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import dao.ContactDAO;
import model.Contact;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.MessagingException;

public class QuartzJob implements Job {

    public void execute(JobExecutionContext context) {
        Connection connection = DbUtil.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        StringBuilder message = new StringBuilder();
        ResourceBundle bundle = ResourceBundle.getBundle("smtp");
        String host = bundle.getString("host");
        String port = bundle.getString("port");
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");
        List<Contact> contacts = contactDAO.getContactsByBirthday(LocalDate.now());
        for (Contact contact : contacts
                ) {
            message.append(contact.getFirstname()).append(" ").append(contact.getSurname()).append("\n");
        }
        SendMail.send(host, port, user, pass, user, "", message.toString());
    }
}
