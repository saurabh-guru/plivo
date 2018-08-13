package com.plivo.assignment.response;

import com.plivo.assignment.models.UserMessageDetails;
import lombok.Data;

import java.util.List;

@Data
public class SearchMessageResponse {

    private long perPage;

    private long currentPage;

    private long total;

    private List<UserMessageDetails> userMessageDetailsList;

}
