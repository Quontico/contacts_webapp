package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.AddressDAO;
import model.Address;
import model.Contact;
import dao.ContactDAO;
import util.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.*;

public class SearchContact implements ActionCommand {
    Connection connection = DbUtil.getConnection();
    private static Map<String, Object> entity = new LinkedHashMap();

    private Contact contact = new Contact();
    private Address address = new Address();
    private ContactDAO contactDAO = new ContactDAO(connection);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int page = 1;
        int number = 10;
        int amount;
        int pages;

        if (request.getParameter("Page") != null && request.getParameter("Number") != null) {
            contact = (Contact) entity.get("contact");
            address = (Address) entity.get("address");

            page = Integer.parseInt(request.getParameter("Page"));
            number = Integer.parseInt(request.getParameter("Number"));

        } else {
            contact.setFirstname(request.getParameter("FirstName"));
            contact.setSurname(request.getParameter("Surname"));
            contact.setMiddlename(request.getParameter("MiddleName"));
            if (!request.getParameter("FirstDate").isEmpty()) {
                contact.setFirstdate(LocalDate.parse(request.getParameter("FirstDate")));
            }
            if (!request.getParameter("LastDate").isEmpty()) {
                contact.setLastdate((LocalDate.parse(request.getParameter("LastDate"))));
            }
            contact.setGender(request.getParameter("Gender"));
            contact.setMarital(request.getParameter("Marital"));
            contact.setEmail(request.getParameter("Email"));
            contact.setWorkplace(request.getParameter("Workplace"));
            contact.setWebsite(request.getParameter("Website"));
            contact.setCitizenship(request.getParameter("Citizenship"));
            address.setCountry(request.getParameter("Country"));
            address.setCity(request.getParameter("City"));
            address.setStreet(request.getParameter("Street"));
            address.setHouse(request.getParameter("House"));
            address.setApartment(request.getParameter("Apartment"));
            address.setPostcode(request.getParameter("Postcode"));

            entity.put("contact", contact);
            entity.put("address", address);
        }
        amount = contactDAO.getNumberOfSearchContacts(contact, address);
        pages = amount / number;
        if (amount % number != 0) {
            pages++;
        }
        request.setAttribute("number", number);
        request.setAttribute("page", page);
        request.setAttribute("pages", pages);
        request.setAttribute("records", amount);
        request.setAttribute("action", "SEARCH_CONTACTS");
        request.setAttribute("contacts", contactDAO.findContacts(contact, address, (page - 1) * number, page * number));
        return PageURL.LIST_CONTACT;
    }
}
