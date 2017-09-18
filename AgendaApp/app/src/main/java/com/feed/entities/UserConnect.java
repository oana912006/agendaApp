package com.feed.entities;

import java.io.Serializable;

/**
 * Entity from list of all users
 *
 * @author oana
 */
public class UserConnect implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String value;

    public UserConnect(String type, String value){
        setType(type);
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

