package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.model.Department;
import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.dto.EmployeeRequest;
import com.macademia.employeerecordapplication.repository.DepartmentRepository;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.OfficeRepository;
import com.macademia.employeerecordapplication.repository.PayrollRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private OfficeRepository officeRepository;

    @Mock
    private PayrollRepository payrollRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void saveEmployee_func_should_throw_exception_when_department_not_found() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);

        BDDMockito
                .given(departmentRepository.findById(employeeRequest.getDepartmentId()))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.saveEmployee(employeeRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Department not found!");
    }

    @Test
    public void saveEmployee_func_should_throw_exception_when_office_not_found() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);

        BDDMockito
                .given(departmentRepository.findById(employeeRequest.getDepartmentId()))
                .willReturn(Optional.of(new Department()));

        BDDMockito
                .given(officeRepository.findById(employeeRequest.getOfficeId()))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.saveEmployee(employeeRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Office not found!");
    }

    @Test
    public void saveEmployee_function_should_be_called_if_office_and_department_exists() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Hatice");
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);
        employeeRequest.setGender("FEMALE");
        employeeRequest.setPosition("SOFTWARE_ENGINEER");
        employeeRequest.setMaritalStatus("MARRIED");

        Department department = new Department();
        Office office = new Office();

        BDDMockito
                .given(departmentRepository.findById(employeeRequest.getDepartmentId()))
                .willReturn(Optional.of(department));

        BDDMockito
                .given(officeRepository.findById(employeeRequest.getOfficeId()))
                .willReturn(Optional.of(office));

        //when
        employeeService.saveEmployee(employeeRequest);

        // then
        ArgumentCaptor<Employee> savedCaptor = ArgumentCaptor.forClass(Employee.class);
        Mockito
                .verify(employeeRepository, times(1))
                .save(savedCaptor.capture());
        assertThat(savedCaptor.getValue().getName()).isEqualTo("Hatice");
    }

    @Test
    public void getEmployeeById_func_should_throw_exception_when_employee_not_found() {
        //given
        Long id = 1L;

        BDDMockito
                .given(employeeRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.getEmployeeById(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Employee not found!");
    }

    @Test
    public void deleteEmployeeById_func_should_not_delete_payrolls_if_there_is_no_payroll() {
        //given
        Long id = 1L;

        BDDMockito
                .given(payrollRepository.findByEmployeeId(id))
                .willReturn(Collections.emptyList());

        //when
        employeeService.deleteEmployeeById(id);

        //then
        Mockito
                .verify(payrollRepository, times(0))
                .deleteAll(Collections.emptyList());
        Mockito
                .verify(employeeRepository, times(1))
                .deleteById(id);
    }

    @Test
    public void updateEmployee_func_should_throw_exception_when_employee_not_found() {
        //given
        Long id = 1L;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Hatice");
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);
        employeeRequest.setGender("FEMALE");
        employeeRequest.setPosition("SOFTWARE_ENGINEER");
        employeeRequest.setMaritalStatus("MARRIED");

        BDDMockito
                .given(employeeRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.updateEmployee(id, employeeRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Employee not found!");
    }

    @Test
    public void updateEmployee_func_should_throw_exception_when_department_not_found() {
        //given
        Long id = 1L;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Hatice");
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);
        employeeRequest.setGender("FEMALE");
        employeeRequest.setPosition("SOFTWARE_ENGINEER");
        employeeRequest.setMaritalStatus("MARRIED");

        BDDMockito
                .given(employeeRepository.findById(id))
                .willReturn(Optional.of(new Employee()));

        BDDMockito
                .given(departmentRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.updateEmployee(id, employeeRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Department not found!");
    }

    @Test
    public void updateEmployee_func_should_throw_exception_when_office_not_found() {
        //given
        Long id = 1L;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Hatice");
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);
        employeeRequest.setGender("FEMALE");
        employeeRequest.setPosition("SOFTWARE_ENGINEER");
        employeeRequest.setMaritalStatus("MARRIED");

        BDDMockito
                .given(employeeRepository.findById(id))
                .willReturn(Optional.of(new Employee()));

        BDDMockito
                .given(departmentRepository.findById(employeeRequest.getDepartmentId()))
                .willReturn(Optional.of(new Department()));

        BDDMockito
                .given(officeRepository.findById(employeeRequest.getOfficeId()))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.updateEmployee(id, employeeRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Office not found!");
    }

    @Test
    public void updateEmployee_func_should_call_save_with_correct_mapping() {
        //given
        Long id = 1L;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Hatice");
        employeeRequest.setDepartmentId(1L);
        employeeRequest.setOfficeId(1L);
        employeeRequest.setGender("FEMALE");
        employeeRequest.setPosition("SOFTWARE_ENGINEER");
        employeeRequest.setMaritalStatus("MARRIED");

        BDDMockito
                .given(employeeRepository.findById(id))
                .willReturn(Optional.of(new Employee()));

        BDDMockito
                .given(departmentRepository.findById(employeeRequest.getDepartmentId()))
                .willReturn(Optional.of(new Department()));

        BDDMockito
                .given(officeRepository.findById(employeeRequest.getOfficeId()))
                .willReturn(Optional.of(new Office()));

        //when
        employeeService.updateEmployee(id, employeeRequest);

        //then
        ArgumentCaptor<Employee> savedCaptor = ArgumentCaptor.forClass(Employee.class);
        Mockito
                .verify(employeeRepository, times(1))
                .save(savedCaptor.capture());
        assertThat(savedCaptor.getValue().getName()).isEqualTo("Hatice");
    }

    @Test
    public void updateOfficeOfEmployeesByDepartmentId_func_should_throw_exception_when_department_not_found() {
        //given
        Long departmentId = 1L;
        Long officeId = 1L;

        BDDMockito
                .given(departmentRepository.findById(departmentId))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.updateOfficeOfEmployeesByDepartmentId(departmentId, officeId));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Department not found!");
    }

    @Test
    public void updateOfficeOfEmployeesByDepartmentId_func_should_throw_exception_when_office_not_found() {
        //given
        Long departmentId = 1L;
        Long officeId = 1L;

        BDDMockito
                .given(departmentRepository.findById(departmentId))
                .willReturn(Optional.of(new Department()));

        BDDMockito
                .given(officeRepository.findById(officeId))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> employeeService.updateOfficeOfEmployeesByDepartmentId(departmentId, officeId));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Office not found!");
    }

    @Test
    public void updateOfficeOfEmployeesByDepartmentId_func_should_update_offices_of_found_employees() {
        //given
        Long departmentId = 1L;
        Long officeId = 1L;

        Office office = new Office();
        office.setName("OfficeTest");

        Employee employee = new Employee();
        employee.setName("Hatice");

        BDDMockito
                .given(departmentRepository.findById(departmentId))
                .willReturn(Optional.of(new Department()));

        BDDMockito
                .given(officeRepository.findById(officeId))
                .willReturn(Optional.of(office));

        BDDMockito
                .given(employeeRepository.findByDepartmentId(departmentId))
                .willReturn(List.of(employee));

        //when
        employeeService.updateOfficeOfEmployeesByDepartmentId(departmentId, officeId);

        //then
        ArgumentCaptor<List<Employee>> savedCaptor = ArgumentCaptor.forClass(List.class);
        Mockito
                .verify(employeeRepository, times(1))
                .saveAll(savedCaptor.capture());

        savedCaptor.getValue().forEach(captor -> {
            assertThat(captor.getOffice().getName()).isEqualTo("OfficeTest");
        });

    }
}