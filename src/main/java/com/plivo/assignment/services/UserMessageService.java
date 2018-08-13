package com.plivo.assignment.services;

import com.plivo.assignment.models.UserMessageDetails;
import com.plivo.assignment.request.UserRequest;
import com.plivo.assignment.response.SearchMessageResponse;

public interface UserMessageService{

    long insertMessage(UserRequest request);

    void updateMessage(UserRequest request) throws Exception;

    SearchMessageResponse searchByName(String name, Integer page);
}