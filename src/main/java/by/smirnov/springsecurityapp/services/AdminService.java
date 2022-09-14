package by.smirnov.springsecurityapp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doingAdminStuff(){
        System.out.println("Admin stuff!");
    }
}
