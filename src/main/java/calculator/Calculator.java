package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import exception.CalculatotException;

public class Calculator {
	
    private static final String IS_NUMBER_REGEX = "\\d+(\\.\\d+)?";

    public int calculate(String expressionStr) throws CalculatotException {
    	
    	List<String> expression = new  ConverterToPolishNotation().convert(expressionStr);

        try {        	
            Deque<Integer> stack = new ArrayDeque<>();
            for (String operand : expression) {
                if (isNumber(operand)) {
                    stack.addFirst(Integer.parseInt(operand));
                } else {
                    int value1 = stack.removeFirst();
                    if (operand.equals(MathConstant.NOT_OPERATOR)) {
                        stack.addFirst(makeOperation(operand, value1, 0));
                    } else {
                        int value2 = stack.removeFirst();
                        stack.addFirst(makeOperation(operand, value2, value1));
                    }
                }
            }
            return stack.removeFirst();
        } catch (NoSuchElementException e) {
            throw new CalculatotException("Error_wrong_expression", e);
        }
    }

    private int makeOperation(String operation, int value1, int value2) throws CalculatotException {
        switch (operation) {
            case MathConstant.NOT_OPERATOR:
                return ~value1;
            case MathConstant.MULTIPLICATION_OPERATOR:
                return value1 * value2;
            case MathConstant.DIVISION_OPERATOR:
                return value1 / value2;
            case MathConstant.ADDITION_OPERATOR:
                return value1 + value2;
            case MathConstant.SUBTRACTION_OPERATOR:
                return value1 - value2;
            case MathConstant.LEFT_SHIFT_OPERATOR:
                return value1 << value2;
            case MathConstant.RIGHT_SHIFT_OPERATOR:
                return value1 >> value2;
            case MathConstant.AND_OPERATOR:
                return value1 & value2;
            case MathConstant.POWER_OPERATOR:
                return value1 ^ value2;
            case MathConstant.OR_OPERATOR:
                return value1 | value2;
            default:
                throw new CalculatotException("Error_wrong_expression");
        }
    }

    private boolean isNumber(String operand) {
        Pattern pattern = Pattern.compile(IS_NUMBER_REGEX);
        return pattern.matcher(operand).matches();
    }	

}
