package ru.ssau.vyashka.labs.javafx.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalcBuffer {

    private Double storedResult = null;
    private String currentText = "0";
    private Operation pendingOperation = Operation.NONE;
    private boolean isNewInputState = true;
    private String errorMessage = null;
    private final DecimalFormat formatter;

    public CalcBuffer() {
        formatter = new DecimalFormat("0.##########", DecimalFormatSymbols.getInstance(Locale.US));
    }

    public enum Operation {
        NONE, PLUS, MINUS, MULTI, DIV, SQRT, POWER
    }

    public void addDigitOrComma(String digit) {
        if (errorMessage != null) clear();
        boolean isComma = digit.equals(".") || digit.equals(",");

        if (isNewInputState) {
            if (isComma) {
                currentText = "0.";
            } else {
                currentText = digit;
            }
            isNewInputState = false;
        } else {
            if (isComma && currentText.contains(".")) {
                return;
            }
            currentText += digit;
        }
    }

    public void setOperation(Operation operation) {
        if (errorMessage != null) return;

        if (pendingOperation != Operation.NONE && !isNewInputState) {
            calculate();
        } else {
            try {
                storedResult = Double.parseDouble(currentText);
            } catch (NumberFormatException e) {
                storedResult = 0.0;
            }
        }

        pendingOperation = operation;
        isNewInputState = true;
    }

    public void calculate() {
        if (pendingOperation == Operation.NONE || storedResult == null) return;

        double secondOperand;
        try {
            secondOperand = Double.parseDouble(currentText);
        } catch (NumberFormatException e) {
            return;
        }

        double result = 0;

        switch (pendingOperation) {
            case PLUS -> result = storedResult + secondOperand;
            case MINUS -> result = storedResult - secondOperand;
            case MULTI -> result = storedResult * secondOperand;
            case DIV -> {
                if (secondOperand == 0) {
                    showError("Ошибка: Деление на Ноль");
                    return;
                }
                result = storedResult / secondOperand;
            }
            case POWER -> result = Math.pow(storedResult, secondOperand);
        }

        storedResult = result;
        currentText = formatter.format(result);
        pendingOperation = Operation.NONE;
        isNewInputState = true;
    }

    public void executeUnary(Operation operation) {
        double currentValue = Double.parseDouble(currentText);
        if (operation == Operation.SQRT) {
            if (currentValue < 0) {
                showError("Ошибка: Взятие корня из отрицательного числа");
                return;
            }
            double res = Math.sqrt(currentValue);
            currentText = formatter.format(res);
            isNewInputState = true;
        }
    }

    public void clear() {
        storedResult = null;
        currentText = "0";
        pendingOperation = Operation.NONE;
        isNewInputState = true;
        errorMessage = null;
    }

    private void showError(String message) {
        errorMessage = message;
        currentText = message;
        isNewInputState = true;
        storedResult = null;
        pendingOperation = Operation.NONE;
    }

    public String getText() {
        return currentText;
    }
}