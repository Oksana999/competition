package springprojects.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinnerResponse {
    private String firstName;
    private String lastName;
    private String country;
    private double maxTotalRate;

    @Override
    public String toString() {
        return "WinnerResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", maxTotalRate=" + maxTotalRate +
                '}';
    }
}
