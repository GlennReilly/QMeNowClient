package demo.bluemongo.com.QMeNowClient.model;

import io.realm.RealmObject;

/**
 * Created by glenn on 30/01/16.
 */
public class RealmBusinessDTO extends RealmObject{
    private int id;
    private String businessName = "";
    private String buttonColourHexCode;
    private String headerColourHexCode;
    private String backgroundColourHexCode;
    private String LogoFileName;


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

    public String getLogoFileName() {
        return LogoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        LogoFileName = logoFileName;
    }
}
