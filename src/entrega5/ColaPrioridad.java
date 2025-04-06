package entrega5;

import java.util.ArrayList;
import java.util.List;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {
	
    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
    	return new ColaPrioridad<>();
    }
    
    @Override
    public void add(PriorityElement<E, P> element) {
    	int i = 0;
    	
        for (PriorityElement<E, P> priorityElement : this.elements()) {
            if (element.priority().compareTo(priorityElement.priority()) < 0) {
                break;
            }
            i++;
        }
        this.elements().add(i, element);
    }
    
    public void add(E value, P priority) {
    	this.add(new PriorityElement<>(value, priority));
    }
    
    public List<E> valuesAsList() {
    	List<E> values = new ArrayList<>();
    	
        for (PriorityElement<E, P> element : this.elements()) {
            values.add(element.value());
        }
        return values;
    }
    
    public void decreasePriority(E value, P newPriority) {
    	PriorityElement<E, P> element = null;

        for (PriorityElement<E, P> priorityElement : this.elements()) {
            if (priorityElement.value().equals(value)) {
                element = priorityElement;
                break;
            }
        }

        if (element != null) {
            this.elements().remove(element);
            this.add(new PriorityElement<>(value, newPriority));
        } else {
            throw new IllegalArgumentException("El valor no existe en la cola.");
        }
    }
    
    public E removeValue() {
    	if (this.elements().isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return this.elements().remove(0).value();

    }
    
    public void addAllValues(List<E> values, P priority) {
    	for (E value : values) {
            this.add(value, priority);
        }
    }
}
