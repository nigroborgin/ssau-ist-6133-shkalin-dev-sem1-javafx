package ru.ssau.vyashka.labs.javafx.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.ssau.vyashka.labs.javafx.domain.CalcBuffer;

public class CalcController {

    @FXML
    private Label label;

    private final CalcBuffer calcBuffer = new CalcBuffer();

    @FXML
    public void initialize() {
        updateView();

        label.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> obs, Scene oldScene, Scene newScene) {
                if (newScene != null) {
                    newScene.addEventFilter(
                            KeyEvent.KEY_PRESSED,
                            keyEvent -> CalcController.this.handleKeyPressed(keyEvent));
                }
            }
        });
    }

    @FXML
    protected void onDigitClick(ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();

        calcBuffer.addDigitOrComma(digit);
        updateView();
    }

    @FXML
    protected void onPlusClick() {
        calcBuffer.setOperation(CalcBuffer.Operation.PLUS);
        updateView();
    }

    @FXML
    protected void onMinusClick() {
        calcBuffer.setOperation(CalcBuffer.Operation.MINUS);
        updateView();
    }

    @FXML
    protected void onDivideClick() {
        calcBuffer.setOperation(CalcBuffer.Operation.DIV);
        updateView();
    }

    @FXML
    protected void onMultiplyClick() {
        calcBuffer.setOperation(CalcBuffer.Operation.MULTI);
        updateView();
    }

    @FXML
    protected void onEqualClick() {
        calcBuffer.calculate();
        updateView();
    }

    @FXML
    protected void onPowerClick() {
        calcBuffer.setOperation(CalcBuffer.Operation.POWER);
        updateView();
    }

    @FXML
    protected void onSqrtClick() {
        calcBuffer.executeUnary(CalcBuffer.Operation.SQRT);
        updateView();
    }

    @FXML
    protected void onClearClick() {
        calcBuffer.clear();
        updateView();
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        String text = keyEvent.getText();
        KeyCode code = keyEvent.getCode();

        if (text.matches("[0-9]") || text.equals(".") || text.equals(","))
            calcBuffer.addDigitOrComma(text);
        else if (text.equals("+") || code == KeyCode.ADD)
            calcBuffer.setOperation(CalcBuffer.Operation.PLUS);
        else if (text.equals("-") || code == KeyCode.SUBTRACT)
            calcBuffer.setOperation(CalcBuffer.Operation.MINUS);
        else if (text.equals("*") || code == KeyCode.MULTIPLY)
            calcBuffer.setOperation(CalcBuffer.Operation.MULTI);
        else if (text.equals("/") || code == KeyCode.DIVIDE)
            calcBuffer.setOperation(CalcBuffer.Operation.DIV);
        else if (text.equals("=") || code == KeyCode.EQUALS || code == KeyCode.ENTER)
            calcBuffer.calculate();
        else if (code == KeyCode.BACK_SPACE || code == KeyCode.DELETE) {
            calcBuffer.clear();
        }

        updateView();
    }

    private void updateView() {
        label.setText(calcBuffer.getText());
    }

}