package pl.coderslab.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/twitter")
@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @RequestMapping("/message")
    public String all(Model model, HttpSession session){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        }
        User user = (User)object;
        model.addAttribute("receivedMessageList", getAllReceivedMessages(user.getEmail()));
        model.addAttribute("sendMessageList", getAllSendMessages(user.getEmail()));
        return "allMessages";
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public String sendMessageGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("userId", userRepository.findOne(id));
        return "sendMessage";
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.POST)
    public String sendMessagePost(@PathVariable("id") Long id, @RequestParam("message") String message, HttpSession session, Model model){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        } else {
            User user = (User)object;
            User sender = userRepository.findByEmail(user.getEmail());
            User receiver = userRepository.findOne(id);
            if(sender.getId() == receiver.getId()) {
                model.addAttribute("tweets", getAllTweets());
                model.addAttribute("error", "error");
                return "homePage";
            } else {
                Message messageToSend = new Message();
                messageToSend.setMessage(message);
                messageToSend.setSender(sender);
                messageToSend.setReceiver(receiver);
                messageToSend.setView(false);
                messageRepository.save(messageToSend);
                return "redirect:/twitter/home";
            }
        }
    }

    @RequestMapping("/message/{id}/{view}")
    public String messageViewed(@PathVariable("id") Long id, @PathVariable("view") Boolean view, HttpSession session, Model model){
        Object object = session.getAttribute("user");
        if(object == null){
            return "redirect:/twitter/login";
        }
        User user = (User)object;
        User loadedUser = userRepository.findByEmail(user.getEmail());
        Message message = messageRepository.findOne(id);
        if(message.getReceiver().getId() == loadedUser.getId() && view == false){
            message.setView(true);
            messageRepository.save(message);
        }
        model.addAttribute("message", message);
        return "showMessage";
    }

    @ModelAttribute("receivedMessageList")
    public List<Message> getAllReceivedMessages(String email){
        List<Message> messages = messageRepository.findByReceiver(userRepository.findByEmail(email));
        for (Message message : messages) {
            String text = message.getMessage();
            if(text.length() > 30){
                message.setMessage(StringUtils.substring(text,0, 29));
            }
        }
        List<Message> receivedMessageList = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            receivedMessageList.add(i, messages.get(messages.size() - 1 - i));
        }
        return receivedMessageList;
    }

    @ModelAttribute("sendMessageList")
    public List<Message> getAllSendMessages(String email){
        List<Message> messages = messageRepository.findBySender(userRepository.findByEmail(email));
        for (Message message : messages) {
            String text = message.getMessage();
            if(text.length() > 30){
                message.setMessage(StringUtils.substring(text,0, 29));
            }
        }
        List<Message> sendMessageList = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            sendMessageList.add(i, messages.get(messages.size() - 1 - i));
        }
        return sendMessageList;
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
