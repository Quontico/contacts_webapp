package model;

public class Telephone {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private int dialingPrefix;
    private int providerCode;
    private int phoneNumber;
    private String numberType;
    private String commentary;
    private Integer relatedContact;

    public int getDialingPrefix() {
        return dialingPrefix;
    }

    public void setDialingPrefix(int dialingPrefix) {
        this.dialingPrefix = dialingPrefix;
    }

    public int getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(int providerCode) {
        this.providerCode = providerCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Integer getRelatedContact() {
        return relatedContact;
    }

    public void setRelatedContact(Integer relatedContact) {
        this.relatedContact = relatedContact;
    }

    @Override
    public String toString() {
        return "Telephone : " + dialingPrefix + "-" + providerCode + "-" + phoneNumber;
    }
}