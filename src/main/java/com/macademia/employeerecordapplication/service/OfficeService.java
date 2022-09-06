package com.macademia.employeerecordapplication.service;

import com.macademia.employeerecordapplication.exception.BusinessException;
import com.macademia.employeerecordapplication.exception.NotFoundException;
import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.dto.OfficeRequest;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveOffice(OfficeRequest officeRequest){
        officeRepository.save(officeRequest.toOffice());
    }

    public List<Office> getAllOffices(){
        return officeRepository.findAll();
    }

    public Office getOfficeById(Long id){
        return officeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Office not found!"));
    }

    public void deleteOffice(Long id){
        if(employeeRepository.existsByOfficeId(id)){
            throw new BusinessException("You have to remove all employees who is in this office.");
        }
        Office office = getOfficeById(id);
        officeRepository.delete(office);
    }

    public Office updateOffice(Long id, OfficeRequest officeRequest){
        officeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Office not found!"));
        Office office = officeRequest.toOffice();
        office.setId(id);
        return officeRepository.save(office);
    }
}
