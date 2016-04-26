package demo.bluemongo.com.QMeNowClient.model;

/**
 * Created by glenn on 31/08/15.
 */
public class Business {
    private int id;
    private String businessName = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";
    private transient String contactName = "";
    private transient String logoName;
    private int defaultLocationId;
    private transient String logoFilePath;
    private String buttonColourHexCode;
    private String headerColourHexCode;
    private String backgroundColourHexCode;
    private String footerColourHexCode;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getLogoName() {
        return logoName;

    }

    public int getDefaultLocationId() {
        return defaultLocationId;
    }

    public void setDefaultLocationId(int defaultLocationId) {
        this.defaultLocationId = defaultLocationId;
    }


    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public String getButtonColourHexCode() {
        return buttonColourHexCode;
    }

    public void setButtonColourHexCode(String buttonColourHexCode) {
        this.buttonColourHexCode = buttonColourHexCode;
    }

    public String getHeaderColourHexCode() {
        return headerColourHexCode;
    }

    public void setHeaderColourHexCode(String headerColourHexCode) {
        this.headerColourHexCode = headerColourHexCode;
    }

    public String getBackgroundColourHexCode() {
        return backgroundColourHexCode;
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.backgroundColourHexCode = backgroundColourHexCode;
    }

    public String getFooterColourHexCode() {
        return footerColourHexCode;
    }

    public void setFooterColourHexCode(String footerColourHexCode) {
        this.footerColourHexCode = footerColourHexCode;
    }
}
