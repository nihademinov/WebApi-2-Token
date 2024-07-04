package org.example.webapi2.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

@GetMapping("/api/getAdmin")
    public String getAdmin()
{
    return "admin";
}

}
