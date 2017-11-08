package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import util.DbUtil;
import util.ValidateInput;
import dao.AddressDAO;
import dao.AttachmentDAO;
import dao.ContactDAO;
import dao.TelephoneDAO;
import model.Address;
import model.Attachment;
import model.Contact;
import model.Telephone;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.AttachmentControl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


public class AddOrEditContact implements ActionCommand {

    private Connection connection;

    private Contact contact;
    private Address address;
    private Telephone telephone;
    private Attachment attachment;

    private ContactDAO contactDAO;
    private AddressDAO addressDAO;
    private TelephoneDAO telephoneDAO;
    private AttachmentDAO attachmentDAO;

    private String attachmentAction;

    private static String ALL_LITERAL_REGEX = "[a-zA-Zа-яА-ЯЁё0-9 '-]+";
    private static String ALL_STRING_REGEX = "[a-zA-Zа-яА-ЯЁё '-]+";
    private static String ALL_NUMBER_REGEX = "[0-9]+";
    private static String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    private ResourceBundle bundle = ResourceBundle.getBundle("dropbox");

    public AddOrEditContact() {
        connection = DbUtil.getConnection();
        contactDAO = new ContactDAO(connection);
        telephoneDAO = new TelephoneDAO(connection);
        addressDAO = new AddressDAO(connection);
        attachmentDAO = new AttachmentDAO(connection);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AttachmentControl control = null;
        try {
            control = new AttachmentControl();

            contact = new Contact();
            address = new Address();

            if (!ServletFileUpload.isMultipartContent(request)) {
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype=multipart/form-data.");
                writer.flush();
                return PageURL.ERROR_PAGE;
            }

        } catch (IOException e) {
            LOGGER.error("IOException occured: " + e);
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024 * 1024);
        File tempDir = (File) request.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(1024 * 1024 * 1024);
        upload.setHeaderEncoding("UTF-8");


        try {
            connection.commit();
            List formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {

                Iterator iterator = formItems.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    if (!item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String name = item.getName();
                        long size = item.getSize();

                        if (fieldName.equals("avatar")) {
                            if (size > 0) {
                                if (name.substring(name.lastIndexOf('.')).equals(".png") || name.substring(name.lastIndexOf('.')).equals(".jpeg") || name.substring(name.lastIndexOf('.')).equals(".jpg")) {
                                    String fileName = contact.getIdcontact() + name.substring(name.lastIndexOf('.'));
                                    String path = bundle.getString("PHOTO_FOLDER") + fileName;
                                    if (control.isExists(path)) {
                                        control.deleteFile(path);
                                    }
                                    contact.setUrlAvatar(path);
                                    contactDAO.updatePhoto(contact);
                                    control.uploadFile(path, item.getInputStream());
                                } else {
                                    LOGGER.error("Wrong avatar validation!");
                                }
                            }

                        } else {
                            String path = bundle.getString("ATTACHMENT_FOLDER") + contact.getIdcontact() + "/"
                                    + name;
                            for (int i = 1; control.isExists(path); i++) {
                                path = bundle.getString("ATTACHMENT_FOLDER") + contact.getIdcontact() + "/"
                                        + name.substring(0, name.lastIndexOf('.')) + "(" + i + ")" + name.substring(name.lastIndexOf('.'));
                            }

                            switch (attachmentAction) {
                                case "none":
                                    break;
                                case "edit":
                                    if (item.getSize() > 0) {
                                        control.deleteFile(attachment.getAttachmentPath());
                                        attachment.setAttachmentPath(path);
                                        attachment.setAttachmentName(name);
                                        control.uploadFile(path, item.getInputStream());
                                    }

                                    attachment.setDateUpload(LocalDate.now());
                                    attachmentDAO.updateAttachment(attachment);
                                    break;
                                case "add":
                                    attachment.setDateUpload(LocalDate.now());
                                    attachment.setAttachmentPath(path);
                                    control.uploadFile(path, item.getInputStream());
                                    attachmentDAO.addAttachment(attachment, contact.getIdcontact());

                                    break;
                                case "delete":
                                    control.deleteFile(attachment.getAttachmentPath());
                                    attachmentDAO.deleteAttachment(attachment.getIdattachment());
                                    break;

                            }
                            attachmentAction = "";
                        }
                    } else {
                        String name = item.getFieldName();
                        String value = item.getString("UTF-8");
                        handleFormField(name, value);
                    }
                }
            }
            connection.commit();
        } catch (Exception ex) {
            LOGGER.error("Some exception during rollback in AddOrEditContact (execute): " + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("SQLException during rollback in AddOrEditContact (execute): " + e);
            }
        }

        request.setAttribute("Page", request.getParameter("Page"));
        request.setAttribute("Number", request.getParameter("Number"));
        return PageURL.LIST_CONTACT_COMMAND;
    }

    private void handleFormField(String name, String value) {

        switch (name) {
            case "idcontact":
                try {
                    contact.setIdcontact(Integer.parseInt(value));
                } catch (Exception e) {
                    LOGGER.error("There was a try to add an empty idcontact in AddOrEditContact: " + e);
                }
                break;
            case "FirstName":
                if (ValidateInput.testFieldValue(ALL_STRING_REGEX, value)) {
                    contact.setFirstname(value);
                } else {
                    LOGGER.info("Wrong firstname value during validation!!!");
                }
                break;
            case "Surname":
                if (ValidateInput.testFieldValue(ALL_STRING_REGEX, value)) {
                    contact.setSurname(value);
                } else {
                    LOGGER.info("Wrong Surname value during validation!!!");
                }
                break;
            case "MiddleName":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_STRING_REGEX, value)) {
                        contact.setMiddlename(value);
                    } else {
                        LOGGER.info("Wrong Middlename value during validation!!!");
                    }
                }
                break;
            case "Birthdate":
                if (!value.isEmpty()) {
                    contact.setBirthdate(LocalDate.parse(value));
                }
                break;
            case "Gender":
                contact.setGender(value);
                break;
            case "Marital":
                contact.setMarital(value);
                break;
            case "Citizenship":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_STRING_REGEX, value)) {
                        contact.setCitizenship(value);
                    } else {
                        LOGGER.info("Wrong Citizenship value during validation!!!");
                    }
                }
                break;
            case "Workplace":
                if (value.length() > 0) {
                    contact.setWorkplace(value);
                }
                break;
            case "Email":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(EMAIL_REGEX, value)) {
                        contact.setEmail(value);
                    } else {
                        LOGGER.info("Wrong Email value during validation!!!");
                    }
                }
                break;
            case "Website":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_STRING_REGEX, value)) {
                        contact.setWebsite(value);
                    } else {
                        LOGGER.info("Wrong Website value during validation!!!");
                    }
                }
                break;
            case "Country":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_STRING_REGEX, value)) {
                        address.setCountry(value);
                    } else {
                        LOGGER.info("Wrong Country value during validation!!!");
                    }
                }
                break;
            case "City":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_LITERAL_REGEX, value)) {
                        address.setCity(value);
                    } else {
                        LOGGER.info("Wrong City value during validation!!!");
                    }
                }
                break;
            case "Street":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_LITERAL_REGEX, value)) {
                        address.setStreet(value);
                    } else {
                        LOGGER.info("Wrong Street value during validation!!!");
                    }
                }
                break;
            case "House":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_NUMBER_REGEX, value)) {
                        address.setHouse(value);
                    } else {
                        LOGGER.info("Wrong House value during validation!!!");
                    }
                }
                break;
            case "Apartment":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_NUMBER_REGEX, value)) {
                        address.setApartment(value);
                    } else {
                        LOGGER.info("Wrong Apartment value during validation!!!");
                    }
                }
                break;
            case "Postcode":
                if (value.length() > 0) {
                    if (ValidateInput.testFieldValue(ALL_NUMBER_REGEX, value)) {
                        address.setPostcode(value);
                    } else {
                        LOGGER.info("Wrong Postcode value during validation!!!");
                    }
                }
                if (contact.getIdcontact() == null) {
                    contact.setIdcontact(contactDAO.addContact(contact));
                    addressDAO.addAddress(address, contact.getIdcontact());
                } else {
                    contactDAO.UpdateContact(contact);
                    addressDAO.updateAddress(address, contact.getIdcontact());
                }

                break;
            case "Dialing_Prefix":
                telephone = new Telephone();
                if (value.length() > 0 && value.length() < 5) {
                    if (ValidateInput.testFieldValue(ALL_NUMBER_REGEX, value)) {
                        telephone.setDialingPrefix(Integer.parseInt(value));
                    } else {
                        LOGGER.info("Wrong Dialing_Prefix value during validation!!!");
                    }
                }
                break;
            case "Provider_Code":
                if (value.length() > 0 && value.length() < 5) {
                    if (ValidateInput.testFieldValue(ALL_NUMBER_REGEX, value)) {
                        telephone.setProviderCode(Integer.parseInt(value));
                    } else {
                        LOGGER.info("Wrong Provider_Code value during validation!!!");
                    }
                }
                break;
            case "Phone_Number":
                if (value.length() > 6) {
                    if (ValidateInput.testFieldValue(ALL_NUMBER_REGEX, value)) {
                        telephone.setPhoneNumber(Integer.parseInt(value));
                    } else {
                        LOGGER.info("Wrong Phone_Number value during validation!!!");
                    }
                }
                break;
            case "Number_Type":
                telephone.setNumberType(value);
                break;
            case "Phone_Commentary":
                telephone.setCommentary(value);
                break;
            case "Phone_ID":
                try {
                    telephone.setId(Integer.parseInt(value));
                } catch (Exception e) {
                }
                break;
            case "Phone_Action":
                switch (value) {
                    case "none":
                        break;
                    case "add":
                        telephoneDAO.addPhone(telephone, contact.getIdcontact());
                        break;
                    case "edit":
                        telephoneDAO.updatePhone(telephone);
                        break;
                    case "delete":
                        telephoneDAO.deletePhone(telephone.getId());
                        break;
                }
                telephone = null;
                break;
            case "atchAction":
                attachmentAction = value;
                break;
            case "Commentary":
                attachment = new Attachment();
                attachment.setAttachmentComment(value);
                break;
            case "PathFile":
                attachment.setAttachmentPath(value);
                break;
            case "atchName":
                attachment.setAttachmentName(value);
                break;
            case "atchId":
                try {
                    attachment.setIdattachment(Integer.parseInt(value));
                } catch (Exception e) {
                    LOGGER.error("There was a try to add an empty idattachment in AddOrEditContact: " + e);
                }
                break;
        }
    }
}
