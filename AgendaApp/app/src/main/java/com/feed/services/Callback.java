package com.feed.services;

/**
 * Callback that provides methods for success or error case of requests that were made
 */
public interface Callback {

    /**
     * Holds actions to be performed when request successfully finished
     *
     * @param data - data that is returned
     * @param <T>  - generic type of data
     */
    <T> void finished(T data);

    /**
     * Holds action to be performed when request is finished with error
     *
     * @param e - exception that occurs
     */
    void onError(Exception e);
}
