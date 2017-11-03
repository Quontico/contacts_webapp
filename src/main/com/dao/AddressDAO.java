package dao;

import model.Address;
import model.Contact;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.sql.*;
import java.util.*;

public class AddressDAO {

    private final Connection connection;

    private static Logger LOGGER = Logger.getRootLogger();

    public AddressDAO(Connection connection) {
        this.connection = connection;
    }

    public Address getAddress(int idcontact) {
        Address address = new Address();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM address where contacts_idcontact = ?");
            preparedStatement.setInt(1, idcontact);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                address.setIdAddress(rs.getInt("idAddress"));
                address.setCountry(rs.getString("Country"));
                address.setCity(rs.getString("City"));
                address.setStreet(rs.getString("Street"));
                address.setHouse(rs.getString("House"));
                address.setApartment(rs.getString("Apartment"));
                address.setPostcode(rs.getString("Postcode"));
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AddressDAO: " + e);
        }

        return address;
    }

    public void addAddress(Address address, int idcontact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO address(Country, City, " +
                    "Street, House, Apartment, Postcode, contacts_idcontact) VALUES (?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getHouse());
            preparedStatement.setString(5, address.getApartment());
            preparedStatement.setString(6, address.getPostcode());
            preparedStatement.setInt(7, idcontact);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AddressDAO: " + e);
        }
    }

    public void updateAddress(Address address, int idcontact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE address SET Country=?, City=?, Street=?, House=?," +
                            " Apartment=?, Postcode=? WHERE contacts_idcontact=?");
            // Parameters start with 1
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getHouse());
            preparedStatement.setString(5, address.getApartment());
            preparedStatement.setString(6, address.getPostcode());
            preparedStatement.setInt(7, idcontact);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AddressDAO: " + e);
        }
    }

    public void deleteAddress(int idcontact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from address where contacts_idcontact=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idcontact);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in AddressDAO: " + e);
        }
    }
}
