package za.co.CrudApi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.CrudApi.entity.Employee;
import za.co.CrudApi.repository.EmployeeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeService {


    @Mock
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepository employeeRepository;


    public void init() {
        MockitoAnnotations.initMocks( this );
    }


    @Test
    public void getAllEmployeeTest() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add( new Employee( UUID.randomUUID().toString(), "John", "Doe", "1234", "9712208665084", "20 main street" ) );
        employeeList.add( new Employee( UUID.randomUUID().toString(), "Jane", "Doe", "1236", "9712208373085", "20 main street" ) );
        employeeList.add( new Employee( UUID.randomUUID().toString(), "Joseph", "Doe", "1238", "9712209472084", "20 main street" ) );

        when( employeeRepository.findAll() ).thenReturn( employeeList );

        List<Employee> resultedEmployeeList = employeeRepository.findAll();

        assertEquals( 3, resultedEmployeeList.size() );

        verify( employeeRepository, times( 1 ) ).findAll();

    }

    @Test
    public void createEmployeeTest() {
        Map<String, Employee> employeeMap = new HashMap<>();
        String id = UUID.randomUUID().toString();
        Employee employee = new Employee( id, "John", "Doe", "1234", "9712208665084", "20 main street" );
        employeeMap.put( id, employee );

        when( employeeService.saveEmployeeDetails( employee ) ).thenReturn( employee );

        Employee resultedEmployee = employeeService.saveEmployeeDetails( employee );

        assertEquals( employee, resultedEmployee );
        verify( employeeService, times( 1 ) ).saveEmployeeDetails( employee );


    }

    @Test
    public void updateEmployeeTest() {
        Map<String, Employee> employeeMap = new HashMap<>();
        String id = UUID.randomUUID().toString();
        Employee employee = new Employee( id, "John", "Doe", "1234", "9712208665084", "20 main street" );
        employeeMap.put( id, employee );
        when( employeeService.updateEmployeeDetails( employee ) ).thenReturn( employee );

        Employee updatedEmployee = employeeService.updateEmployeeDetails( employee );

        assertEquals( id, updatedEmployee.getId() );


    }

    @Test
    public void searchByFirstNameOrIdNumberOrMobileNumberTest() {
        Map<String, Employee> employeeMap = new HashMap<>();
        String id = UUID.randomUUID().toString();
        Employee employee = new Employee( id, "John", "Doe", "1234", "9712208665084", "20 main street" );
        employeeMap.put( id, employee );
        when( employeeService.searchByFirstNameOrIdNumberOrMobileNumber( "John", "", "" ) ).thenReturn( Optional.of( employee ) );
        Optional<Employee> optionalEmployee = employeeService.searchByFirstNameOrIdNumberOrMobileNumber( "John", "", "" );
        assertEquals( "John", optionalEmployee.get().getFirstName() );


    }

}
