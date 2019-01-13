package com.matt.restfulwebservice.service;


import com.matt.restfulwebservice.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
*
* I create Post to send to web services
* */
@Service
public class PostFactory {

    public static final String TITLE = "my title";
    public static final String BODY = "my body";
    public static final int USER_ID = 3;
    private static final Logger LOG = LoggerFactory.getLogger(PostFactory.class);

    public Post createPost() {


        Post postRequest = new Post();
        postRequest.setTitle(TITLE);
        postRequest.setBody(BODY);
        postRequest.setUserId(USER_ID);

        return postRequest;


    }

}
