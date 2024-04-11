package com.example.JWT.service;


import com.example.JWT.Dto.AuthRequest;
import com.example.JWT.model.Employee;
import com.example.JWT.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    public String Token;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JWTService jwtService;


    public String createEmployee(Employee employee) {
        Employee createdEmp = new Employee();

        if (employee != null) {
            createdEmp.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));

            createdEmp.setUsername(employee.getUsername());
            //   createdEmp.setPassword((employee.getPassword()));
            createdEmp = employeeRepo.save(createdEmp);

            Token = jwtService.generateToken(createdEmp.getUsername());

            return "Employee Created Succesfully " + employee.getUsername();
        } else {
            return "Somthing went worng!!";
        }
    }


    public String signInEmp(AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }


}
