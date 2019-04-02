package springprojects.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    public  ResponseCode responseCode;
    public String message;

    public ApiResponse(ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }


}
