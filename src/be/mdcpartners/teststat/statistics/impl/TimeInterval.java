package be.mdcpartners.teststat.statistics.impl;

import static be.mdcpartners.teststat.statistics.Statistics.SUMMARY;
import static be.mdcpartners.teststat.statistics.Statistics.SUMMARY_MONTH;
import static be.mdcpartners.teststat.statistics.Statistics.SUMMARY_YEAR;

public enum TimeInterval {

	YEARLY('Y', SUMMARY_YEAR, "year"), MONTHLY('M', SUMMARY_MONTH, "month"), ONLY_SUMMARY('N', SUMMARY, "no interval");
	final public char code;
	final public int summary;
	final String nice;

	public String nice() {
		return nice;
	}

	private TimeInterval(char code, int summary, String nice) {
		this.code = code;
		this.summary = summary;
		this.nice = nice;
	}

	public static TimeInterval fromChar(char code) {
		for (TimeInterval ti : values()) {
			if (ti.code == code) {
				return ti;
			}
		}
		throw new RuntimeException("Unknown code '" + code + "' for timeInterval");
	}

}
