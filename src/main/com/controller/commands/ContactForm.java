package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.AddressDAO;
import dao.AttachmentDAO;
import dao.ContactDAO;
import dao.TelephoneDAO;
import model.Address;
import model.Attachment;
import model.Contact;
import model.Telephone;
import util.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ContactForm implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Connection connection = DbUtil.getConnection();

        ContactDAO contactDAO = new ContactDAO(connection);
        AddressDAO addressDAO = new AddressDAO(connection);
        TelephoneDAO phoneDAO = new TelephoneDAO(connection);
        AttachmentDAO attachmentDAO = new AttachmentDAO(connection);

        try {
            connection.commit();

            List<String> genders = contactDAO.getAllGenders();
            List<String> maritals = contactDAO.getAllMaritals();

            request.setAttribute("genders", genders);
            request.setAttribute("maritals", maritals);

            LocalDate today = LocalDate.now();
            request.setAttribute("today", today);

            if (request.getParameter("idcontact") != null) {

                String idcontact = request.getParameter("idcontact");


                List<Telephone> phones;
                List<Attachment> attachments;

                Contact contact = contactDAO.getContactById(Integer.parseInt(idcontact));
                request.setAttribute("contact", contact);

                Address address = addressDAO.getAddress(Integer.parseInt(idcontact));
                request.setAttribute("address", address);

                attachments = attachmentDAO.getAllAttachments(Integer.parseInt(idcontact));
                request.setAttribute("attachments", attachments);

                phones = phoneDAO.getAllPhones(Integer.parseInt(idcontact));
                request.setAttribute("telephones", phones);

            }
        } catch (SQLException ex) {
            LOGGER.error("SQLException occured" + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Connection is not rollbacked: " + e);
            }
        }
        return PageURL.ADD_OR_EDIT;
    }
}
