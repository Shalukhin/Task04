package entity.composite;

import java.util.Comparator;

import exception.CompositeException;

public interface Component {
	
	boolean add(Component component) throws CompositeException;
	boolean remove(Object obj) throws CompositeException;
	Component getChild(int index) throws CompositeException;
	String getValue();
	int getSize();
	ComponentType getType();
	void sort(ComponentType sortedComposite, Comparator<Component> comparator) throws CompositeException;
}
