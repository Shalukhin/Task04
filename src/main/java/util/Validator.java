package util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.Calculator;
import exception.CalculatotException;

public class Validator {
	
	public static boolean isExpression(String text) {
		String regex = "(>>)|(<<)|\\&|\\||~|\\^";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.find();
	}
	

	public static boolean isExpressionValid(String text) {
		if (!isBracketValid(text)) {
			return false;
		}
		Calculator calculator = new Calculator();
		try {
			int rez = calculator.calculate(text);
		} catch (CalculatotException e) {
			return false;
		}
		return true;
	}
	
	private static boolean isBracketValid(String text) {
		Deque<Character> stack = new ArrayDeque<Character>();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '(') {
				stack.push(text.charAt(i));
			}
			if (text.charAt(i) == ')') {
				char exist;
				try {
					exist = stack.pop();
				} catch (NoSuchElementException e) {
					return false;
				}
			}
		}
		
		return stack.isEmpty();
	}	
}
