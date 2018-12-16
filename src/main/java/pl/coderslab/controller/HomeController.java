package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Tweet;
import pl.coderslab.repository.TweetRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TweetRepository tweetRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("tweets", getAllTweets());
        return "homePage";
    }

    @ModelAttribute
    public List<Tweet> getAllTweets(){
        List<Tweet> tweets = tweetRepository.findAll();
        List<Tweet> newTweets = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            newTweets.add(i, tweets.get(tweets.size() - i - 1));
        }
        return newTweets;
    }
}
