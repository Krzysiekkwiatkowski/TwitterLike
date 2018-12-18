package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/twitter")
@Controller
public class HomeController {
    @Autowired
    private TweetRepository tweetRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeGet(Model model) {
        model.addAttribute("tweets", getAllTweets());
        return "homePage";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String homePost(@RequestParam("text") String text, HttpSession session) {
        if (text.isEmpty()) {
            return "redirect:/twitter/home";
        } else {
            Object object = session.getAttribute("user");
            if (object != null) {
                User user = (User) object;
                System.out.println(user.getEmail() + " " + user.getUsername());
                Tweet tweet = new Tweet();
                tweet.setUser(user);
                tweet.setText(text);
                tweetRepository.save(tweet);
            } else {
                return "redirect:/twitter/login";
            }
            return "redirect:/twitter/home";
        }
    }

    @ModelAttribute
    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        List<Tweet> newTweets = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            newTweets.add(i, tweets.get(tweets.size() - i - 1));
        }
        return newTweets;
    }
}
