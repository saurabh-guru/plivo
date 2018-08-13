package com.plivo.assignment.models;

import com.plivo.assignment.request.UserRequest;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user_message_details")
public class UserMessageDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String message;

    public UserMessageDetails(UserRequest userRequest){
        this.message = userRequest.getMessage();
        this.name = userRequest.getName();
    }

    public UserMessageDetails(){

    }

    public void update(UserRequest request) {
        if(request.getName()!=null){
            this.name = request.getName();
        }
	if(request.getMessage()!=null){
	    this.message = request.getMessage();
	}
    }
}
