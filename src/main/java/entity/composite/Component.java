package entity.composite;

public interface Component {
	
	boolean add(Component component);
	boolean remove(int index);
	Component getChild(int index);

}
