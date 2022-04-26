package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "all-users";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "info-user";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("updateUser") int id, Model model) {
        User userById = userService.getUser(id);
        model.addAttribute("user", userById);

        return "info-user";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("deleteUser") int id) {
        userService.deleteUser(id);

        return "redirect:/";
    }
}
