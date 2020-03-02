package entity.composite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.CompositeException;

public class Composite implements Component {
	
	private static final Logger LOGGER = LogManager.getLogger(Composite.class.getName());
	private static final String ERR_ADD = "Error_added_component_is_null";
	private static final String ERR_REMOVE = "Error_invalid_deleted_component_index";
	private static final String ERR_GET_CHILD = "Error_invalid_component_index";
	private static final String ERR_SORT = "Error_sort_parameters_is_null";
	
	private List<Component> componentList = new ArrayList<Component>();	
	private ComponentType type;

	public Composite(ComponentType type) {
		super();
		this.type = type;
	}
	
	public ComponentType getType() {
		return type;
	}

	public boolean add(Component component) throws CompositeException {
		
		validateIsNull(component, ERR_ADD);			
		return componentList.add(component);
	}

	public boolean remove(Object obj) throws CompositeException {
		
		validateIsNull(obj, ERR_REMOVE);
		return componentList.remove(obj);
	}

	public Component getChild(int index) throws CompositeException {
		if (index < 0 || index >= componentList.size()) {
		LOGGER.error(ERR_GET_CHILD);
		throw new CompositeException(ERR_GET_CHILD);
		
	}		
		return componentList.get(index);
	}
	
	public String getValue() {
		StringBuilder result = new StringBuilder();
		for (Component component : componentList) {
			result.append(component.getValue());
		}
		return result.toString();
	}	

	@Override
	public int getSize(){
		return componentList.size();
	}	

	@Override
	public void sort(ComponentType sortedComposite, Comparator<Component> comparator) throws CompositeException {
		validateIsNull(sortedComposite, ERR_SORT);
		validateIsNull(comparator, ERR_SORT);		
		if (this.type.equals(sortedComposite)) {
			
			for (int i = 0; i < componentList.size() - 1; i++) {
				if (componentList.get(i).getType().equals(ComponentType.SYMBOL)) {
					continue;
				}
				for (int j = 0; j < componentList.size() - 1 - i; j++) {
					if (componentList.get(j).getType().equals(ComponentType.SYMBOL)) {
						continue;
					}
					int k = j + 1;
					while (k < componentList.size() - 1 && componentList.get(k).getType().equals(ComponentType.SYMBOL)) {
						k++;
					}
					if (componentList.get(k).getType().equals(ComponentType.SYMBOL)) {
						continue;
					}					
					if (comparator.compare(componentList.get(j), componentList.get(k)) > 0) {
						Component temp = componentList.get(j);
						componentList.set(j, componentList.get(k));
						componentList.set(k, temp);
					}					
				}
			}
		}		
		
		for (int i = 0; i < componentList.size(); i++) {
			componentList.get(i).sort(sortedComposite, comparator);
		}
		
	}

	private void validateIsNull(Object obj, String errorMesage) throws CompositeException {
		if (obj == null) {
			LOGGER.error(errorMesage);
			throw new CompositeException(errorMesage);
		}		
	}
}
