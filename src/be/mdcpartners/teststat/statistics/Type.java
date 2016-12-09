package be.mdcpartners.teststat.statistics;

import java.util.EnumSet;

public enum Type {

	TRIAL, SITE, COUNTRY, PROVINCE, LOCATION,;

	public static EnumSet<Type> TRIAL_ONLY = EnumSet.of(TRIAL);
	public static EnumSet<Type> NONE = EnumSet.noneOf(Type.class);
}
