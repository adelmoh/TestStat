
package be.mdcpartners.teststat.statistics;

public interface SomeResult extends Comparable<SomeResult>, Streamable<SomeResult> {
	Type getType();

	@Override
	SomeResult newEmpty();

	int count();

	SomeResult copy();
}
