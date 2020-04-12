package com.amantya.jwt.publisher;

import com.amantya.jwt.Exception.LibraryResourceAlreadyExistsException;
import com.amantya.jwt.publisher.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    private PublisherService publisherService ;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/{publisherID}")
    public Publisher getPublisher(@PathVariable Integer publisherID)
    {
        return new Publisher(publisherID,"Tata McGraw Hill","ttt@gmail.com","123-456-789");
    }

    @PostMapping
    public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher)
    {
        try {
            publisher = publisherService.addPublisher(publisher);
        } catch (LibraryResourceAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(publisher,HttpStatus.CREATED);
    }
}
