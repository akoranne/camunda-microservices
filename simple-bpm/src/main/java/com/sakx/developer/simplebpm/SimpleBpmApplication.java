package com.sakx.developer.simplebpm;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableProcessApplication("SimpleBpmApplication")
public class SimpleBpmApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimpleBpmApplication.class, args);
		
	//// initialize the process engine.
		//StandaloneInMemProcessEngineConfiguration conf = new StandaloneInMemProcessEngineConfiguration();
		//ProcessEngine engine = conf.buildProcessEngine();
		//
		//engine.getRepositoryService().createDeployment() //
//				.addModelInstance("flow.bpmn", Bpmn.createExecutableProcess("flow") //
//						.startEvent().serviceTask().name("Step1").camundaClass(SysoutDelegate.class).serviceTask()
//						.name("Step2").camundaClass(SysoutDelegate.class).endEvent().done())
//				.deploy();
		//
		//engine.getRuntimeService().startProcessInstanceByKey("flow", Variables.putValue("someData", "someValue"));
		
	}
}





