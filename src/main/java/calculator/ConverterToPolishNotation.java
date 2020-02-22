package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.CalculatotException;

public class ConverterToPolishNotation {
	
	
    private static final char SINGLE_DOT = '.';
    private static final Logger LOGGER = LogManager.getLogger(ConverterToPolishNotation.class);

    private int index;

    public List<String> convert(String expression) throws CalculatotException {
       
       
        List<String> result = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        index = 0;
        while (index < expression.length()) {
            if (Character.isDigit(expression.charAt(index))) {
                index = putNumberToResult(expression, result, index);
            } else if (expression.charAt(index) == MathConstant.NOT_OPERATOR.charAt(0) || expression.charAt(index) == MathConstant.OPENING_BRACKET_OPERATOR.charAt(0)) {
                stack.addFirst(String.valueOf(expression.charAt(index)));
                index++;
            } else if (expression.charAt(index) == MathConstant.CLOSING_BRACKET_OPERATOR.charAt(0)) {
                if (!putOperatorsFromStackToResultUntilFoundOpeningBracket(result, stack)) {
                    return new ArrayList<>();
                }
            } else {
                String operator = getOperatorAsString(expression);
                index = putHighPriorityOperatorsFromStackToResult(result, stack, index, operator);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.removeFirst());
        }
        return result;
    }

    private boolean putOperatorsFromStackToResultUntilFoundOpeningBracket(List<String> result, Deque<String> stack) {
        String stackElement;
        try {
            while (!(stackElement = stack.removeFirst()).equals(MathConstant.OPENING_BRACKET_OPERATOR)) {
                result.add(stackElement);
            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Incorrect expression");
            return false;
        }
        index++;
        return true;
    }

    private String getOperatorAsString(String expression) {
        String operator;
        if (expression.charAt(index) == MathConstant.LEFT_SHIFT_OPERATOR.charAt(0) || expression.charAt(index) == MathConstant.RIGHT_SHIFT_OPERATOR.charAt(0)) {
            operator = expression.substring(index, index + 2);
            index++;
        } else {
            operator = expression.substring(index, index + 1);
        }
        return operator;
    }

    private int putHighPriorityOperatorsFromStackToResult(List<String> result, Deque<String> stack, int index, String currentOperator) throws CalculatotException {
        String stackElement;
        while (!stack.isEmpty()) {
            stackElement = stack.removeFirst();
            if (getOperatorPriority(stackElement) < getOperatorPriority(currentOperator)) {
                result.add(stackElement);
            } else {
                stack.addFirst(stackElement);
                break;
            }
        }
        stack.addFirst(currentOperator);
        index++;
        return index;
    }

    private int putNumberToResult(String expression, List<String> result, int index) {
        int numberStart = index;
        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            index++;
        }
        if (index < expression.length() && expression.charAt(index) == SINGLE_DOT) {
            index++;
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                index++;
            }
        }
        String number = expression.substring(numberStart, index);
        result.add(number);
        return index;
    }

    private int getOperatorPriority(String operator) throws CalculatotException {
        switch (operator) {
            case MathConstant.NOT_OPERATOR:
                return 1;
            case MathConstant.MULTIPLICATION_OPERATOR:
            case MathConstant.DIVISION_OPERATOR:
                return 2;
            case MathConstant.ADDITION_OPERATOR:
            case MathConstant.SUBTRACTION_OPERATOR:
                return 3;
            case MathConstant.LEFT_SHIFT_OPERATOR:
            case MathConstant.RIGHT_SHIFT_OPERATOR:
                return 4;
            case MathConstant.AND_OPERATOR:
                return 5;
            case MathConstant.POWER_OPERATOR:
                return 6;
            case MathConstant.OR_OPERATOR:
                return 7;
            case MathConstant.OPENING_BRACKET_OPERATOR:
            case MathConstant.CLOSING_BRACKET_OPERATOR:
                return 9;
            default:
            	LOGGER.error("Error_Wrong_expression");
                throw new CalculatotException("Error_Wrong_expression");
        }
    }

}
