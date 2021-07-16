package za.co.CrudApi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class ErrorMessage  {

    private int statusCode;
    private Date timeStamp;
    private String message;
    private String description;
}
