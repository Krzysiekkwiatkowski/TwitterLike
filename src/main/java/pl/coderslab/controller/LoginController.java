package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String postRegister(@Valid @ModelAttribute User user, BindingResult result, HttpSession session, Model model){
        if(result.hasErrors()){
            return "registerForm";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        for (User checkEmail : userRepository.findAll()) {
            if(checkEmail.getEmail().equals(user.getEmail())){
                model.addAttribute("notUnique", "notUnique");
                return "registerForm";
            }
        }
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/twitter/home";
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.GET)
    public String getEdit(Model model, HttpSession session){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        }
        User user = (User)object;
        User loadedUser = userRepository.findByEmail(user.getEmail());
        model.addAttribute("user", loadedUser);
        return "editAccountForm";
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public String postEdit(@Valid @ModelAttribute User user, BindingResult result, HttpSession session, @RequestParam("dateOfBirth") String dateOfBirth){
        if(result.hasErrors()){
            return "editAccountForm";
        }
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/twitter/home";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String getResetPassword(Model model, HttpSession session){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        }
        User user = (User)object;
        User loadedUser = userRepository.findByEmail(user.getEmail());
        model.addAttribute("user", loadedUser);
        return "resetPasswordForm";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String postResetPassword(@Valid @ModelAttribute User user, BindingResult result, @RequestParam("newPassword") String newPassword, HttpSession session, Model model){
        User loadedUser = userRepository.findByEmail(user.getEmail());
        boolean check = BCrypt.checkpw(user.getPassword(), loadedUser.getPassword());
        if(check == false) {
            model.addAttribute("wrongPassword", "wrongPassword");
            return "resetPasswordForm";
        } else {
            loadedUser.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            userRepository.save(loadedUser);
            session.setAttribute("user", loadedUser);
            return "redirect:/twitter/home";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "loginForm";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogout(HttpSession session){
        Object object = session.getAttribute("user");
        if(object != null){
            session.setAttribute("user", null);
        }
        return "redirect:/twitter/home";
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
