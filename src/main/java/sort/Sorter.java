package sort;

import java.util.Comparator;

import entity.composite.Component;
import entity.composite.ComponentType;
import exception.CompositeException;
import sort.comparator.ByChildSizeComparator;

public class Sorter {

	public static void sortTextByParagraphsIncreaseCountSentence(Component component) throws CompositeException {
		Comparator<Component> comparator = new ByChildSizeComparator();
		component.sort(ComponentType.TEXT, comparator);
	}
}
