package be.mdcpartners.teststat.statistics;

import java.util.Set;

public interface ResultContainer extends Streamable<ResultContainer> {
	SomeResult result(Type type);

	void addResult(SomeResult someResult);

	Set<Type> availableTypes();

	void removeResult(Type type);

	boolean haveResult(Type type);

	ResultContainer copy();
}
