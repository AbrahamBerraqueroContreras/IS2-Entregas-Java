package entrega5;

import java.util.Comparator;

public class ListaOrdenada<E> extends AgregadoLineal<E> {

	// private ArrayList<E> elements = new ArrayList<E>();
	private Comparator<E> comparator;

	public ListaOrdenada(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
		return new ListaOrdenada<E>(comparator);
	}

	private int indexOrder(E e) {
		for (int i = 0; i < this.elements().size(); i++) {
			if (this.comparator.compare(e, this.elements().get(i)) < 0) {
				return i;
			}
		}
		return this.elements().size();
	}

	@Override
	public void add(E e) {
		this.elements().add(this.indexOrder(e), e);
	}
}
