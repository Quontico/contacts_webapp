package controller.commands;

import controller.ActionCommand;
import dao.AttachmentDAO;
import model.Attachment;
import util.AttachmentControl;
import util.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class GetFileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AttachmentControl control;
        Connection connection = DbUtil.getConnection();
        AttachmentDAO attachmentDAO = new AttachmentDAO(connection);
        try {
            connection.commit();
            control = new AttachmentControl();

            Integer idAttachment = Integer.parseInt(request.getParameter("attachmentId"));

            Attachment attachment = attachmentDAO.getAttachmentById(idAttachment);

            if (control.isExists(attachment.getAttachmentPath())) {
                response.addHeader("Content-Disposition", "filename=" + attachment.getAttachmentName());
                response.addHeader("Content-length", "" + control.getFileSize(attachment.getAttachmentPath()));

                control.downloadFile(attachment.getAttachmentPath(), response.getOutputStream());
            }
        } catch (SQLException | IOException ex) {
            LOGGER.error("IOException occured or SQLException occured :" + ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Error during rollback of connection: " + e);
            }
        }
        return null;
    }
}
