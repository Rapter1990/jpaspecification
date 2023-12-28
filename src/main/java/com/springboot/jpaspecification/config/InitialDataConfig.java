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

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

/**
 * {@link Configuration} class named {@link InitialDataConfig} for initializing fake data using Faker library.
 * Implements the {@link CommandLineRunner} interface to execute data generation logic on application startup.
 */
@Configuration
@RequiredArgsConstructor
@Log4j2
public class InitialDataConfig implements CommandLineRunner {

    private final DepartmentRepository departmentRepository ;
    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    /**
     * Executes the data generation logic on application startup.
     *
     * @param args The command-line arguments passed to the application.
     * @throws Exception If an error occurs during data generation.
     */
    @Override
    public void run(String... args) throws Exception {
        generateFakeDepartments(10);
        generateFakeEmployees(100);
        log.info("Generated Fake Data");
    }

    /**
     * Generates fake department data and saves it to the department repository.
     *
     * @param amount The number of fake departments to generate.
     */
    private void generateFakeDepartments(int amount) {
        for (int i = 0; i < amount; i++) {
            Department department = Department.builder()
                    .name(faker.company().industry())
                    .build();
            departmentRepository.save(department);
        }
    }

    /**
     * Generates fake employee data and saves it to the employee repository.
     *
     * @param amount The number of fake employees to generate.
     */
    private void generateFakeEmployees(int amount) {
        for (int i = 0; i < amount; i++) {

            LocalDate birthdayDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = calculateAge(birthdayDate);

            Employee employee = Employee.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .birthdayDate(birthdayDate)
                    .age(age)
                    .companyName(faker.company().name())
                    .department(randomlyChooseDepartment())
                    .build();
            employeeRepository.save(employee);
        }
    }

    /**
     * Calculates the age based on the given birthday date.
     *
     * @param birthday The birthday date of the employee.
     * @return The calculated age.
     */
    private int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }

    /**
     * Randomly selects a department from the existing department list.
     *
     * @return The randomly chosen department.
     */
    private Department randomlyChooseDepartment() {
        List<Department> departmentList = departmentRepository.findAll();

        return departmentList.get(faker.number().numberBetween(0, departmentList.size()));
    }

}
