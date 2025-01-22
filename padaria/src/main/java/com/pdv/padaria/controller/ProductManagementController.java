package com.pdv.padaria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class ProductManagementController {

    @FXML
    private TableView<?> productTable;  // Tabela onde os produtos serão listados

    // Método para adicionar um produto
    @FXML
    private void handleAddProduct() {
        // Lógica para adicionar produto (abrir outra tela ou exibir formulário)
        showAlert("Adicionar Produto", "Abrindo tela para adicionar um novo produto.");
    }

    // Método para remover um produto
    @FXML
    private void handleRemoveProduct() {
        // Lógica para remover produto
        showAlert("Remover Produto", "Produto removido com sucesso.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
