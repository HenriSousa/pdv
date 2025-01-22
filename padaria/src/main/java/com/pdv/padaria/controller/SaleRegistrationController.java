package com.pdv.padaria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SaleRegistrationController {
    @FXML
    private TextField productField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField totalField;

    // Método para registrar a venda
    @FXML
    private void handleRegisterSale() {
        // Lógica para calcular o total da venda
        double quantity = Double.parseDouble(quantityField.getText());
        double total = calculateTotal(quantity);
        totalField.setText(String.valueOf(total));
        showAlert("Venda Registrada", "Venda registrada com sucesso!");
    }

    private double calculateTotal(double quantity) {
        // Exemplo de cálculo, você pode integrar com a base de dados
        double productPrice = 10.0; // Exemplo de preço fixo
        return productPrice * quantity;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
