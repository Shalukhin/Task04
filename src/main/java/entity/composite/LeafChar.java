package entity.composite;

import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.CompositeException;

public class LeafChar implements Component {

	private static final Logger LOGGER = LogManager.getLogger(LeafChar.class.getName());
	private static final String ERR_GET_CHILD = "Error_Unsupport_Operation";
	
	private ComponentType type = ComponentType.SYMBOL;
	private char symbol;	

	public LeafChar(char symbol) {
		super();
		this.symbol = symbol;
	}	

	public ComponentType getType() {
		return type;
	}

	@Override
	public boolean add(Component component) throws CompositeException {
		return false;
	}

	@Override
	public boolean remove(Object obj) throws CompositeException {
		return false;
	}

	@Override
	public Component getChild(int index) throws CompositeException {
		LOGGER.error(ERR_GET_CHILD);
		throw new CompositeException(ERR_GET_CHILD);
	}

	@Override
	public String getValue() {		
		return String.valueOf(symbol);
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public void sort(ComponentType sortedComposite, Comparator<Component> comparator) throws CompositeException {
		// TODO Auto-generated method stub
		
	}

	

}
