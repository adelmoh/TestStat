package be.mdcpartners.teststat.statistics;

public interface Streamable<T> {
	String alias();

	T newEmpty();

	/**
	 * @param stringRepresentation
	 * @throws InitException
	 */
	void init(String stringRepresentation) throws InitException;

	String stream();
}
