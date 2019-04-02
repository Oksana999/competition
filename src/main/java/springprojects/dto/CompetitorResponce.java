package springprojects.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompetitorResponce {
    private ResponseCode responseCode;
    private String message;

    public CompetitorResponce(ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}
