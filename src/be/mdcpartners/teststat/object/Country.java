package be.mdcpartners.teststat.object;

import be.mdcpartners.teststat.statistics.Type;

public class Country extends AbstractHasIdImpl {

	private String name;

	public Country(long id, String name) {
		setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Type getType() {
		return Type.COUNTRY;
	}
}
