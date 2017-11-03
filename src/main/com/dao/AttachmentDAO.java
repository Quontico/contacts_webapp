package dao;

import model.Attachment;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDAO {
    private Connection connection;

    private static Logger LOGGER = Logger.getRootLogger();

    public AttachmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAttachment(Attachment attachment, int idcontact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO attachment(" +
                    "Name, FilePath, DateUpload, attachment_comment, contacts_idcontact) VALUES (?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, attachment.getAttachmentName());
            preparedStatement.setString(2, attachment.getAttachmentPath());
            preparedStatement.setDate(3, Date.valueOf(attachment.getDateUpload()));
            preparedStatement.setString(4, attachment.getAttachmentComment());
            preparedStatement.setInt(5, idcontact);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AttachmentDAO: " + e);
        }
    }

    public void updateAttachment(Attachment attachment) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE attachment SET Name=?, FilePath=?, DateUpload=?, " +
                            "attachment_comment=? WHERE idattachment=?");
            // Parameters start with 1
            preparedStatement.setString(1, attachment.getAttachmentName());
            preparedStatement.setString(2, attachment.getAttachmentPath());
            if (attachment.getDateUpload() != null) {
                preparedStatement.setDate(3, Date.valueOf(attachment.getDateUpload()));
            }
            preparedStatement.setString(4, attachment.getAttachmentComment());
            preparedStatement.setInt(5, attachment.getIdattachment());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AttachmentDAO: " + e);
        }
    }

    public List<Attachment> getAllAttachments(int idcontact) {
        List<Attachment> attachments = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM attachment WHERE contacts_idcontact = ?");
            preparedStatement.setInt(1, idcontact);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Attachment attachment = new Attachment();
                attachment.setIdattachment(rs.getInt("idattachment"));
                attachment.setAttachmentName(rs.getString("Name"));
                attachment.setAttachmentPath(rs.getString("FilePath"));
                attachment.setDateUpload(LocalDate.parse(rs.getString("DateUpload")));
                attachment.setAttachmentComment(rs.getString("attachment_comment"));
                attachments.add(attachment);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AttachmentDAO: " + e);
        }

        return attachments;
    }

    public void deleteAttachment(int idAttachment) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM attachment WHERE idattachment=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idAttachment);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AttachmentDAO: " + e);
        }
    }

    public Attachment getAttachmentById(int idattachment) {
        Attachment attachment = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM attachment WHERE idattachment = ?");
            preparedStatement.setInt(1, idattachment);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                attachment = new Attachment();
                attachment.setIdattachment(rs.getInt("idattachment"));
                attachment.setAttachmentName(rs.getString("Name"));
                attachment.setAttachmentPath(rs.getString("FilePath"));
                attachment.setDateUpload(LocalDate.parse(rs.getString("DateUpload")));
                attachment.setAttachmentComment(rs.getString("attachment_comment"));
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AttachmentDAO: " + e);
        }

        return attachment;
    }
}
