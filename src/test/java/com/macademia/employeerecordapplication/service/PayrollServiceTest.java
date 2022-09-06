package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Payroll;
import com.macademia.employeerecordapplication.model.dto.PayrollRequest;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.PayrollRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PayrollServiceTest {

    @Mock
    private PayrollRepository payrollRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private PayrollService payrollService;

    @Test
    public void getPayrollById_func_should_throw_exception_when_payroll_not_found() {
        //given
        Long id = 1L;

        BDDMockito
                .given(payrollRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> payrollService.getPayrollById(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Payroll not found!");
    }

    @Test
    public void savePayroll_func_should_throw_exception_when_employee_not_found() {
        //given
        PayrollRequest request = new PayrollRequest();
        request.setEmployeeId(1L);

        BDDMockito
                .given(employeeRepository.findById(request.getEmployeeId()))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> payrollService.savePayroll(request));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Employee not found!");
    }

    @Test
    public void savePayroll_func_should_call_save_with_correct_mapping() {
        //given
        PayrollRequest request = new PayrollRequest();
        request.setAmountOfFeePaid(1000D);
        request.setEmployeeId(1L);

        BDDMockito
                .given(employeeRepository.findById(request.getEmployeeId()))
                .willReturn(Optional.of(new Employee()));

        //when
        payrollService.savePayroll(request);

        //then
        ArgumentCaptor<Payroll> savedCaptor = ArgumentCaptor.forClass(Payroll.class);
        Mockito
                .verify(payrollRepository, times(1))
                .save(savedCaptor.capture());
        assertThat(savedCaptor.getValue().getAmountOfFeePaid()).isEqualTo(1000D);
    }

    @Test
    public void deletePayrollById_func_should_throw_exception_when_payroll_not_found() {
        //given
        Long id = 1L;

        BDDMockito
                .given(payrollRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> payrollService.deletePayrollById(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Payroll not found!");
    }

    @Test
    public void updatePayroll_func_should_throw_exception_when_payroll_not_found() {
        //given
        Long id = 1L;

        PayrollRequest request = new PayrollRequest();
        request.setAmountOfFeePaid(1000D);
        request.setEmployeeId(1L);

        BDDMockito
                .given(payrollRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> payrollService.updatePayroll(id, request));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Payroll not found!");
    }

    @Test
    public void updatePayroll_func_should_throw_exception_when_employee_not_found() {
        //given
        Long id = 1L;

        PayrollRequest request = new PayrollRequest();
        request.setAmountOfFeePaid(1000D);
        request.setEmployeeId(1L);

        BDDMockito
                .given(payrollRepository.findById(id))
                .willReturn(Optional.of(new Payroll()));

        BDDMockito
                .given(employeeRepository.findById(request.getEmployeeId()))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> payrollService.updatePayroll(id, request));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Employee not found!");
    }

    @Test
    public void updatePayroll_func_should_call_save_with_correct_mapping() {
        //given
        Long id = 1L;

        PayrollRequest request = new PayrollRequest();
        request.setAmountOfFeePaid(1000D);
        request.setEmployeeId(1L);

        BDDMockito
                .given(payrollRepository.findById(id))
                .willReturn(Optional.of(new Payroll()));

        BDDMockito
                .given(employeeRepository.findById(request.getEmployeeId()))
                .willReturn(Optional.of(new Employee()));

        //when
        payrollService.updatePayroll(id, request);

        //then
        ArgumentCaptor<Payroll> savedCaptor = ArgumentCaptor.forClass(Payroll.class);
        Mockito
                .verify(payrollRepository, times(1))
                .save(savedCaptor.capture());
        assertThat(savedCaptor.getValue().getAmountOfFeePaid()).isEqualTo(1000D);;
    }
}