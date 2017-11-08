package dao;

import model.Address;
import model.Contact;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ContactDAO {

    private static Logger LOGGER = Logger.getRootLogger();

    private final Connection connection;

    public ContactDAO(Connection connection) {
        this.connection = connection;
    }

    public int addContact(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact(Surname, FirstName, " +
                    "MiddleName, BirthdayDate, Gender, MaritalStatus, Email, Workplace, Website, Citizenship, UrlAvatar) VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            // Parameters start with 1
            preparedStatement.setString(1, contact.getSurname());
            preparedStatement.setString(2, contact.getFirstname());
            preparedStatement.setString(3, contact.getMiddlename());
            if (contact.getBirthdate() != null) {
                preparedStatement.setDate(4, Date.valueOf(contact.getBirthdate()));
            } else {
                preparedStatement.setDate(4, null);
            }
            preparedStatement.setString(5, contact.getGender());
            preparedStatement.setString(6, contact.getMarital());
            preparedStatement.setString(7, contact.getEmail());
            preparedStatement.setString(8, contact.getWorkplace());
            preparedStatement.setString(9, contact.getWebsite());
            preparedStatement.setString(10, contact.getCitizenship());
            preparedStatement.setString(11, contact.getUrlAvatar());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int autoIncKeyFromApi = -1;

            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            } else {

                // throw an exception from here
            }
            LOGGER.info("Query: " + preparedStatement);
            return autoIncKeyFromApi;
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }

        return -1;
    }

    public void deleteContact(int idcontact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from contact where idcontact=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idcontact);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
    }

    public void UpdateContact(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE contact SET Surname=?, FirstName=?, MiddleName=?, BirthdayDate=?, Gender=?," +
                            " MaritalStatus=?, Email=?, Workplace=?, Website=?, Citizenship=? WHERE idcontact=?");
            // Parameters start with 1
            preparedStatement.setString(1, contact.getSurname());
            preparedStatement.setString(2, contact.getFirstname());
            preparedStatement.setString(3, contact.getMiddlename());
            if (contact.getBirthdate() != null) {
                preparedStatement.setDate(4, Date.valueOf(contact.getBirthdate()));
            } else {
                preparedStatement.setDate(4, null);
            }
            preparedStatement.setString(5, contact.getGender());
            preparedStatement.setString(6, contact.getMarital());
            preparedStatement.setString(7, contact.getEmail());
            preparedStatement.setString(8, contact.getWorkplace());
            preparedStatement.setString(9, contact.getWebsite());
            preparedStatement.setString(10, contact.getCitizenship());
            preparedStatement.setInt(11, contact.getIdcontact());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
    }

    public void updatePhoto(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET UrlAvatar=? WHERE idcontact=?");
            preparedStatement.setString(1, contact.getUrlAvatar());
            preparedStatement.setInt(2, contact.getIdcontact());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException in updatePhoto: " + e);
        }

    }

    public Map<Contact, Address> getAllContacts(int firstrow, int lastrow) {
        LinkedHashMap<Contact, Address> contacts = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SQL_CALC_FOUND_ROWS * FROM contact JOIN address " +
                    "ON contact.idcontact = address.contacts_idcontact LIMIT ?,?");
            preparedStatement.setInt(1, firstrow);
            preparedStatement.setInt(2, lastrow);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setIdcontact(rs.getInt("idcontact"));
                contact.setFirstname(rs.getString("FirstName"));
                contact.setSurname(rs.getString("Surname"));
                contact.setMiddlename(rs.getString("MiddleName"));
                if (rs.getString("BirthdayDate") != null) {
                    contact.setBirthdate(LocalDate.parse(rs.getString("BirthdayDate")));
                } else {
                    contact.setBirthdate(null);
                }
                contact.setWorkplace(rs.getString("Workplace"));

                Address address = new Address();
                address.setCountry(rs.getString("Country"));
                address.setCity(rs.getString("City"));
                address.setStreet(rs.getString("Street"));
                address.setHouse(rs.getString("House"));
                address.setApartment(rs.getString("Apartment"));
                contacts.put(contact, address);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }

        return contacts;
    }

    public Contact getContactById(int idcontact) {
        Contact contact = new Contact();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM contact WHERE idcontact=?");
            preparedStatement.setInt(1, idcontact);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                contact.setIdcontact(rs.getInt("idcontact"));
                contact.setSurname(rs.getString("Surname"));
                contact.setFirstname(rs.getString("FirstName"));
                contact.setMiddlename(rs.getString("MiddleName"));
                if (rs.getString("BirthdayDate") != null) {
                    contact.setBirthdate(LocalDate.parse(rs.getString("BirthdayDate")));
                } else {
                    contact.setBirthdate(null);
                }
                contact.setGender(rs.getString("Gender"));
                contact.setMarital(rs.getString("MaritalStatus"));
                contact.setWorkplace(rs.getString("Workplace"));
                contact.setEmail(rs.getString("Email"));
                contact.setWebsite(rs.getString("Website"));
                contact.setCitizenship(rs.getString("Citizenship"));
                contact.setUrlAvatar(rs.getString("UrlAvatar"));
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return contact;
    }

    public Map<Contact, Address> findContacts(Contact contact, Address address, int firstrow, int lastrow) {
        LinkedHashMap<Contact, Address> contacts = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM contact JOIN address ON contact.idcontact = address.contacts_idcontact" +
                            " WHERE (FirstName LIKE ? OR ? = '') AND (Surname LIKE ? OR ? = '' ) " +
                            "AND (MiddleName LIKE ? OR ? = '' ) AND (Gender LIKE ? OR ? = '' ) AND (MaritalStatus LIKE ? OR ? = '' ) " +
                            "AND (Citizenship LIKE ? OR ? = '' ) AND (BirthdayDate >= ? OR ? IS NULL ) AND (BirthdayDate <= ? OR ? IS NULL ) " +
                            "AND (Country LIKE ? OR ? = '' ) AND (City LIKE ? OR ? = '' ) AND (Street LIKE ? OR ? = '' ) " +
                            "AND (House LIKE ? OR ? = '' ) AND (Apartment LIKE ? OR ? = '' ) AND (Postcode LIKE ? OR ? = '' ) " +
                            "LIMIT ?,?");

            preparedStatement.setString(1, "%" + contact.getFirstname() + "%");
            preparedStatement.setString(2, contact.getFirstname());
            preparedStatement.setString(3, "%" + contact.getSurname() + "%");
            preparedStatement.setString(4, contact.getSurname());
            preparedStatement.setString(5, "%" + contact.getMiddlename() + "%");
            preparedStatement.setString(6, contact.getMiddlename());
            preparedStatement.setString(7, "%" + contact.getGender() + "%");
            preparedStatement.setString(8, contact.getGender());
            preparedStatement.setString(9, "%" + contact.getMarital() + "%");
            preparedStatement.setString(10, contact.getMarital());
            preparedStatement.setString(11, "%" + contact.getCitizenship() + "%");
            preparedStatement.setString(12, contact.getCitizenship());
            if (contact.getFirstdate() != null) {
                preparedStatement.setDate(13, Date.valueOf(contact.getFirstdate()));
                preparedStatement.setDate(14, Date.valueOf(contact.getFirstdate()));
            } else {
                preparedStatement.setDate(13, null);
                preparedStatement.setDate(14, null);
            }
            if (contact.getLastdate() != null) {
                preparedStatement.setDate(15, Date.valueOf(contact.getLastdate()));
                preparedStatement.setDate(16, Date.valueOf(contact.getLastdate()));
            } else {
                preparedStatement.setDate(15, null);
                preparedStatement.setDate(16, null);
            }
            preparedStatement.setString(17, "%" + address.getCountry() + "%");
            preparedStatement.setString(18, address.getCountry());
            preparedStatement.setString(19, "%" + address.getCity() + "%");
            preparedStatement.setString(20, address.getCity());
            preparedStatement.setString(21, "%" + address.getStreet() + "%");
            preparedStatement.setString(22, address.getStreet());
            preparedStatement.setString(23, "%" + address.getHouse() + "%");
            preparedStatement.setString(24, address.getHouse());
            preparedStatement.setString(25, "%" + address.getApartment() + "%");
            preparedStatement.setString(26, address.getApartment());
            preparedStatement.setString(27, "%" + address.getPostcode() + "%");
            preparedStatement.setString(28, address.getPostcode());
            preparedStatement.setInt(29, firstrow);
            preparedStatement.setInt(30, lastrow);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Contact thisContact = new Contact();
                thisContact.setIdcontact(rs.getInt("idcontact"));
                thisContact.setFirstname(rs.getString("FirstName"));
                thisContact.setSurname(rs.getString("Surname"));
                thisContact.setMiddlename(rs.getString("MiddleName"));
                if (rs.getString("BirthdayDate") != null) {
                    thisContact.setBirthdate(LocalDate.parse(rs.getString("BirthdayDate")));
                } else {
                    thisContact.setBirthdate(null);
                }
                thisContact.setWorkplace(rs.getString("Workplace"));

                Address thisAddress = new Address();
                thisAddress.setCountry(rs.getString("Country"));
                thisAddress.setCity(rs.getString("City"));
                thisAddress.setStreet(rs.getString("Street"));
                thisAddress.setHouse(rs.getString("House"));
                thisAddress.setApartment(rs.getString("Apartment"));
                contacts.put(thisContact, thisAddress);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return contacts;
    }


    public int getNumberOfSearchContacts(Contact contact, Address address) {
        try {
            int number = -1;
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT COUNT(*) FROM contact JOIN address ON contact.idcontact = address.contacts_idcontact" +
                            " WHERE (FirstName LIKE ? OR ? = '') AND (Surname LIKE ? OR ? = '' ) " +
                            "AND (MiddleName LIKE ? OR ? = '' ) AND (Gender LIKE ? OR ? = '' ) AND (MaritalStatus LIKE ? OR ? = '' ) " +
                            "AND (Citizenship LIKE ? OR ? = '' ) AND (BirthdayDate >= ? OR ? IS NULL ) AND (BirthdayDate <= ? OR ? IS NULL ) " +
                            "AND (Country LIKE ? OR ? = '' ) AND (City LIKE ? OR ? = '' ) AND (Street LIKE ? OR ? = '' ) " +
                            "AND (House LIKE ? OR ? = '' ) AND (Apartment LIKE ? OR ? = '' ) AND (Postcode LIKE ? OR ? = '' ) ");

            preparedStatement.setString(1, "%" + contact.getFirstname() + "%");
            preparedStatement.setString(2, contact.getFirstname());
            preparedStatement.setString(3, "%" + contact.getSurname() + "%");
            preparedStatement.setString(4, contact.getSurname());
            preparedStatement.setString(5, "%" + contact.getMiddlename() + "%");
            preparedStatement.setString(6, contact.getMiddlename());
            preparedStatement.setString(7, "%" + contact.getGender() + "%");
            preparedStatement.setString(8, contact.getGender());
            preparedStatement.setString(9, "%" + contact.getMarital() + "%");
            preparedStatement.setString(10, contact.getMarital());
            preparedStatement.setString(11, "%" + contact.getCitizenship() + "%");
            preparedStatement.setString(12, contact.getCitizenship());
            if (contact.getFirstdate() != null) {
                preparedStatement.setDate(13, Date.valueOf(contact.getFirstdate()));
                preparedStatement.setDate(14, Date.valueOf(contact.getFirstdate()));
            } else {
                preparedStatement.setDate(13, null);
                preparedStatement.setDate(14, null);
            }
            if (contact.getLastdate() != null) {
                preparedStatement.setDate(15, Date.valueOf(contact.getLastdate()));
                preparedStatement.setDate(16, Date.valueOf(contact.getLastdate()));
            } else {
                preparedStatement.setDate(15, null);
                preparedStatement.setDate(16, null);
            }
            preparedStatement.setString(17, "%" + address.getCountry() + "%");
            preparedStatement.setString(18, address.getCountry());
            preparedStatement.setString(19, "%" + address.getCity() + "%");
            preparedStatement.setString(20, address.getCity());
            preparedStatement.setString(21, "%" + address.getStreet() + "%");
            preparedStatement.setString(22, address.getStreet());
            preparedStatement.setString(23, "%" + address.getHouse() + "%");
            preparedStatement.setString(24, address.getHouse());
            preparedStatement.setString(25, "%" + address.getApartment() + "%");
            preparedStatement.setString(26, address.getApartment());
            preparedStatement.setString(27, "%" + address.getPostcode() + "%");
            preparedStatement.setString(28, address.getPostcode());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                number = rs.getInt(1);
            }
            return number;
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return -1;
    }

    public List<String> findEmails(String[] ids) {
        List<String> emails = new ArrayList<>();
        String email;
        for (String id : ids) {
            email = getContactById(Integer.parseInt(id)).getEmail();
            emails.add(email);
        }
        return emails;
    }

    public List<String> getAllGenders() {
        List<String> genders = new ArrayList<>();
        String gender;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Gender FROM genders");
            while (rs.next()) {
                gender = rs.getString("Gender");
                genders.add(gender);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return genders;
    }

    public List<String> getAllMaritals() {
        List<String> maritals = new ArrayList<>();
        String marital;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Marital FROM maritals");
            while (rs.next()) {
                marital = rs.getString("Marital");
                maritals.add(marital);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return maritals;
    }

    public List<Contact> getContactsByBirthday(LocalDate date) {
        List<Contact> contacts = new ArrayList<>();
        Date birthday = Date.valueOf(date);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE DAY(BirthdayDate)=DAY(?) " +
                    "AND MONTH(BirthdayDate)=MONTH(?)");
            preparedStatement.setDate(1, birthday);
            preparedStatement.setDate(2, birthday);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setFirstname(rs.getString("FirstName"));
                contact.setSurname(rs.getString("Surname"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }

        return contacts;
    }

    public int getNumberOfContacts() {
        try {
            int number = -1;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM contact");
            while (rs.next()) {
                number = rs.getInt(1);
            }
            return number;
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return -1;
    }

    public Contact getContactByEmail(String Mail) {
        Contact contact = new Contact();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM contact WHERE Email=?");
            preparedStatement.setString(1, Mail);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                contact.setIdcontact(rs.getInt("idcontact"));
                contact.setSurname(rs.getString("Surname"));
                contact.setFirstname(rs.getString("FirstName"));
            }
        } catch (SQLException e) {
            LOGGER.error("Some SQLException in ContactDAO: " + e);
        }
        return contact;
    }
}
