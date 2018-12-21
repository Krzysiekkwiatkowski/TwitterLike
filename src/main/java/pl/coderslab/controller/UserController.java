package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/twitter")
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
            return "redirect:/twitter/login";
        } else {
            User user = (User) object;
            model.addAttribute("userTweets", allUserTweets(user.getEmail()));
            model.addAttribute("comments", allComments());
            return "userTweets";
        }
    }

    @RequestMapping("/user/{id}")
    public String tweetDetails(@PathVariable("id") Long id, Model model, HttpSession session){
        Object object = session.getAttribute("user");
        User user = (User)object;
        User loadedUser = userRepository.findByEmail(user.getEmail());
        List<Tweet> tweets = allUserTweets(loadedUser.getEmail());
        for (Tweet checkTweets : tweets) {
            if(checkTweets.getId() == id){
                model.addAttribute("tweet", checkTweets);
                model.addAttribute("commentList", tweetComments(checkTweets));
                return "tweetDetails";
            }
        }
        model.addAttribute("wrongUser", "wrongUser");
        return "tweetDetails";
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public String allowComment(@PathVariable("id") Long id, HttpSession session, Model model){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        } else {
            model.addAttribute("allow", id);
            model.addAttribute("tweets", getAllTweets());
            return "homePage";
        }
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") Long id, @RequestParam("text") String text, HttpServletRequest request, HttpSession session){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        } else {
            User user = (User) object;
            User loadedUser = userRepository.findByEmail(user.getEmail());
            Tweet tweet = tweetRepository.findOne(id);
            Comment comment = new Comment();
            comment.setUser(loadedUser);
            comment.setText(text);
            comment.setPost(tweet);
            commentRepository.save(comment);
            String allow = request.getParameter("allow");
            if (allow != null) {
                return "redirect:/twitter/user";
            } else {
                return "redirect:/twitter/user/" + id;
            }
        }
    }

    public List<Comment> tweetComments(Tweet tweet){
        List<Comment> comments = commentRepository.findByPost(tweet);
        List<Comment> commentList = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            commentList.add(i, comments.get(comments.size() - 1 - i));
        }
        return commentList;
    }

    @ModelAttribute("userTweets")
    public List<Tweet> allUserTweets(String email){
        return tweetRepository.findByUser(userRepository.findByEmail(email));
    }

    @ModelAttribute("comments")
    public List<Comment> allComments(){
        return commentRepository.findAll();
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
