package parser;

public enum TypeOfParser {
	
	TEXT_TO_PARAGRAPHS("([^\\n]+)([\\n]*)"),
	PARAGRAPHS_TO_SENTENCES("([^.!?]+[.!?])([\\s]*)"),
	SENTENCE_TO_LEXEMES("([^\\s]+)([\\s]*)"),
	LEXEME_TO_WORDS("([^,.!:]+)([,.!:]*)"),
	WORDS_TO_SYMBOLS(".");

	private String regex;	

	private TypeOfParser(String regex) {
		this.regex = regex;		
	}

	public String getRegex() {
		return regex;
	}
}
