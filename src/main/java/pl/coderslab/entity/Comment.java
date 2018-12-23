package pl.coderslab.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tweet post;
    private Date created;
    @NotBlank
    @Size(max = 60)
    private String text;

    public Comment() {
        this.created = Date.valueOf(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getPost() {
        return post;
    }

    public void setPost(Tweet post) {
        this.post = post;
    }

    public Date getCreated() {
        return created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
