package com.sakx.developer.simplebpm;

import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Getter @NoArgsConstructor
public class Showcase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private CamundaBpmProperties camundaBpmProperties;

	@Autowired
	private TaskService taskService;

	private String processInstanceId;
	
	
	public void run(Map<String, Object> flowArgs) throws Exception {
		logger.info(" ----------------------> in Showcase::run() ");

		flowArgs.put("info", "testing the a bpm flow service ");
		
		processInstanceId = runtimeService.startProcessInstanceByKey("Hello").getProcessInstanceId();
		logger.info("started instance: {}", processInstanceId);

		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		taskService.complete(task.getId());
		logger.info("completed task: {}", task);

		// now jobExecutor should execute the async job
	}

//	@EventListener
//	public void notify(final PostDeployEvent unused) {
//		processInstanceId = runtimeService.startProcessInstanceByKey("Hello").getProcessInstanceId();
//		logger.info("started instance: {}", processInstanceId);
//
//		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
//		taskService.complete(task.getId());
//		logger.info("completed task: {}", task);
//
//		// now jobExecutor should execute the async job
//	}

}