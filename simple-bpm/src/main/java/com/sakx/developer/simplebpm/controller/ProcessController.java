package com.sakx.developer.simplebpm.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakx.developer.simplebpm.Showcase;

@RefreshScope
@RestController
public class ProcessController {
	private static final Logger log = LoggerFactory.getLogger(ProcessController.class);

	private String bpmName = "bpmn/hello.bpmn";
	
	
	@Autowired
	Showcase showcase;
	
	@Value("${config.name:unknown}")
	private String flowName;

	@RequestMapping("/")
	public Map<String, String> whoAmI() {
		// String resolvedName =  cn.orElse(name);
		return Collections.singletonMap(flowName, "Flow -" + bpmName + "!");
	}


	@RequestMapping("/hello-flow")
	public Map<String, String> execFlow() {
		log.info(" in execFlow() " );
		
		// execute the flow
		Map<String, Object> flowArgs = new HashMap<String, Object>();
		try {
			showcase.run(flowArgs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> amap = new HashMap<String, String>();
		amap.put("status", "SUCCESS");
		amap.put("message", "flow kicked off");		
		return amap;
	}


}
