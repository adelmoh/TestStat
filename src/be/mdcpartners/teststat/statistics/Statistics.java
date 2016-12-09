package be.mdcpartners.teststat.statistics;

import java.util.List;

public interface Statistics extends Streamable<Statistics> {
	int SUMMARY_YEAR = -1;
	int SUMMARY_MONTH = -2;
	int SUMMARY = -3;

	String nice();

	String ALL = "__ALL__";

	StatisticsResult compute(ResultContainer result);

	List<?> defaultSorter(int dim, Object resultOfComputation);
}
