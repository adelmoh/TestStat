package be.mdcpartners.teststat.statistics.impl;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import be.mdcpartners.teststat.object.PleaseImplementMeException;
import be.mdcpartners.teststat.statistics.InitException;
import be.mdcpartners.teststat.statistics.ResultContainer;
import be.mdcpartners.teststat.statistics.SomeResult;
import be.mdcpartners.teststat.statistics.Type;

public class ResultContainerImpl implements ResultContainer {
	Map<Type, SomeResult> results = new TreeMap<>();

	@Override
	public String alias() {
		return "RC";
	}

	@Override
	public ResultContainer newEmpty() {
		return new ResultContainerImpl();
	}

	@Override
	public void init(String stringRepresentation) throws InitException {
		// nothing for now
	}

	@Override
	public String stream() {
		return "~";
	}

	@Override
	public SomeResult result(Type type) {
		return results.get(type);
	}

	@Override
	public void addResult(SomeResult someResult) {
		results.put(someResult.getType(), someResult);
	}

	@Override
	public Set<Type> availableTypes() {
		return results.keySet();
	}

	@Override
	public void removeResult(Type type) {
		results.remove(type);
	}

	@Override
	public boolean haveResult(Type type) {
		return results.containsKey(type);
	}

	@Override
	public ResultContainer copy() {
		throw new PleaseImplementMeException();
	}
}
