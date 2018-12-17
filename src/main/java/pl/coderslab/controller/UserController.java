package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @RequestMapping("/user")
    public String allTweets(Model model, HttpSession session){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/login";
        } else {
            User user = (User) object;
            model.addAttribute("userTweets", allUserTweets(user.getEmail()));
            return "userTweets";
        }
    }

    @RequestMapping("/user/{id}")
    public String tweetDetails(@PathVariable("id") Long id, Model model){
        Tweet tweet = tweetRepository.findOne(id);
        model.addAttribute("tweet", tweet);
        return "tweetDetails";
    }

    @ModelAttribute("userTweets")
    public List<Tweet> allUserTweets(String email){
        return tweetRepository.findByUser(userRepository.findByEmail(email));
    }
}
