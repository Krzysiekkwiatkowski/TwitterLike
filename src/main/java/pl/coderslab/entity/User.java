package pl.coderslab.entity;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @AssertTrue
    private Boolean enabled;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @Nullable
    private String dateOfBirth;
    private String aboutMe;
    private int phone;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        try{
            if(Date.valueOf(dateOfBirth).before(Date.valueOf(LocalDate.now()))) {
                this.dateOfBirth = dateOfBirth;
            } else {
                this.dateOfBirth = null;
            }
        } catch (IllegalArgumentException e){
            this.dateOfBirth = null;
        }
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
