package za.co.CrudApi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;


    @Basic(optional = false)
    @NotBlank(message = "FirstName is required.")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic(optional = false)


    @NotBlank(message = "LastName is required.")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic(optional = false)
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Basic(optional = false)
    @Pattern(regexp = "([0-9][0-9])((?:[0][1-9])|(?:[1][0-2]))((?:[0-2][0-9])|(?:[3][0-1]))([0-9])([0-9]{3})([0-9])([0-9])([0-9])", message = "ID Number must be a valid South Africa ID Number")
    @Column(name = "ID_NUMBER")
    private String idNumber;

    @Basic(optional = false)
    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;


}
