package entity.composite;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.CompositeException;

public class Composite implements Component {
	
	private static final Logger LOGGER = LogManager.getLogger(Composite.class.getName());
	private static final String ERR_ADD = "Error_added_component_is_null";
	private static final String ERR_REMOVE = "Error_invalid_deleted_component_index";
	private static final String ERR_GET_CHILD = "Error_invalid_component_index";
	
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

	private void validateIsNull(Object obj, String errorMesage) throws CompositeException {
		if (obj == null) {
			LOGGER.error(errorMesage);
			throw new CompositeException(errorMesage);
		}		
	}
	
	

}
