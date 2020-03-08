package msc20.components.model;

import javax.persistence.Embeddable;

@Embeddable
public class Pair<T, R> {

	public Pair() {
	}

	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}

	private T first;
	private T second;
}
