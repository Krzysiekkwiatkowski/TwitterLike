package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        model.addAttribute("commentList", tweetComments(tweet));
        return "tweetDetails";
    }

    @ModelAttribute("userTweets")
    public List<Tweet> allUserTweets(String email){
        return tweetRepository.findByUser(userRepository.findByEmail(email));
    }

    @RequestMapping("/comment/{id}")
    public String addComment(@PathVariable("id") Long id, @RequestParam("text") String text, HttpSession session){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/login";
        } else {
            User user = (User)object;
            User loadedUser = userRepository.findByEmail(user.getEmail());
            Tweet tweet = tweetRepository.findOne(id);
            Comment comment = new Comment();
            comment.setUser(loadedUser);
            comment.setText(text);
            comment.setPost(tweet);
            commentRepository.save(comment);
        }
        return "redirect:/user/" + id;
    }

    public List<Comment> tweetComments(Tweet tweet){
        List<Comment> comments = commentRepository.findByPost(tweet);
        List<Comment> commentList = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            commentList.add(i, comments.get(comments.size() - 1 - i));
        }
        return commentList;
    }
}
