package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import calculator.Calculator;
import entity.composite.Component;
import entity.composite.Composite;
import entity.composite.LeafChar;
import exception.CalculatotException;
import exception.CompositeException;
import exception.ParserException;
import util.Validator;

public class Parser {
	
	private static final Logger LOGGER = LogManager.getLogger(Parser.class.getName());
	private static final String ERR_PARSE = "Error_parse_text_is_null";
	private static final String ERR_NEXT_PARSE = "Error_next_parser_not_set";

	private TypeOfParser typeOfParser;
	private Parser nextParser;

	public Parser(TypeOfParser typeOfParser) {
		super();
		this.typeOfParser = typeOfParser;		
	}	

	public void setNextParser(Parser nextParser) {
		this.nextParser = nextParser;
	}


	public TypeOfParser getTypeOfParser() {
		return typeOfParser;
	}

	public void LOGGER(Parser nextParser) {
		this.nextParser = nextParser;
	}

	public Component parse(String text) throws CompositeException, ParserException, CalculatotException {	
		if (text == null) {
			LOGGER.error(ERR_PARSE);
			throw new ParserException(ERR_PARSE);
		}
		if (nextParser == null && !typeOfParser.equals(TypeOfParser.WORDS_TO_SYMBOLS)) {
			LOGGER.error(ERR_NEXT_PARSE);
			throw new ParserException(ERR_NEXT_PARSE);
		}
		
		Component component = new Composite(typeOfParser.getTypeOfReturnedConposite());

		Pattern pattern = Pattern.compile(typeOfParser.getRegex());
		Matcher matcher = pattern.matcher(text);
		
		while (matcher.find()) {			
			if (typeOfParser.equals(TypeOfParser.WORDS_TO_SYMBOLS) && !matcher.group().isEmpty()) {				
				component.add(new LeafChar(matcher.group().charAt(0)));
			} else {
				String fragment = matcher.group(1);
				if (typeOfParser.equals(TypeOfParser.LEXEME_TO_WORDS) && Validator.isExpression(fragment) && Validator.isExpressionValid(fragment)) {
					fragment = calculateExpression(fragment);
				}
				component.add(nextParser.parse(fragment));
				if (!matcher.group(2).isEmpty()) {
					component.add(new LeafChar(matcher.group(2).charAt(0)));
				}				
			}
		}

		return component;
	}
	
	private String calculateExpression(String expression) throws CalculatotException {
		Calculator calculator = new Calculator();
		return String.valueOf(calculator.calculate(expression));
	}
}
