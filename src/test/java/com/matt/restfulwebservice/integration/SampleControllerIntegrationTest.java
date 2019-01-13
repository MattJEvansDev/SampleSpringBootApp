package com.matt.restfulwebservice.integration;

import com.matt.restfulwebservice.controller.SampleController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleControllerIntegrationTest {

    @Autowired
    private SampleController sampleController;


    @Test
    public void testAutowired() throws Exception {
        Assert.assertNotNull(sampleController);
        Assert.assertNotNull(ReflectionTestUtils.getField(sampleController, "restTemplate"));
        Assert.assertNotNull(ReflectionTestUtils.getField(sampleController, "postFactory"));
    }

    @Test
    public void testGetStatusCode() {
        HttpStatus status = sampleController.getStatusCode();
        Assert.assertTrue(status.is2xxSuccessful());
    }


    @Test
    public void testGetStatusCodeWithHeader() {
        HttpStatus status = sampleController.getStatusCodeForAllPostsWithHeaders();

        Assert.assertTrue(status.is2xxSuccessful());
    }

}
