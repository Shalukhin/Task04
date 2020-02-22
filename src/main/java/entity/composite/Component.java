package entity.composite;

import exception.CompositeException;

public interface Component {
	
	boolean add(Component component) throws CompositeException;
	boolean remove(Object obj) throws CompositeException;
	Component getChild(int index) throws CompositeException;
	String getValue();
	int getSize();
}
