package com.sample.spring.jersey.swagger.beans;

import java.util.List;

public interface GenericResponse<T> {

    /**
     * Gets the value of the errors property.
     *
     */
    boolean hasErrors();

    /**
     * Sets the value of the errors property.
     *
     */
    void setErrors(boolean value);

    /**
     * Gets the value of the messageLists property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageLists property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageLists().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Message }
     *
     *
     */
    List<Message> getMessageList();

    T   getResult();
    void setResult(T t);

}
