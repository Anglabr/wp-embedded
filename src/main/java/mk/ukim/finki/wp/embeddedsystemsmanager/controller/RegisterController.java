package mk.ukim.finki.wp.embeddedsystemsmanager.controller;

import mk.ukim.finki.wp.embeddedsystemsmanager.model.enumerations.Role;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.InvalidArgumentsException;
import mk.ukim.finki.wp.embeddedsystemsmanager.model.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.embeddedsystemsmanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam Role role) {
        try{
            this.userService.register(username, password, repeatPassword, name, surname, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
