package com.matt.restfulwebservice.controller;

import com.matt.restfulwebservice.controller.SampleController;
import com.matt.restfulwebservice.model.Post;
import com.matt.restfulwebservice.service.PostFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleControllerTest {

    @Mock
    private PostFactory postFactory;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity<Post> mockedResponseEntity;


    @InjectMocks
    private SampleController sampleControllerWithMockedServices;



    @Test
    public void testPostToService() {

        Post post = new Post();
        post.setId(0);
        Mockito.when(postFactory.createPost()).thenReturn(post);


        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(HttpEntity.class), Mockito.eq(Post.class))).thenReturn(mockedResponseEntity);

        sampleControllerWithMockedServices.makePostRequest();

        Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(HttpEntity.class), Mockito.eq(Post.class));

        Mockito.verify(postFactory, Mockito.times(1)).createPost();


    }

}