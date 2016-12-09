package be.mdcpartners.teststat.statistics.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.mdcpartners.teststat.object.PleaseImplementMeException;
import be.mdcpartners.teststat.object.Trial;
import be.mdcpartners.teststat.statistics.InitException;
import be.mdcpartners.teststat.statistics.ResultContainer;
import be.mdcpartners.teststat.statistics.ResultTrials;
import be.mdcpartners.teststat.statistics.Statistics;
import be.mdcpartners.teststat.statistics.StatisticsResult;
import be.mdcpartners.teststat.statistics.Type;
import be.mdcpartners.teststat.util.Util;

public class EnrollmentStatistics implements Statistics {

	private int minPeriodDef;
	private int maxPeriodDef;
	private TimeInterval timeInterval;

	public EnrollmentStatistics(TimeInterval timeInterval, int minPeriodDef, int maxPeriodDef) {
	}

	@Override
	public StatisticsResult compute(ResultContainer resultContainer) {
		int minPeriod = calcPeriod(minPeriodDef);
		int maxPeriod = calcPeriod(maxPeriodDef);
		Map<Integer, Data> resultMap = new TreeMap<>();
		ResultTrials resultTrials = (ResultTrials) resultContainer.result(Type.TRIAL);

		for (Trial trial : resultTrials.trials()) {
			if (trial.getStudyStart() == null) {
				continue;
			}
			double enrollment = trial.getEnrollment();
			int period = timeInterval != TimeInterval.ONLY_SUMMARY ? timeInterval == TimeInterval.YEARLY
					? Util.year(trial.getStudyStart()) : Util.yearMonth(trial.getStudyStart()) : 0;

			addWithSummary(resultMap, enrollment, period, minPeriod, maxPeriod);
		}
		return new StatisticsResultContainer(resultMap);
	}

	private void addWithSummary(Map<Integer, Data> resultMap, double enrollment, int period, int minPeriod,
			int maxPeriod) {
		if (timeInterval != TimeInterval.ONLY_SUMMARY && period >= minPeriod && period <= maxPeriod) {
			add(resultMap, enrollment, period);
		}
		add(resultMap, enrollment, timeInterval.summary);
	}

	private static void add(Map<Integer, Data> resultOneTransformed, double enrollment, int period) {
		Data d = resultOneTransformed.get(period);
		if (d == null) {
			d = new Data();
			resultOneTransformed.put(period, d);
		}
		d.enrollment += enrollment;
		d.trialsStarted++;
	}

	public static class Data {
		int trialsStarted;
		double enrollment;

		public double getEnrollment() {
			return enrollment;
		}

		public int getTrialsStarted() {
			return trialsStarted;
		}

		@Override
		public String toString() {
			return "T: " + trialsStarted + " E:" + enrollment;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Data) {
				Data d = (Data) obj;
				return trialsStarted == d.trialsStarted && Math.abs(enrollment - d.enrollment) <= 10e-10;
			}
			return false;
		}
	}

	public int calcPeriod(int periodDef) {
		if (timeInterval == TimeInterval.YEARLY) {
			return Util.caclPeriodYear(periodDef);
		}
		return Util.caclPeriodYearMonth(periodDef);
	}

	@Override
	public List<?> defaultSorter(int dim, Object resultOfComputation) {
		int minPeriod = calcPeriod(minPeriodDef);
		int maxPeriod = calcPeriod(maxPeriodDef);
		List<Object> res = new LinkedList<>();
		if (dim == 1) {
			for (int i = minPeriod; i <= maxPeriod; i++) {
				res.add(i);
			}
		} else {
			throw new RuntimeException("dim " + dim);
		}
		return res;
	}

	public void setMinYear(int minYear) {
		this.minPeriodDef = minYear;
		timeInterval = TimeInterval.YEARLY;
	}

	public void setMaxYear(int maxYear) {
		this.minPeriodDef = maxYear;
		timeInterval = TimeInterval.YEARLY;
	}

	public void setMinMonths(int minMonths) {
		this.minPeriodDef = minMonths;
		timeInterval = TimeInterval.MONTHLY;
	}

	public void setMaxMonths(int maxMonths) {
		this.maxPeriodDef = maxMonths;
		timeInterval = TimeInterval.MONTHLY;
	}

	@Override
	public String alias() {
		return "enrollmentStatistics";
	}

	@Override
	public Statistics newEmpty() {
		return new EnrollmentStatistics(timeInterval, minPeriodDef, maxPeriodDef);
	}

	@Override
	public void init(String stringRepresentation) throws InitException {
		throw new PleaseImplementMeException();
	}

	@Override
	public String stream() {
		StringBuilder sb = new StringBuilder();
		sb.append(minPeriodDef);
		sb.append(':');
		sb.append(maxPeriodDef);
		sb.append(':');
		sb.append(timeInterval);
		return sb.toString();
	}

	@Override
	public String nice() {
		return "enrollment statistics";
	}
}
