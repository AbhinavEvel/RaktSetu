package com.rakthsetu.bloodbank.exception;

//Custom exception — jab koi record (by ID) database me nahi milta
public class ResourceNotFoundException extends RuntimeException {

 public ResourceNotFoundException(String message) {
     super(message);
 }
}
