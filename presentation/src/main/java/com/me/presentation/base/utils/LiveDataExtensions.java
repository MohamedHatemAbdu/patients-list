package com.me.presentation.base.utils;

import androidx.lifecycle.MutableLiveData;

import com.me.presentation.base.model.Resource;
import com.me.presentation.base.model.ResourceState;

public class LiveDataExtensions {

    public static <T> void setSuccess(
            MutableLiveData<Resource<T>> mutableLiveData, T data) {
        mutableLiveData.
                postValue(new Resource(ResourceState.SUCCESS, data));

    }

    public static <T> void setLoading(
            MutableLiveData<Resource<T>> mutableLiveData) {
        mutableLiveData.
                postValue(new Resource(ResourceState.LOADING));

    }

    public static <T> void setError(
            MutableLiveData<Resource<T>> mutableLiveData, String message) {
        mutableLiveData.
                postValue(new Resource(ResourceState.ERROR, message));

    }

}
