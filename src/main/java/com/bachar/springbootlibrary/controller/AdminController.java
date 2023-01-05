package com.bachar.springbootlibrary.controller;

import com.bachar.springbootlibrary.requrestmodels.AddBookRequest;
import com.bachar.springbootlibrary.service.AdminService;
import com.bachar.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value = "Authorization") String token, AddBookRequest addBookRequest) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (admin == null || !admin.equals("admin@email.com")) {
            throw new Exception("Administration page only.");
        }
        adminService.postBook(addBookRequest);
    }

    @PostMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value = "Authorization") String token, Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (admin == null || !admin.equals("admin@email.com")) {
            throw new Exception("Administration page only.");
        }
        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader(value = "Authorization") String token, Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (admin == null || !admin.equals("admin@email.com")) {
            throw new Exception("Administration page only.");
        }
        adminService.decreaseBookQuantity(bookId);
    }

}
