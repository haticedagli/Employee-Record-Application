package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.dto.OfficeRequest;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.OfficeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private OfficeRepository officeRepository;

    @InjectMocks
    private OfficeService officeService;

    @Test
    public void getOfficeById_func_should_throw_exception_when_office_not_found() {
        //given
        Long id = 1L;

        BDDMockito
                .given(officeRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> officeService.getOfficeById(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Office not found!");
    }

    @Test
    public void deleteOffice_func_should_throw_exception_if_there_is_employees_in_this_department() {
        //given
        Long id = 1L;

        BDDMockito
                .given(employeeRepository.existsByOfficeId(id))
                .willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> officeService.deleteOffice(id));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("You have to remove all employees who is in this office.");
    }

    @Test
    public void updateOffice_func_should_throw_exception_if_office_is_not_found() {
        //given
        Long id = 1L;

        OfficeRequest request = new OfficeRequest();
        request.setName("Office Edirne");

        BDDMockito
                .given(officeRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> officeService.updateOffice(id, request));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Office not found!");
    }

    @Test
    public void updateOffice_func_should_save_office_with_correct_mapping() {
        //given
        Long id = 1L;

        OfficeRequest request = new OfficeRequest();
        request.setName("Office Edirne");

        BDDMockito
                .given(officeRepository.findById(id))
                .willReturn(Optional.of(new Office()));

        //when
        officeService.updateOffice(id, request);

        //then
        ArgumentCaptor<Office> savedCaptor = ArgumentCaptor.forClass(Office.class);
        Mockito
                .verify(officeRepository, times(1))
                .save(savedCaptor.capture());
        assertThat(savedCaptor.getValue().getName()).isEqualTo("Office Edirne");
    }
}