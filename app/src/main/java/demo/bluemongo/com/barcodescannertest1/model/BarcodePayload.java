package demo.bluemongo.com.barcodescannertest1.model;

/**
 * Created by glenn on 29/09/15.
 */
public class BarcodePayload {
    private String DateTimeString;
    private String CustomerName;
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getDateTimeString() {
        return DateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        DateTimeString = dateTimeString;
    }
}
