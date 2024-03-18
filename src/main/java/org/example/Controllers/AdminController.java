package org.example.Controllers;

import org.example.models.forAdmin.*;
import org.example.services.AdminService;
import org.example.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/group/add")
    //@ResponseBody
    public String AddGroupPage(){
        //@RequestBody AddFormGrooup form
        //adminService.addNewGrooup(form);
        return "New group add";
    }
}
