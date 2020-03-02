package sort.comparator;

import java.util.Comparator;

import entity.composite.Component;

public class ByChildSizeComparator implements Comparator<Component> {

	@Override
	public int compare(Component o1, Component o2) {
		
		if (o1.getSize() > o2.getSize()) {

			return 1;
		} else if (o1.getSize() < o2.getSize()) {
			return -1;
		} else {
			return 0;
		}
	}	
}
