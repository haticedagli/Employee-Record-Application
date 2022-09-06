package com.macademia.employeerecordapplication.controller;

import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.dto.OfficeRequest;
import com.macademia.employeerecordapplication.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @GetMapping("/{id}")
    public void get(@PathVariable Long id){
        officeService.getOfficeById(id);
    }

    @PostMapping
    public void save(@RequestBody OfficeRequest officeRequest){
        officeService.saveOffice(officeRequest);
    }

    @PutMapping("/{id}")
    public Office update(@PathVariable Long id, @RequestBody OfficeRequest officeRequest){
        return officeService.updateOffice(id, officeRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        officeService.deleteOffice(id);
    }

    @GetMapping
    public List<Office> getAll(){
        return officeService.getAllOffices();
    }

}
