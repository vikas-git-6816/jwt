package com.amantya.jwt.publisher;

import javax.persistence.*;

@Entity
@Table(name = "PUBLISHER")
public class PublisherEntity {
    @Column(name = "Publisher_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "publisherID_generator")
    @SequenceGenerator(name = "publisherID_generator", sequenceName = "PUBLISHER_SEQUENCE", allocationSize = 50)
    private int publisherID ;

    @Column(name = "Name")
    private String name ;

    @Column(name = "Email_Id")
    private String email ;

    @Column(name = "Phone_Number")
    private String phoneNumber ;

    public PublisherEntity() {
    }

    public PublisherEntity(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
