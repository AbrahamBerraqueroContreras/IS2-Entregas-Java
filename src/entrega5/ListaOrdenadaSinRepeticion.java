package entrega5;

import java.util.Comparator;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {
	
	// protected ArrayList<E> elements = new ArrayList<E>();
	// private Comparator<E> comparator;
	
    public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
    	super(comparator); // Accede al método constructor de ListaOrdenada<E> 
    }
    
    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
    	return new ListaOrdenadaSinRepeticion<E>(comparator);
    }
    
    @Override
    public void add(E e) {
    	if (!this.elements().contains(e)) { 
    		super.add(e);
    	}
    }
}
