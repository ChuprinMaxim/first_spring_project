package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class AppController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());

        return "all-users";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());

        return "info-user";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("updateUser") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));

        return "info-user";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("deleteUser") int id) {
        userService.deleteUser(id);

        return "redirect:/";
    }
}
