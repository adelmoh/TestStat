package be.mdcpartners.teststat.statistics.impl;

import java.util.HashSet;
import java.util.Set;

import be.mdcpartners.teststat.object.Trial;
import be.mdcpartners.teststat.statistics.InitException;
import be.mdcpartners.teststat.statistics.ResultTrials;
import be.mdcpartners.teststat.statistics.SomeResult;
import be.mdcpartners.teststat.statistics.Type;

public class ResultTrialsImpl implements ResultTrials {

	private final Set<Trial> trials = new HashSet<>();

	@Override
	public Type getType() {
		return Type.TRIAL;
	}

	@Override
	public SomeResult newEmpty() {
		return new ResultTrialsImpl();
	}

	@Override
	public int count() {
		return trials.size();
	}

	@Override
	public SomeResult copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(SomeResult o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String alias() {
		return "trials rc";
	}

	@Override
	public void init(String stringRepresentation) throws InitException {
		//
	}

	@Override
	public String stream() {
		return "~";
	}

	@Override
	public Set<Trial> trials() {
		return trials;
	}

	@Override
	public void updateTrials(Set<Trial> trials) {
		this.trials.clear();
		this.trials.addAll(trials);
	}

	@Override
	public void retainTrials(Set<Trial> trials) {
		// TODO Auto-generated method stub

	}

}
