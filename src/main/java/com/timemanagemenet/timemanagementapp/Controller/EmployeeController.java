package com.timemanagemenet.timemanagementapp.Controller;
import com.timemanagemenet.timemanagementapp.Entity.SenderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @GetMapping(value="/admin")
    public SenderResponse adminEndPoint() {
        return new SenderResponse("Hello From Admin");
    }
    @GetMapping(value="/employee")
    public SenderResponse managerEndPoint() {
        return new SenderResponse("Hello From Manager");
    }

}
