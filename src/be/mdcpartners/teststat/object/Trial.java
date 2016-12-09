package be.mdcpartners.teststat.object;

import java.util.Date;
import java.util.List;

import be.mdcpartners.teststat.statistics.Type;

public class Trial extends AbstractHasIdImpl {
	private final String idstring;
	private List<Site> sites;

	public static char[] PHASES = { '1', 'A', '2', 'B', '3', '4', '?', };
	public static char[] PHASES_1234 = { '1', '2', '3', '4', '?', };

	@Override
	public Type getType() {
		return Type.TRIAL;
	}

	private int enrollment;
	private char phase;

	public Trial(String idstring) {
		this.idstring = idstring;
	}

	public List<Site> getSites() {
		return sites;
	}

	public int numSites() {
		return sites.size();
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public String getIdstring() {
		return idstring;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	private Date studyStart;

	public Date getStudyStart() {
		return studyStart;
	}

	public void setStudyStart(Date studyStart) {
		this.studyStart = studyStart;
	}

	private Date studyEnd;

	public Date getStudyEnd() {
		return studyEnd;
	}

	public void setStudyEnd(Date studyEnd) {
		this.studyEnd = studyEnd;
	}

	public char getPhase() {
		return phase;
	}

	public void setPhase(char phase) {
		this.phase = phase;
	}

	public static char toPhase1234(char phase) {
		switch (phase) {
		case '1':
		case 'A':
			return '1';
		case '2':
		case 'B':
			return '2';
		case '3':
			return '3';
		case '4':
			return '4';
		default:
			return '?';
		}
	}
}
