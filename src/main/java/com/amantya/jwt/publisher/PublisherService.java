package com.amantya.jwt.publisher;

import com.amantya.jwt.Exception.LibraryResourceAlreadyExistsException;
import com.amantya.jwt.Exception.LibraryResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService {

    private  PublisherRepository publisherRepository ;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(Publisher publisherToBeAdded)
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

    }

    public Publisher getPublisher(Integer publisherID) throws LibraryResourceNotFoundException {
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherID) ;
        Publisher publisher = null ;
        if(publisherEntity.isPresent()) {
            publisher = createPublisherFromPublisherEntity(publisherEntity.get());
        }else {
            throw new LibraryResourceNotFoundException("Publisher with id " + publisherID + " not found.") ;
        }
        return publisher ;
    }

    private Publisher createPublisherFromPublisherEntity(PublisherEntity pe) {
        return new Publisher(pe.getPublisherID(),pe.getName(),pe.getEmail(),pe.getPhoneNumber());
    }
}
