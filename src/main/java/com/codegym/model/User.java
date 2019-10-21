package com.codegym.model;

import org.codehaus.plexus.classworlds.strategy.Strategy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@Entity
@Table(name = "user")
public class User implements Validator {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(">>> call validate");

        User user = (User) target;
        String number = user.getPhoneNumber();
        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue("phoneNumber", "number.length");
            System.out.println(">> error number length");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("phoneNumber", "number.startsWith");
            System.out.println(">> error number empty");
        }
        if (!number.matches("(^$|[0-9]*$)")) {
            System.out.println(">> error number not in range 0-9");
            errors.rejectValue("phoneNumber", "number.matches");
        }

        String firstName = user.getFirstName();
        ValidationUtils.rejectIfEmpty(errors,"firstName","firstName.empty");
        if (firstName.length()<5 || firstName.length()>45){
            errors.rejectValue("firstName","firstName.length");
        }

        String lastName = user.getLastName();
        ValidationUtils.rejectIfEmpty(errors,"lastName","lastName.empty");
        if (lastName.length()<5 || lastName.length()>45){
            errors.rejectValue("lastName","lastName.length");
        }

        int age = user.getAge();
        if (age <18){
            errors.rejectValue("age","age.value");
        }

        String email = user.getEmail();
        if (!email.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")){
            errors.rejectValue("email","email.matches");
        }
    }
}
