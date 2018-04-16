package com.sakx.developer.example.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sakx.developer.example.webapp.WebappExampleApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebappExampleApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIT {

  @Test
  public void startUpTest() {
    // context init test
  }

}
