package com.amantya.jwt.publisher;

import com.amantya.jwt.Exception.LibraryResourceAlreadyExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private  PublisherRepository publisherRepository ;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher addPublisher(Publisher publisherToBeAdded)
            throws LibraryResourceAlreadyExistsException {

        PublisherEntity publisherEntity = new PublisherEntity(
                publisherToBeAdded.getName(),
                publisherToBeAdded.getEmail(),
                publisherToBeAdded.getPhoneNumber()
        );

        PublisherEntity addedPublisher = null ;

        try {
            addedPublisher = publisherRepository.save(publisherEntity);
        } catch ( DataIntegrityViolationException e) {
            throw new LibraryResourceAlreadyExistsException("Publisher already exist.") ;
        }

        publisherToBeAdded.setID(addedPublisher.getPublisherID());
        return publisherToBeAdded;
    }
}
