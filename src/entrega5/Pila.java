package entrega5;

public class Pila<E> extends AgregadoLineal<E> {
	
    @Override
    public void add(E e) {
    	this.elements().add(0,e);
    }
    
    public E top() {
    	return this.elements().getFirst();
    }
}
