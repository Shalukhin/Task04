package runner;

import entity.composite.Component;
import exception.CompositeException;
import parser.Parser;
import parser.TypeOfParser;
import sort.Sorter;
import util.Reader;

public class Runner {

	public static void main(String[] args) throws CompositeException {
		
		Parser textParagr = new Parser(TypeOfParser.TEXT_TO_PARAGRAPHS);
		Parser paragrSent = new Parser(TypeOfParser.PARAGRAPHS_TO_SENTENCES);
		Parser sentLex = new Parser(TypeOfParser.SENTENCE_TO_LEXEMES);
		Parser lexWord = new Parser(TypeOfParser.LEXEME_TO_WORDS);
		Parser wordSymb = new Parser(TypeOfParser.WORDS_TO_SYMBOLS);
		
		textParagr.setNextParser(paragrSent);
		paragrSent.setNextParser(sentLex);
		sentLex.setNextParser(lexWord);
		lexWord.setNextParser(wordSymb);
		
		Component compositeText = null;
		try {
			compositeText = textParagr.parse(Reader.readTextFromResourceFile("text.txt"));
		} catch (Exception e) {
			System.out.println("Something went wrong((");
		}
		
		if (compositeText != null) {
			System.out.println(compositeText.getValue());
		}
		
				
		try {
			Sorter.sortTextByParagraphsIncreaseCountSentence(compositeText);
		} catch (CompositeException e) {
			System.out.println("Sorting wrong((");
		}
				
		System.out.println("Text after sort by count sentences in paragraph increase:");
		System.out.println();
		
		if (compositeText != null) {
			System.out.println(compositeText.getValue());
		}
	}
}
