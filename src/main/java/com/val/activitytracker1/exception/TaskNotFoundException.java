package com.val.activitytracker1.exception;

public class TaskNotFoundException extends RuntimeException{
    private static final long serialVersionUID =1L;
    public TaskNotFoundException(){
        super();
    }

    public TaskNotFoundException(String customMessage){
        super(customMessage);
    }
}