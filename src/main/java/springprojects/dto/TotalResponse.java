package springprojects.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TotalResponse {
    private String firstName;
    private String lastName;
    private double totalRate;

    public TotalResponse() {
    }

    public TotalResponse(String firstName, String lastName, double total) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalRate = total;
    }

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

    public double getTotal() {
        return totalRate;
    }

    public void setTotal(double total) {
        this.totalRate = total;
    }
}
