package lk.project.employee_details.to.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqTo implements Serializable {

    @Pattern(regexp = "^E\\d{3}$",message = "Invalid employee Id")
    private String id;
    @NotBlank(message = "Name can not be empty")
    @Pattern(regexp = "^[A-Za-z ]{2,}$",message = "Invalid name")
    private String name;
    @NotBlank(message = "Can not be empty")
    @Pattern(regexp = "^[A-Za-z ]{2,}$",message = "Invalid input")
    private String country;


}
