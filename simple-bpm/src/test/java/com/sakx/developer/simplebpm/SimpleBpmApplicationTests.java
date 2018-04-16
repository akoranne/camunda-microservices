package com.sakx.developer.simplebpm;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SimpleBpmApplication.class }, properties = {
		"org.camunda.bpm.spring.boot.starter.example.simple.SimpleApplication.exitWhenFinished=false" })
public class SimpleBpmApplicationTests {

	@Rule
	public Timeout timeout = Timeout.seconds(5);

	@Autowired
	private SimpleBpmApplication application;

//	@Test
//	public void would_fail_if_process_not_completed_after_5_seconds() throws InterruptedException {
//		while (!application.processApplicationStopped && !application.isProcessInstanceFinished()) {
//			Thread.sleep(500L);
//		}
//	}
//
	@Test
	public void contextLoads() {
	}

}
