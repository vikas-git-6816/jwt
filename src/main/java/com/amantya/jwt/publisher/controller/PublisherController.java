package com.amantya.jwt.publisher.controller;

import com.amantya.jwt.publisher.model.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    @GetMapping(path = "/{publisherID}")
    public Publisher getPublisher(@PathVariable String publisherID)
    {
        return new Publisher(publisherID,"Tata McGraw Hill","ttt@gmail.com","123-456-789");
    }
}
