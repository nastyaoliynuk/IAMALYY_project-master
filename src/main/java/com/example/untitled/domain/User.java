package com.example.untitled.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;

    @Column(name = "phone-number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "b-day")
    private LocalDate birthday;

    @Column(name = "info-about-me")
    private String infoAboutMe;

    @Column(name = "location")
    private String location;

    @Column(name = "avatar")
    private String avatar;

    public enum UserStatus {
        NONAME,
        MODEL,
        PHOTOGRAF
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (username.contains(" ")) {
            throw new IllegalArgumentException("Username cannot contain spaces");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.contains(" ")) {
            throw new IllegalArgumentException("Password cannot contain spaces");
        }
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws NumberParseException {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            if (isValidPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            } else {
                this.phoneNumber = null; // Set to null if phone number is not valid
            }
        } else {
            this.phoneNumber = null; // Set to null if phone number is empty or null
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            this.email = null; // Set to null if email is empty or null
        }
        else {
            if (isValidEmail(email)) {
                this.email = email;
            } else {
                this.email = null; // Set to null if email is not valid
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = null;
        }
else{
        this.name = name;
    }}

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDate getB_day() {
        return birthday;
    }

    public void setB_day(LocalDate b_day) {
        if (b_day==null) {
            this.birthday = null;
        } else {
            this.birthday = b_day;
        }
    }

    public String getInfo_about_me() {
        return infoAboutMe;
    }

    public void setInfo_about_me(String info_about_me) {
        if (info_about_me == null || info_about_me.trim().isEmpty()) {
            this.infoAboutMe = null;
        }else{

        this.infoAboutMe = info_about_me;    }}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            this.location = null;
        }
else{
        this.location = location;   }  }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // Method for validating phone number
    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false; // Empty or null phone number is not valid
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            // Встановлюємо код країни як null, оскільки ми не можемо визначити його з номеру телефону
            return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, null));
        } catch (NumberParseException e) {
            // Якщо виникає виняток, то номер телефону не вірний
            return false;
        }
    }

    // Method for validating email
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false; // Empty or null email is not valid
        }

        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}