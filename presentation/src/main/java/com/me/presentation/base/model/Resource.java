package com.me.presentation.base.model;

public class Resource <T> {
    private  ResourceState state;
    private T data;
    private String message;

    public Resource(ResourceState state) {
        this.state = state;
        this.data = null;
        this.message = null;
    }

    public Resource(ResourceState state, String message) {
        this.state = state;
        this.data = null;
        this.message = message;
    }

    public Resource(ResourceState state, T data) {
        this.state = state;
        this.data = data;
        this.message = null;
    }

    public ResourceState getState() {
        return state;
    }

    public void setState(ResourceState state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
