package controller.commands;

import controller.ActionCommand;
import controller.PageURL;
import dao.AddressDAO;
import dao.ContactDAO;
import util.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteContacts implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = DbUtil.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        AddressDAO addressDAO = new AddressDAO(connection);
        try {
            connection.commit();
            String[] idsDel = request.getParameterValues("chkbox");
            if (idsDel != null) {
                for (String id : idsDel
                        ) {
                    addressDAO.deleteAddress(Integer.parseInt(id));
                    contactDAO.deleteContact(Integer.parseInt(id));
                }
            }
            request.setAttribute("contacts", contactDAO.getAllContacts(1, contactDAO.getNumberOfContacts()));
            connection.commit();
        } catch (SQLException ex) {
            LOGGER.error("Connection is not commited:" + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Connection is not rollbacked:" + e);
            }
        }
        return PageURL.LIST_CONTACT_COMMAND;
    }
}
