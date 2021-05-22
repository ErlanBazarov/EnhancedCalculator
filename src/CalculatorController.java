import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class CalculatorController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentage = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15);

    @FXML
    private Label amountLabel;

    @FXML
    private Label percentageLabel;

    @FXML
    private Label tipLabel;

    @FXML
    private Slider percentageSlider;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label totalLabel;

    @FXML
    private Button calculateButton;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private Label peopleCountLabel;

    @FXML
    private TextField peopleCountTextField;

    @FXML
    private Label perPersonLabel;

    @FXML
    private TextField perPersonTextField;

    @FXML
    void calculateButtonPressed(ActionEvent event) {
      BigDecimal amount = new BigDecimal(amountTextField.getText());
      BigDecimal tip = amount.multiply(tipPercentage);
      BigDecimal total = amount.add(tip);
      BigDecimal peopleAmount = new BigDecimal(peopleCountTextField.getText());
      
      tipTextField.setText(currency.format(tip));
      totalTextField.setText(currency.format(total));
      perPersonTextField.setText(currency.format(total.divide(peopleAmount)));
    }

    public void initialize() {
      currency.setRoundingMode(RoundingMode.HALF_UP);

      percentageSlider.valueProperty().addListener(
        new ChangeListener<Number>(){
          @Override
          public void changed(javafx.beans.value.ObservableValue<? extends Number> arg0, Number oldNumber, Number newNumber) {
            tipPercentage = new BigDecimal(newNumber.intValue() / 100.0);
            percentageLabel.setText(percentage.format(tipPercentage));
          };
        }
      );
    }

}
