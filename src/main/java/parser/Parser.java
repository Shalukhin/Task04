package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import entity.composite.Component;
import entity.composite.Composite;
import entity.composite.Leaf;
import exception.CompositeException;
import exception.ParserException;

public class Parser {

	TypeOfParser typeOfParser;
	private Parser nextParser;

	public Parser(TypeOfParser typeOfParser) {
		super();
		this.typeOfParser = typeOfParser;		
	}

	public TypeOfParser getTypeOfParser() {
		return typeOfParser;
	}

	public void setNextParser(Parser nextParser) {
		this.nextParser = nextParser;
	}

	public Component parse(String text) throws CompositeException, ParserException {		
		if (nextParser == null && !typeOfParser.equals(TypeOfParser.WORDS_TO_SYMBOLS)) {
			//LOGGER
			throw new ParserException("Error_next_parser_not_set");
		}
		
		Component component = new Composite();

		Pattern pattern = Pattern.compile(typeOfParser.getRegex());
		Matcher matcher = pattern.matcher(text);
		
		while (matcher.find()) {			
			if (typeOfParser.equals(TypeOfParser.WORDS_TO_SYMBOLS) && !matcher.group().isEmpty()) {				
				component.add(new Leaf(matcher.group().charAt(0)));
			} else {
				component.add(nextParser.parse(matcher.group(1)));
				if (!matcher.group(2).isEmpty()) {
					component.add(new Leaf(matcher.group(2).charAt(0)));
				}				
			}
		}

		return component;
	}
}
