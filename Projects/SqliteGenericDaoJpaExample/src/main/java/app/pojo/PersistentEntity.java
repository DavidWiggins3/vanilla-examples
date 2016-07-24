package app.pojo;

public abstract class PersistentEntity<T> {

	public abstract T getId();

	public abstract void setId(T id);
	
}
