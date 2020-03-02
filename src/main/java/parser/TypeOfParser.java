package parser;


import entity.composite.ComponentType;
import static entity.composite.ComponentType.*;

public enum TypeOfParser {
	
	TEXT_TO_PARAGRAPHS("([^\\n]+)([\\n]*)", TEXT),
	PARAGRAPHS_TO_SENTENCES("([^.!?]+[.!?])([\\s]*)", PARAGRAPH),
	SENTENCE_TO_LEXEMES("([^\\s]+)([\\s]*)", SENTENCE),
	LEXEME_TO_WORDS("([^,.!:]+)([,.!:]*)", LEXEME),
	WORDS_TO_SYMBOLS(".", WORD);

	private String regex;	
	private ComponentType typeOfReturnedConposite;

	private TypeOfParser(String regex, ComponentType typeOfReturnedConposite) {
		this.regex = regex;
		this.typeOfReturnedConposite = typeOfReturnedConposite;
	}

	public String getRegex() {
		return regex;
	}

	public ComponentType getTypeOfReturnedConposite() {
		return typeOfReturnedConposite;
	}
	
}
