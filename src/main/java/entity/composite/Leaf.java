package entity.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.CompositeException;

public class Leaf implements Component {

	private static final Logger LOGGER = LogManager.getLogger(Leaf.class.getName());
	private static final String ERR_GET_CHILD = "Error_Unsupport_Operation";

	private char symbol;	

	public Leaf(char symbol) {
		super();
		this.symbol = symbol;
	}	
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
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

}
