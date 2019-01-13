package com.matt.restfulwebservice.controller;


import com.matt.restfulwebservice.service.PostFactory;
import com.matt.restfulwebservice.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/*
* I contain examples on how to call restTemplate.
*
* */
@RestController
public class SampleController {

    private static final Logger LOG = LoggerFactory.getLogger(SampleController.class);
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private PostFactory postFactory;

    @Autowired
    private RestTemplate restTemplate;


    //  Get individual resource with no headers
    @RequestMapping("/foo-statuscode")
    public HttpStatus getStatusCode() {

        ResponseEntity<String> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", String.class);

        return response.getStatusCode();

    }

    // Get list of resources with headers
    @RequestMapping("/foo-headers")
    public HttpStatus getStatusCodeForAllPostsWithHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-name", "x-value");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Post>> response = restTemplate.exchange(
                POST_URL,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Post>>() {
                });

        List<Post> posts = response.getBody();
        posts.forEach(p -> LOG.trace("post is " + p.getTitle()));

        return response.getStatusCode();
    }

    // Post individual resource with headers
    @RequestMapping("/foo-body")
    public Post makePostRequest() {


        Post postRequest = postFactory.createPost();

        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        requestHeaders.add("X-name", "x-value");
        HttpEntity<Post> requestEntity = new HttpEntity<>(postRequest, requestHeaders);

        ResponseEntity<Post> responseEntity = restTemplate.exchange(POST_URL, HttpMethod.POST, requestEntity, Post.class);

        return responseEntity.getBody();

    }


}
