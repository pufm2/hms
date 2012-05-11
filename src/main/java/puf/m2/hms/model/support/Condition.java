package puf.m2.hms.model.support;

public class Condition {
	private String prop, value;
	private Condition nextCondition;
	private BooleanOperator opearator;
	
	public Condition(String prop, String value) {
		this.prop = prop;
		this.value = value;
	}
	
	public Condition and(Condition condition) {
		opearator = BooleanOperator.and;
		nextCondition = condition;
		return condition;
	}
	
	public Condition or(Condition condition) {
		opearator = BooleanOperator.or;
		nextCondition = condition;
		return condition;

	}
	
	public String toString() {
		String str = prop + " = '" + value + "'";
		if (nextCondition != null) {
			str += " " + opearator.name() + " " + nextCondition; 
		}
		
		return str;
	}
	

}
