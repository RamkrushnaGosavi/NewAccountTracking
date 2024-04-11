package com.example.JWT.controller;


import com.example.JWT.Dto.AuthRequest;
import com.example.JWT.model.Employee;
import com.example.JWT.service.EmployeeService;
import com.example.JWT.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {


    @Autowired
    JWTService jwtService;

    @Autowired
    EmployeeService employeeService;



    @PostMapping("/register")
    public String registerEmployee(@RequestBody Employee employee)
    {
        return employeeService.createEmployee(employee);
    }


//    @PostMapping("/authenticate")
//    public String getAuthenticateToken(@RequestBody Employee employee)
//    {
//        return jwtService.generateToken(employee.getUserName());
//    }


    @PostMapping("/signIn")
    public String logedInEmployee(@RequestBody AuthRequest authRequest)
    {
        return employeeService.signInEmp(authRequest);
    }

}
