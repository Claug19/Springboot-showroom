package com.showroomSoap.soap.user;

import com.showroom.springboot.model.User;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.com.innova.models.soap.emp package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    //  Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
    //  com.com.part2.models.soap.user
    public ObjectFactory() {
    }

    //  Create an instance of {@link GetUserRequest }
    public GetUserRequest createGetUserRequest() {
        return new GetUserRequest();
    }

    //  Create an instance of {@link GetUserResponse }
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    //  Create an instance of {@link User }
    public User createUser() {
        return new User();
    }

}