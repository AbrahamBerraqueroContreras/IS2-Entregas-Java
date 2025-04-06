package entrega5;

import java.util.List;
import java.util.ArrayList;

public abstract class AgregadoLineal<E> {

	private ArrayList<E> elements = new ArrayList<>();

	public int size() {
		return this.elements.size();
	}

	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	public List<E> elements() {
		return (List<E>) this.elements;
	}

	public abstract void add(E e);

	public void addAll(List<E> list) {
		for (E i : list) {
			this.elements.add(i);
		}
	}

	public E remove() {
		if (this.elements.isEmpty()) {
	        throw new IllegalStateException("La lista está vacía.");
	    }
		return this.elements.remove(0);
	}

	public List<E> removeAll() {
		ArrayList<E> elements_clone = (ArrayList<E>) this.elements.clone();
		this.elements.clear();
		return elements_clone;
	}
}
