package com.springboot.jpaspecification.config;

import com.springboot.jpaspecification.entity.Department;
import com.springboot.jpaspecification.entity.Employee;
import com.springboot.jpaspecification.repository.DepartmentRepository;
import com.springboot.jpaspecification.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class InitialDataConfig implements CommandLineRunner {

    private final DepartmentRepository departmentRepository ;
    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {
        generateFakeDepartments(10);
        generateFakeEmployees(100);
        log.info("Generated Fake Data");
    }

    private void generateFakeDepartments(int amount) {
        for (int i = 0; i < amount; i++) {
            Department department = Department.builder()
                    .name(faker.company().industry())
                    .build();
            departmentRepository.save(department);
        }
    }

    private void generateFakeEmployees(int amount) {
        for (int i = 0; i < amount; i++) {
            Employee employee = Employee.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .birthdayDate(faker.date().birthday())
                    .age(faker.number().numberBetween(20, 60))
                    .companyName(faker.company().name())
                    .department(randomlyChooseDepartment())
                    .build();
            employeeRepository.save(employee);
        }
    }

    private Department randomlyChooseDepartment() {
        List<Department> departmentList = departmentRepository.findAll();

        return departmentList.get(faker.number().numberBetween(0, departmentList.size()));
    }

}
