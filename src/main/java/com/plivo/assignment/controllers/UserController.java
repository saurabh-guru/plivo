package com.plivo.assignment.controllers;

import com.plivo.assignment.enums.ErrorCode;
import com.plivo.assignment.models.UserMessageDetails;
import com.plivo.assignment.request.UserRequest;
import com.plivo.assignment.response.SearchMessageResponse;
import com.plivo.assignment.response.UserEntityResponse;
import com.plivo.assignment.services.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "plivo/v1")
public class UserController {

    @Autowired
    UserMessageService userMessageService;

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public UserEntityResponse updateMessage(RequestEntity<UserRequest> requestEntity){
        try{
            userMessageService.updateMessage(requestEntity.getBody());
            return UserEntityResponse.buildSuccessResponse("Successfully Updated");
        }catch(Exception e){
            return UserEntityResponse.buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @RequestMapping(value="/searchByName",method = RequestMethod.GET)
    public UserEntityResponse searchMessage(String name,Integer page){
        try{
            SearchMessageResponse searchMessageResponse = userMessageService.searchByName(name,page);
            return UserEntityResponse.buildSuccessResponse(searchMessageResponse);
        }catch(IllegalArgumentException e){
            return UserEntityResponse.buildNotFoundResponse(ErrorCode.DATA_NOT_FOUND,e.getMessage());
        }
	catch(Exception e){
            return UserEntityResponse.buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @RequestMapping(value="/getMessages",method = RequestMethod.GET)
    public UserEntityResponse getAllMessages(Integer page){
        try{
            SearchMessageResponse searchMessageResponse = userMessageService.getAll(page);
            return UserEntityResponse.buildSuccessResponse(searchMessageResponse);
        }catch(Exception e){
            return UserEntityResponse.buildErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

}
