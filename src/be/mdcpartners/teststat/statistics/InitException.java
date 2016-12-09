package be.mdcpartners.teststat.statistics;

public class InitException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * to be used init() method of streamable object. Marks that there where
	 * problems during destream but no real crash. (in composit destreams /
	 * tile, query there can be a crash on lower level)
	 **/

	public InitException() {
	}

	public InitException(String msg) {
		super(msg);
	}
}
