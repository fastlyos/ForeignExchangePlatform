package pl.forex.trading_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.forex.trading_platform.domain.user.Role;
import pl.forex.trading_platform.domain.user.User;
import pl.forex.trading_platform.repository.UserRepository;
import pl.forex.trading_platform.validator.ValidationGroupUniqueEmail;

import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationProcess(@Validated({ValidationGroupUniqueEmail.class, Default.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("registration errors: " + result.getAllErrors().toString());
            return "registration";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setActive(true);
        user.setRoles(roles);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setBalance(500000);
        user.setBlockedAmount(0);
        user.setActive(true);
        userRepository.save(user);
        model.addAttribute("registrationResult", 1);
        return "/login";
    }
}
