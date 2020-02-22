package runner;

import entity.composite.Component;
import parser.Parser;
import parser.TypeOfParser;
import util.Reader;

public class Runner {

	public static void main(String[] args) {
		
		Parser textParagr = new Parser(TypeOfParser.TEXT_TO_PARAGRAPHS);
		Parser paragrSent = new Parser(TypeOfParser.PARAGRAPHS_TO_SENTENCES);
		Parser sentLex = new Parser(TypeOfParser.SENTENCE_TO_LEXEMES);
		Parser lexWord = new Parser(TypeOfParser.LEXEME_TO_WORDS);
		Parser wordSymb = new Parser(TypeOfParser.WORDS_TO_SYMBOLS);
		
		textParagr.setNextParser(paragrSent);
		paragrSent.setNextParser(sentLex);
		sentLex.setNextParser(lexWord);
		lexWord.setNextParser(wordSymb);
		
		Component compos = null;
		try {
			compos = textParagr.parse(Reader.readTextFromResourceFile("text.txt"));
		} catch (Exception e) {
			System.out.println("Something went wrong((");
		}
		
		if (compos != null) {
			System.out.println(compos.getValue());
		}
		

	}

}
