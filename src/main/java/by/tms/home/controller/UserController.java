package by.tms.home.controller;

import by.tms.home.dao.UserDao;
import by.tms.home.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public ModelAndView getSavePage(ModelAndView modelAndView) {
        modelAndView.setViewName("savePage");
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView saveUserToDatabase(@ModelAttribute("newUser") User user, ModelAndView modelAndView) {
        modelAndView.setViewName("savePage");
        userDao.saveUser(user);
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAllUsersFromDatabase(ModelAndView modelAndView) {
        modelAndView.setViewName("allUsersPage");
        List<User> allUsers = (List<User>) userDao.getAllUsers();
        modelAndView.addObject("users", allUsers);
        return modelAndView;
    }

    @GetMapping("/info/{username}")
    public ModelAndView getUserInfoByUsername(@PathVariable("username")String username, ModelAndView modelAndView) {
        modelAndView.setViewName("userInfoPage");
        User userByUsername = (User) userDao.getUserByUsername(username);
        modelAndView.addObject("user", userByUsername);
        return modelAndView;
    }

}
