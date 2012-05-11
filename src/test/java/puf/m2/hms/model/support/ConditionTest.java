package puf.m2.hms.model.support;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConditionTest {

	@Test
	public void testAnd() {
		new Condition("p1", "v1").and(new Condition("p2", "v2")).and(new Condition("p3", "v3"));
	}

	@Test
	public void testOr() {
		new Condition("p1", "v1").or(new Condition("p2", "v2")).or(new Condition("p3", "v3"));
	}

	@Test
	public void testToString() {
		Condition c = new Condition("p1", "v1");
		c.and(new Condition("p2", "v2")).or(new Condition("p3", "v3"));
		assertEquals("p1 = 'v1' and p2 = 'v2' or p3 = 'v3'", c.toString());
	}

}
