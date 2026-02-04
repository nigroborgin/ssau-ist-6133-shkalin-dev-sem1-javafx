package ru.ssau.vyashka.labs.javafx.domain;

public class CalcBuffer {

    private Double number1 = Double.NaN;
    private Double number2 = Double.NaN;
    private Double result = Double.NaN;
    private Status status = Status.CLEAR;
    private Operation operation = Operation.NONE;

    /*
        ввели число1 - запомнили
        ввели операцию - запомнили
        ввели число2 - 1. вычислили число1-операция-число2; 2. запомнили результат
        ввели операцию - 1. запомнили операцию, 2. вывели результат; 2. запомнили результат как число1; 3. забыли число2;
    */

    public void inputNumber(Double number) {
        switch (status) {
            case CLEAR:
                number1 = number;
                status = Status.NUM_1;
                break;
            case NUM_1:
                number2 = number;
                status = Status.NUM_1_2;
                break;
        }
    }

    public Double plus() {
        if (operation != Operation.NONE) {
            switch (status) {
                case NUM_1:
                    operation = Operation.PLUS;
                    break;
                case NUM_1_2:
                    operation = Operation.PLUS;
                    break;
            }
        }
        return null;
    }

    public Double equal() {
        return result;
    }

    private Double calculate() {
        if (status == Status.NUM_1_2) {
            switch (operation) {
                case NONE:
                    // Ничего не делаем
                    System.err.println("CalcBuffer: вызван метод calculate(), но Операция не задана");
                    break;
                case PLUS:
                    result = number1 + number2;
                    operation = Operation.NONE;
                    break;
                case MINUS:
                    result = number1 - number2;
                    operation = Operation.NONE;
                    break;
                case MULTI:
                    result = number1 * number2;
                    operation = Operation.NONE;
                    break;
                case DIV:
                    result = number1 / number2;
                    operation = Operation.NONE;
                    break;
                case STEPEN:
                    result = Math.pow(number1, number2);
                    operation = Operation.NONE;
                    break;
            }
        } else if (status == Status.NUM_1) {
            switch (operation) {
                case SQRT:
                    result = Math.sqrt(number1);
                    operation = Operation.NONE;
                    break;
                default:
                    // TODO: все остальные случаи
                    break;
            }
        }
        return result;
    }

    public void clear() {
        number1 = Double.NaN;
        number2 = Double.NaN;
        result = Double.NaN;
    }

}
