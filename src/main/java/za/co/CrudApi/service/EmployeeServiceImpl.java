package za.co.CrudApi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.CrudApi.entity.Employee;
import za.co.CrudApi.exception.EntityAlreadyExistException;
import za.co.CrudApi.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private static Map<String, Employee> employeeMap = new HashMap<>();


    public Employee saveEmployeeDetails(Employee employee) {
        //Optional<Employee> searchIdNumber = employeeRepository.findByIdNumber( employee.getIdNumber() );
        //Optional<Employee> searchMobileNumber = employeeRepository.findByMobileNumber( employee.getMobileNumber() );


        if (!isIDNumberOrMobileNumberExist( employee.getIdNumber(), employee.getMobileNumber() )) {
            log.info( "inserting new record on Employee table {}", employee.toString() );
            // return employeeRepository.save( employee );
            employee.setId( UUID.randomUUID().toString() );
            employeeMap.put( employee.getId(), employee );
            return employee;
        }
        for (Map.Entry<String, Employee> employeeEntry : employeeMap.entrySet()) {

            if (employeeEntry.getValue().getIdNumber().equals( employee.getIdNumber() )) {
                throw new EntityAlreadyExistException( String.format( "Record  already exist  for ID Number: %1$s ", employee.getIdNumber() ) );
            } else if (employeeEntry.getValue().getMobileNumber().equals( employee.getMobileNumber() )) {

                throw new EntityAlreadyExistException( String.format( "Record  already exist  for Mobile Number: %1$s ", employee.getMobileNumber() ) );
            }
        }
        return null;
    }


    public Boolean isIDNumberOrMobileNumberExist(String idNumber, String mobileNumber) {
        for (Map.Entry<String, Employee> employeeEntry : employeeMap.entrySet()) {
            Employee employee = employeeEntry.getValue();
            if (employee.getIdNumber().equals( idNumber ) || employee.getMobileNumber().equals( mobileNumber )) {
                return true;
            }
        }

        return false;
    }


    public Optional<Employee> searchByFirstNameOrIdNumberOrMobileNumber(String firstName, String idNumber, String mobileNumber) {
        //Optional<Employee> search = employeeRepository.findByFirstNameOrIdNumberOrMobileNumber( firstName, idNumber, mobileNumber );
        for (Map.Entry<String, Employee> employeeEntry : employeeMap.entrySet()) {
            Employee employee = employeeEntry.getValue();
            if (employee.getFirstName().equalsIgnoreCase( firstName ) || employee.getIdNumber().equalsIgnoreCase( idNumber ) || employee.getMobileNumber().equalsIgnoreCase( mobileNumber )) {
                return Optional.of( employee );
            }
        }

        return Optional.empty();
    }

    public Employee updateEmployeeDetails(Employee employee) {
        //Optional<Employee> searchById = employeeRepository.findById( employee.getId() );
        Employee existingEmployee = employeeMap.get( employee.getId() );
        if (employeeMap.containsKey( employee.getId() )) {
            if (!employee.getIdNumber().equals( existingEmployee.getIdNumber() ) && !isIDNumberOrMobileNumberExist( employee.getIdNumber(), employee.getMobileNumber() )) {
                throw new EntityAlreadyExistException( String.format( "ID Number already exist  for: %1$s ", employee.getIdNumber() ) );
            } else if (!employee.getIdNumber().equals( existingEmployee.getIdNumber() ) && !isIDNumberOrMobileNumberExist( employee.getIdNumber(), employee.getMobileNumber() )) {
                throw new EntityAlreadyExistException( String.format( "Mobile Number  already exist  for: %1$s ", employee.getMobileNumber() ) );
            } else {
                existingEmployee.setFirstName( employee.getFirstName() );
                existingEmployee.setLastName( employee.getLastName() );
                existingEmployee.setIdNumber( employee.getIdNumber() );
                existingEmployee.setMobileNumber( employee.getMobileNumber() );
                existingEmployee.setPhysicalAddress( employee.getPhysicalAddress() );
                //return employeeRepository.save( existingEmployee );
                return employeeMap.put( employee.getId(), existingEmployee );
            }
        }
        throw new EntityAlreadyExistException( String.format( "Record  not found  for ID: %1$s ", employee.getId() ) );

    }

    public boolean checkRSAIDValidation(String idNumber) {
        int sum = 0;
        boolean isSecond = false;
        if (StringUtils.isBlank( idNumber ) || idNumber.length() != 13) {
            log.info( "IDNumber length is {}", StringUtils.isBlank( idNumber ) ? "Blank" : idNumber.length() );
            return false;
        }
        for (int i = idNumber.length() - 1; i >= 0; i--) {

            int a = idNumber.charAt( i ) - '0';
            if (isSecond == true) {
                a = a * 2;
            }
            sum += a / 10;
            sum += a % 10;


            isSecond = !isSecond;

        }

        return (sum % 10 == 0);
    }


}



