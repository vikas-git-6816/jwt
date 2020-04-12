package com.amantya.jwt.publisher;

import com.amantya.jwt.Exception.LibraryResourceAlreadyExistsException;
import com.amantya.jwt.Exception.LibraryResourceNotFoundException;
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
    public ResponseEntity<?> getPublisher(@PathVariable Integer publisherID)
    {
        Publisher publisher = null ;
        try {
           publisher = publisherService.getPublisher(publisherID);
        }catch (LibraryResourceNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
        }
        return  new ResponseEntity<>(publisher, HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher)
    {
        try {
             publisherService.addPublisher(publisher);
        } catch (LibraryResourceAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(publisher,HttpStatus.CREATED);
    }
}
