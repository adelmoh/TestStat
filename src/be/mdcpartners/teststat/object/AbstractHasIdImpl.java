package be.mdcpartners.teststat.object;

public abstract class AbstractHasIdImpl implements HasId {

	protected long id;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
}
