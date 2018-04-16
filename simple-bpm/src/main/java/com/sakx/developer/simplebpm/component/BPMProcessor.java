package com.sakx.developer.simplebpm.component;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.sakx.developer.simplebpm.Showcase;

@Controller
//public class BPMProcessor implements CommandLineRunner {
public class BPMProcessor {
	boolean processApplicationStopped;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobExecutor jobExecutor;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ConfigurableApplicationContext context;

	@Autowired
	private CamundaBpmProperties camundaBpmProperties;

	@Autowired
	private Showcase showcase;

	@Autowired
	private ProcessEngine processEngine;

	@Value("${org.camunda.bpm.spring.boot.starter.example.simple.SimpleApplication.exitWhenFinished:true}")
	private boolean exitWhenFinished  = false;

	@EventListener
	public void onPostDeploy(PostDeployEvent event) {
		logger.info("postDeploy: {}", event);
	}

	@EventListener
	public void onPreUndeploy(PreUndeployEvent event) {
		logger.info("preUndeploy: {}", event);
		processApplicationStopped = true;
	}

/*
 * Use this when doing a one time batch logic
 * Remove for an active service
 * 	
	@SuppressWarnings("deprecation")
	@Scheduled(fixedDelay = 1500L)
	public void exitApplicationWhenProcessIsFinished() {
		Assert.isTrue(!((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration())
				.isDbMetricsReporterActivate());

		String processInstanceId = showcase.getProcessInstanceId();

		if (processInstanceId == null) {
			logger.info("processInstance not yet started!");
			return;
		}

		if (isProcessInstanceFinished()) {
			logger.info("processinstance ended!");

//			if (exitWhenFinished) {
//				jobExecutor.shutdown();
//				SpringApplication.exit(context, () -> 0);
//			}
			return;
		}
		logger.info("processInstance not yet ended!");
	}
 */

	public boolean isProcessInstanceFinished() {
		final HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(showcase.getProcessInstanceId()).singleResult();

		return historicProcessInstance != null && historicProcessInstance.getEndTime() != null;
	}

	// @Override
	//public void run(String... strings) throws Exception {
	public void run(Map<String, Object> flowArgs) throws Exception {
		logger.info(" ----------------------> in BPMProcessor::run() ");
		logger.info("CommandLineRunner#run() - {}", ToStringBuilder
				.reflectionToString(camundaBpmProperties.getApplication(), ToStringStyle.SHORT_PREFIX_STYLE));
		
		flowArgs.put("info", "testing the a bpm flow service ");
		ProcessInstance instance =  processEngine.getRuntimeService().startProcessInstanceByKey("hello", flowArgs);
	
		logger.info("kicked off " + instance.getBusinessKey() 
			+ ", id - " + instance.getId() 
			+ ", instance - " + instance.getProcessInstanceId() );

	}


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

	public static class SysoutDelegate implements JavaDelegate {
		public void execute(DelegateExecution execution) throws Exception {
			System.out.println("Hello from activity " + execution.getCurrentActivityId());
		}
	}

}
