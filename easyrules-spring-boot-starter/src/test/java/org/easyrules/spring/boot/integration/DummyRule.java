package org.easyrules.spring.boot.integration;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.spring.SpringRule;

@SpringRule
public class DummyRule {
	@Condition
	public boolean when() {
		return true;
	}

	@Action (order = 1)
	public void then() throws Exception {
		System.out.println("Hey, I'm managed by Spring 1");
	}

	@Action(order = 2)
	public void andThen() {
		System.out.println("Hey, I'm managed by Spring 2");
	}
}
