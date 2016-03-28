package demo.bluemongo.com.barcodescannertest1.model;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetails {
    private String firstName;
    private String lastName;
    private Integer customerId;
    //private Integer businessId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

/*    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }*/
}
