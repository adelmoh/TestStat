package be.mdcpartners.teststat.statistics.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.mdcpartners.teststat.object.Trial;
import be.mdcpartners.teststat.statistics.InitException;
import be.mdcpartners.teststat.statistics.ResultContainer;
import be.mdcpartners.teststat.statistics.ResultTrials;
import be.mdcpartners.teststat.statistics.Statistics;
import be.mdcpartners.teststat.statistics.StatisticsResult;
import be.mdcpartners.teststat.statistics.Type;

public class PhaseStatistics implements Statistics {
	private static final char SUMMARY_CHAR = 'S';
	private static char[] PHASES = { '1', 'A', '2', 'B', '3', '4', '?', SUMMARY_CHAR };
	private static char[] PHASES_1234 = { '1', '2', '3', '4', '?', SUMMARY_CHAR };

	private boolean phase1234;

	@Override
	public PhaseStatistics newEmpty() {
		return new PhaseStatistics();
	}

	public static class Data {
		int trialsStarted;
		int sitesOpened;
		double enrollment;

		TreeMap<Integer, Integer> enrollmentHistogram;

		public double getEnrollment() {
			return enrollment;
		}

		public int getTrialsStarted() {
			return trialsStarted;
		}

		public int getSitesOpened() {
			return sitesOpened;
		}

		@Override
		public String toString() {
			return "T: " + trialsStarted + " S:" + sitesOpened + " E:" + enrollment + " min:" + getMinEnrollmentAtSite()
					+ " avg:" + getAvgEnrollmentAtSite() + " med:" + getMedEnrollmentAtSite() + " max:"
					+ getMaxEnrollmentAtSite();
		}

		public double getAvgEnrollmentAtSite() {
			if (enrollmentHistogram == null) {
				return 0;
			}
			int v = 0;
			int sum = 0;
			for (Map.Entry<Integer, Integer> e : enrollmentHistogram.entrySet()) {
				v += e.getKey() * e.getValue();
				sum += e.getValue();
			}
			return v / (double) sum;
		}

		public double getMaxEnrollmentAtSite() {
			if (enrollmentHistogram == null) {
				return 0;
			}
			return enrollmentHistogram.lastKey();
		}

		public double getMedEnrollmentAtSite() {
			if (enrollmentHistogram == null) {
				return 0;
			}
			int sum = 0;
			for (Map.Entry<Integer, Integer> e : enrollmentHistogram.entrySet()) {
				sum += e.getValue();
			}
			int half = sum /= 2;
			int cnt = 0;
			for (Map.Entry<Integer, Integer> e : enrollmentHistogram.entrySet()) {
				cnt += e.getValue();
				if (cnt >= half) {
					return e.getKey();
				}
			}
			throw new UnsupportedOperationException();
		}

		public double getMinEnrollmentAtSite() {
			if (enrollmentHistogram == null) {
				return 0;
			}
			return enrollmentHistogram.firstKey();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Data) {
				Data d = (Data) obj;
				return trialsStarted == d.trialsStarted && sitesOpened == d.sitesOpened
						&& Math.abs(enrollment - d.enrollment) <= 10e-10;
			}
			return false;
		}

		void inc(double v, int sites) {
			this.enrollment += v;
			this.sitesOpened += sites;
			if (sites >= 2) {
				if (enrollmentHistogram == null) {
					enrollmentHistogram = new TreeMap<>();
				}
				increment(enrollmentHistogram, (int) Math.round(v));
			}
		}

	}

	private static void increment(TreeMap<Integer, Integer> h, int k) {
		Integer w = h.get(k);
		h.put(k, w == null ? 1 : 1 + w);
	}

	@Override
	public StatisticsResult compute(ResultContainer resultContainer) {
		Map<Character, Data> resultMap = new TreeMap<>();
		initMap(resultMap);

		ResultTrials resultTrials = (ResultTrials) resultContainer.result(Type.TRIAL);

		for (Trial trial : resultTrials.trials()) {
			int cnt = trial.numSites();

			char phase = phase1234 ? Trial.toPhase1234(trial.getPhase()) : trial.getPhase();

			add(phase, resultMap, trial.getEnrollment(), cnt);
		}
		return new StatisticsResultContainer(resultMap);
	}

	private void initMap(Map<Character, Data> map) {
		for (char p : phase1234 ? PHASES_1234 : PHASES) {
			map.put(p, new Data());
		}
	}

	private static void add(char phase, Map<Character, Data> map, double enrollment, int sites) {
		for (char p : new char[] { phase, SUMMARY_CHAR }) {
			Data allPhase = map.get(p);
			if (allPhase == null) {
				throw new RuntimeException("Have all phase == null for " + phase + " with map " + map);
			}
			allPhase.inc(enrollment, sites);
			allPhase.trialsStarted++;
		}
	}

	@Override
	public List<?> defaultSorter(int dim, Object resultOfComputation) {
		List<String> res = new LinkedList<>();
		if (dim == 1) {
			for (char phase : phase1234 ? PHASES_1234 : PHASES) {
				if (SUMMARY_CHAR != phase) {
					res.add(Character.toString(phase));
				}
			}
		} else {
			throw new RuntimeException("dim " + dim);
		}
		return res;
	}

	@Override
	public String alias() {
		return "PhaseStats";
	}

	@Override
	public void init(String stringRepresentation) throws InitException {
		phase1234 = "Y".equals(stringRepresentation);
	}

	@Override
	public String stream() {
		return phase1234 ? "Y" : "N";
	}

	@Override
	public String nice() {
		return "phase statistics";
	}

}
