import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ATMGUI {
    private ATM atm;
    private TextField pinField, amountField;
    private Button checkBalanceButton, depositButton, withdrawButton;
    private Label insertCardLabel;

    public ATMGUI(ATM atm) {
        this.atm = atm;

        Label pinLabel = new Label("PIN:");
        pinField = new TextField();
        Label amountLabel = new Label("Amount:");
        amountField = new TextField();
        checkBalanceButton = new Button("Check Balance");
        depositButton = new Button("Deposit");
        withdrawButton = new Button("Withdraw");
        insertCardLabel = new Label("Please insert your ATM card");

        checkBalanceButton.setOnAction(e -> checkBalance());
        depositButton.setOnAction(e -> deposit());
        withdrawButton.setOnAction(e -> withdraw());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(
                insertCardLabel,
                new Label(),
                pinLabel,
                pinField,
                amountLabel,
                amountField,
                checkBalanceButton,
                depositButton,
                withdrawButton,
                new Label() 
        );

        Scene scene = new Scene(root, 400, 300);

        // Set stage
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ATM Machine");
        stage.show();
    }

    private void checkBalance() {
        String pin = pinField.getText();
        if (!pin.equals("1234")) {
            showError("Invalid PIN. Please try again.");
            return;
        }

        double balance = atm.checkBalance();
        showMessage("Your balance is: $" + balance);
    }

    private void deposit() {
        String pin = pinField.getText();
        if (!pin.equals("1234")) {
            showError("Invalid PIN. Please try again.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid amount. Please enter a valid number.");
            return;
        }

        atm.executeTransaction(new DepositTransaction(amount));
        showMessage("Deposit successful.");
    }

    private void withdraw() {
        String pin = pinField.getText();
        if (!pin.equals("1234")) {
            showError("Invalid PIN. Please try again.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid amount. Please enter a valid number.");
            return;
        }

        boolean success = atm.executeTransaction(new WithdrawalTransaction(amount));
        if (success) {
            showMessage("Withdrawal successful.");
        } else {
            showError("Insufficient funds.");
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
