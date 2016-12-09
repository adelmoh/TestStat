package be.mdcpartners.teststat.object;

import be.mdcpartners.teststat.statistics.Type;

public class Province extends AbstractHasIdImpl {

	private final Country country;
	private final String name;

	public Province(long id, String name, Country country) {
		setId(id);
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	@Override
	public Type getType() {
		return Type.PROVINCE;
	}
}
