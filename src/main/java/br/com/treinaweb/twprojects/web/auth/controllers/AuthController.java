package br.com.treinaweb.twprojects.web.auth.controllers;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojects.core.repositories.EmployeeRepository;
import br.com.treinaweb.twprojects.core.services.auth.AuthenticatedUser;
import br.com.treinaweb.twprojects.web.auth.dtos.UpdatePasswordForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/update-password")
    public ModelAndView updatePassword() {
        var model = Map.of("updatePasswordForm", new UpdatePasswordForm());
        return new ModelAndView("auth/update-password", model);
    }

    @PostMapping("/update-password")
    public String updatePassword(
        @Valid UpdatePasswordForm updatePasswordForm,
        BindingResult result,
        Authentication authentication
    ) {
        if (result.hasErrors()) {
            return "/auth/update-password";
        }
        var employee = ((AuthenticatedUser) authentication.getPrincipal()).getEmployee();
        var passwordMatches = passwordEncoder.matches(
            updatePasswordForm.getCurrentPassword(), 
            employee.getPassword()
        );
        if (passwordMatches) {
            var newPasswordHash = passwordEncoder.encode(updatePasswordForm.getNewPassword());
            employee.setPassword(newPasswordHash);
            employeeRepository.save(employee);
            return "redirect:/";
        }
        return "/auth/update-password";
    }
    
}
