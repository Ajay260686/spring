package com.sample.spring.jersey.swagger.beans;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "GenericResponse")
public abstract class AbstractGenericResponse<T> implements  GenericResponse<T>{

    protected  boolean errors;
    protected List<Message> messageLists;

    public AbstractGenericResponse(){

    }

    @Override
    public boolean hasErrors() {
        return errors;
    }

    @Override
    public List<Message> getMessageList() {
        if (messageLists == null) {
            messageLists = new ArrayList<Message>();
        }
        return this.messageLists;
    }

    @Override
    public void setErrors(boolean errors) {
        this.errors = errors;
    }
}
