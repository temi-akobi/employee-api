package za.co.CrudApi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.CrudApi.entity.Employee;
import za.co.CrudApi.exception.EmployeeNotFoundException;
import za.co.CrudApi.service.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger( EmployeeController.class );


    @PostMapping("/saveEmployeeDetails")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<Employee> saveEmployeeDetails(@Valid @RequestBody Employee employee) {
        Employee _employee = employeeService.saveEmployeeDetails( employee );
        return new ResponseEntity<>( _employee, HttpStatus.CREATED );
    }


    @PutMapping("/updateEmployeeDetails")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<Employee> updateEmployeeDetails(@Valid @RequestBody Employee employee) {
        Employee _employee = employeeService.updateEmployeeDetails( employee );
        return new ResponseEntity<>( _employee, HttpStatus.CREATED );

    }


    @CrossOrigin
    @GetMapping("/searchByFirstNameOrIdNumberOrMobileNumber/{searchName}")
    public ResponseEntity<Employee> getByFirstNameOrIdnumberOrMobileNumber(@PathVariable("searchName") String firstName, @PathVariable("searchName") String idNumber, @PathVariable("searchName") String mobileNumber) {
        Employee employee = employeeService.searchByFirstNameOrIdNumberOrMobileNumber( firstName, idNumber, mobileNumber ).orElseThrow( () -> new EmployeeNotFoundException( String.format( "Employee not found for: %1$s ", firstName != null ? firstName : idNumber != null ? idNumber : mobileNumber != null ? mobileNumber : " " ) ) );

        return new ResponseEntity<>( employee, HttpStatus.OK );
    }


}
