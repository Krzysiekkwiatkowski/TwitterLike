package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/twitter")
@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model){
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid @ModelAttribute User user, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "registerForm";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        session.setAttribute("user", user);
        return "homePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute User user, BindingResult result, Model model, HttpSession session){
        User loadedUser = userRepository.findByEmail(user.getEmail());
        if(loadedUser == null){
            model.addAttribute("error", "error");
            return "loginForm";
        } else {
            boolean check = BCrypt.checkpw(user.getPassword(), loadedUser.getPassword());
            if(check == false){
                model.addAttribute("error", "error");
                return "loginForm";
            }
        }
        session.setAttribute("user", loadedUser);
        return "redirect:/twitter/home";
    }
}
