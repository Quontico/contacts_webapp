package dao;

import model.Telephone;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelephoneDAO {
    private Connection connection;
    private static Logger LOGGER = Logger.getRootLogger();

    public TelephoneDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPhone(Telephone phone, int idcontact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO telephone( " +
                    "DialingPrefix, ProviderCode, PhoneNumber, NumberType, commentary, contacts_idcontact) VALUES (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, phone.getDialingPrefix());
            preparedStatement.setInt(2, phone.getProviderCode());
            preparedStatement.setInt(3, phone.getPhoneNumber());
            preparedStatement.setString(4, phone.getNumberType());
            preparedStatement.setString(5, phone.getCommentary());
            preparedStatement.setInt(6, idcontact);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in TelephoneDAO: " + e);
        }
    }

    public void updatePhone(Telephone phone) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE telephone SET DialingPrefix=?, ProviderCode=?, PhoneNumber=?, " +
                            "commentary=?, NumberType=? WHERE idtelephone=?");
            // Parameters start with 1
            preparedStatement.setInt(1, phone.getDialingPrefix());
            preparedStatement.setInt(2, phone.getProviderCode());
            preparedStatement.setInt(3, phone.getPhoneNumber());
            preparedStatement.setString(4, phone.getCommentary());
            preparedStatement.setString(5, phone.getNumberType());
            preparedStatement.setInt(6, phone.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Some SQLException in TelephoneDAO: " + e);
        }
    }

    public List<Telephone> getAllPhones(int idcontact) {
        List<Telephone> phones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM telephone WHERE contacts_idcontact = ?");
            preparedStatement.setInt(1, idcontact);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Telephone phone = new Telephone();
                phone.setId(rs.getInt("idtelephone"));
                phone.setDialingPrefix(rs.getInt("DialingPrefix"));
                phone.setProviderCode(rs.getInt("ProviderCode"));
                phone.setPhoneNumber(rs.getInt("PhoneNumber"));
                phone.setNumberType(rs.getString("NumberType"));
                phone.setCommentary(rs.getString("commentary"));
                phones.add(phone);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in TelephoneDAO: " + e);
        }

        return phones;
    }

    public void deletePhone(int idPhone) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM telephone WHERE idtelephone=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idPhone);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in TelephoneDAO: " + e);
        }
    }

}
