package entrega5;

public class Cola<E> extends AgregadoLineal<E> {
	
	// private ArrayList<E> elements = new ArrayList<E>();
	
    public static <E> Cola<E> of() {
    	return new Cola<E>();
    }
    
    @Override
    public void add(E e) {
    	this.elements().add(e);
    }
}
