package be.mdcpartners.teststat.statistics.impl;

import be.mdcpartners.teststat.object.PleaseImplementMeException;
import be.mdcpartners.teststat.statistics.StatisticsResult;

public class StatisticsResultContainer implements StatisticsResult {
	Object result;

	@Override
	public String alias() {
		return "STATSRESULT";
	}

	@Override
	public Object getResult() {
		return result;
	}

	public StatisticsResultContainer() {
	}

	public StatisticsResultContainer(Object t) {
		result = t;
	}

	@Override
	public void init(String stringRepresentation) {
		throw new PleaseImplementMeException();
	}

	@Override
	public StatisticsResult newEmpty() {
		return new StatisticsResultContainer();
	}

	@Override
	public String stream() {
		throw new PleaseImplementMeException();
	}
}
