package com.plivo.assignment.services.impl;

import com.plivo.assignment.models.UserMessageDetails;
import com.plivo.assignment.repositories.UserMessageDetailsRepository;
import com.plivo.assignment.request.UserRequest;
import com.plivo.assignment.response.SearchMessageResponse;
import com.plivo.assignment.services.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageDetailsRepository userMessageDetailsRepository;

    @Override
    public long updateMessage(UserRequest userRequest) {
        UserMessageDetails userMessageDetails = new UserMessageDetails(userRequest);
        userMessageDetailsRepository.save(userMessageDetails);
        return userMessageDetails.getId();
    }

    /*@Override
    public void updateMessage(UserRequest request) throws Exception {
       UserMessageDetails userMessageDetails = userMessageDetailsRepository.searchByName(request.getName());
       if(userMessageDetails!=null){
            userMessageDetails.update(request);
            userMessageDetailsRepository.save(userMessageDetails);
            return;
        //}
        //throw new IllegalArgumentException("Message "+request.getMessage()+" is not found!");
    }*/

    @Override
    public SearchMessageResponse searchByName(String name, Integer page) {
        SearchMessageResponse searchMessageResponse = new SearchMessageResponse();
        Pageable pageable = createPageRequest(0,10);
        if(page!=null){

            pageable = createPageRequest(page-1,10);
        }
        Page<UserMessageDetails> userMessageDetailsList = userMessageDetailsRepository.findByName(name,pageable);
        searchMessageResponse.setUserMessageDetailsList(userMessageDetailsList.getContent());
        searchMessageResponse.setCurrentPage(userMessageDetailsList.getPageable().getPageNumber()+1);
        searchMessageResponse.setTotal(userMessageDetailsList.getTotalElements());
        searchMessageResponse.setPerPage(10);

        return searchMessageResponse;
    }

    private Pageable createPageRequest(int start,int end) {
        return PageRequest.of(start,end);
    }
}
