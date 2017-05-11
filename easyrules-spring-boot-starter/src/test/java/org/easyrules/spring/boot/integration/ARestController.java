package org.easyrules.spring.boot.integration;

import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ARestController {

	@Autowired
	private DummyRule dummyRule;

	@Autowired
	private RulesEngine rulesEngine;

	@RequestMapping ("/greeting")
	public String greeting(@RequestParam (value = "name", defaultValue = "World") String name) {
		rulesEngine.registerRule(dummyRule);
		rulesEngine.fireRules();
		return "Hello classes behind proxies and easy rules";
	}
}
