package com.macademia.employeerecordapplication;

import com.macademia.employeerecordapplication.model.Department;
import com.macademia.employeerecordapplication.model.Employee;
import com.macademia.employeerecordapplication.model.Office;
import com.macademia.employeerecordapplication.model.Payroll;
import com.macademia.employeerecordapplication.model.enums.Gender;
import com.macademia.employeerecordapplication.model.enums.MaritalStatus;
import com.macademia.employeerecordapplication.model.enums.Position;
import com.macademia.employeerecordapplication.repository.DepartmentRepository;
import com.macademia.employeerecordapplication.repository.EmployeeRepository;
import com.macademia.employeerecordapplication.repository.OfficeRepository;
import com.macademia.employeerecordapplication.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;

@Component
public class MockDataGenerator {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private PayrollRepository payrollRepository;

    @PostConstruct
    public void run() {
        Department hrDepartment = departmentRepository.save(new Department(null, "Human Resource", "HR", new ArrayList<>()));
        Department techDepartment = departmentRepository.save(new Department(null, "Technology", "Tech", new ArrayList<>()));

        Office istanbulOffice = officeRepository.save(new Office(null, "Office Istanbul", "İstanbul", "Halide Edip Adıvar Mahallesi – Darülaceze Caddesi No:3A BOMONTİ / ŞİŞLİ – İSTANBUL", 340L, new ArrayList<>()));
        Office ankaraOffice = officeRepository.save(new Office(null, "Office Ankara", "İstanbul", "Sağlık Mah. Mithat Paşa Cad. No:3 P.k. 06430 Sıhhiye-Çankaya/ANKARA", 120L, new ArrayList<>()));

        Employee employee1 = employeeRepository.save(new Employee(
                null,
                "Hatice",
                "Dağlı",
                "1236548965",
                "05556667788",
                "Sinanpaşa mah. Beşiktaş / İstanbul",
                Gender.FEMALE,
                Position.SOFTWARE_ENGINEER,
                "applicantPhoto1.jpeg",
                MaritalStatus.MARRIED,
                new Date(),
                null,
                techDepartment,
                istanbulOffice,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        Employee employee2 = employeeRepository.save(new Employee(
                null,
                "Burak",
                "Yılmaz",
                "32145632561",
                "05554442211",
                "Mitatpaşa Cad. Çankaya / Ankara",
                Gender.MALE,
                Position.QUALITY_ENGINEER,
                "applicantPhoto2.jpeg",
                MaritalStatus.MARRIED,
                new Date(),
                null,
                techDepartment,
                ankaraOffice,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        Employee employee3 = employeeRepository.save(new Employee(
                null,
                "Fatih",
                "Gök",
                "48702934571",
                "05454566545",
                "Sağlık Mah. Çankaya / Ankara",
                Gender.MALE,
                Position.TALENT_HUNTER,
                "applicantPhoto2.jpeg",
                MaritalStatus.SINGLE,
                new Date(),
                null,
                hrDepartment,
                ankaraOffice,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        payrollRepository.save(new Payroll(
                null,
                "id1234klm",
                "7654328754",
                30,
                1500D,
                1000D,
                new Date(),
                employee1
        ));

        payrollRepository.save(new Payroll(
                null,
                "id1234klm",
                "7654328754",
                30,
                1500D,
                1000D,
                new Date(),
                employee1
        ));
    }
}

