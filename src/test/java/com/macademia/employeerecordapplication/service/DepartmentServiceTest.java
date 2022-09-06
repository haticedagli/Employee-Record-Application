package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.model.dto.DepartmentRequest;
import com.macademia.employeerecordapplication.repository.DepartmentRepository;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void getDepartmentById_func_should_throw_exception_when_department_not_found() {
        //given
        Long id = 1L;

        BDDMockito
                .given(departmentRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> departmentService.getDepartmentById(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Department not found.");
    }

    @Test
    public void deleteDepartment_func_should_throw_exception_when_there_is_employees_defined_for_this_department() {
        //given
        Long id = 1L;

        BDDMockito
                .given(employeeRepository.existsByDepartmentId(id))
                .willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> departmentService.deleteDepartment(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("You have to remove all employees who is in this department.");
    }

    @Test
    public void updateDepartment_func_should_throw_exception_if_there_is_no_department_found() {
        //given
        Long id = 1L;

        BDDMockito
                .given(departmentRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> departmentService.updateDepartment(id, new DepartmentRequest()));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Department not found.");
    }
}