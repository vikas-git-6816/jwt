package com.amantya.jwt.publisher;

import com.amantya.jwt.Exception.LibraryResourceAlreadyExistsException;
import com.amantya.jwt.Exception.LibraryResourceNotFoundException;
import com.amantya.jwt.publisher.Publisher;
import com.amantya.jwt.utils.LibraryApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    private PublisherService publisherService ;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/{publisherID}")
    public ResponseEntity<?> getPublisher(@PathVariable Integer publisherID,
                                          @RequestHeader(value = "Trace-ID", defaultValue = "") String traceID)
    {
        if(LibraryApiUtils.isEmptyString(traceID)){
            traceID = UUID.randomUUID().toString();
        }
        Publisher publisher = null ;
        try {
           publisher = publisherService.getPublisher(publisherID,traceID);
        }catch (LibraryResourceNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
        }
        return  new ResponseEntity<>(publisher, HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher,
                                          @RequestHeader(value = "Trace-ID", defaultValue = "") String traceID)
    {
        if(LibraryApiUtils.isEmptyString(traceID)){
            traceID = UUID.randomUUID().toString();
        }

        try {
             publisherService.addPublisher(publisher,traceID);
        } catch (LibraryResourceAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(publisher,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{publisherID}")
    public ResponseEntity<?> deletePublisher(@PathVariable Integer publisherID,
                                             @RequestHeader(value = "Trace-ID", defaultValue = "") String traceID)
    {
        if(LibraryApiUtils.isEmptyString(traceID)){
            traceID = UUID.randomUUID().toString();
        }

        try {
            publisherService.deletePublisher(publisherID,traceID);
        }catch (LibraryResourceNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
        }
        return  new ResponseEntity<>(HttpStatus.ACCEPTED) ;
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchPublisher(@RequestParam String name,
                                             @RequestHeader(value = "Trace-ID", defaultValue = "") String traceID)
    {
        if(LibraryApiUtils.isEmptyString(traceID)){
            traceID = UUID.randomUUID().toString();
        }

        if(LibraryApiUtils.isEmptyString(name)) {
            return new ResponseEntity<>("Please enter a name to search publishers.", HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(publisherService.searchPublisher(name,traceID), HttpStatus.OK) ;
    }
}
