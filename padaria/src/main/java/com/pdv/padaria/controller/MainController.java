package com.pdv.padaria.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    private AnchorPane centerPanel;  // Área central onde o conteúdo será carregado

    // Método para carregar uma nova tela no painel central
    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            AnchorPane pane = loader.load();
            centerPanel.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funcionalidade para "Listar Produtos"
    @FXML
    private void handleListProducts() {
        loadFXML("ProductManagement.fxml");
    }

    // Funcionalidade para "Adicionar Produto"
    @FXML
    private void handleAddProduct() {
        loadFXML("AddProduct.fxml");
    }

    // Funcionalidade para "Editar Produto"
    @FXML
    private void handleEditProduct() {
        loadFXML("EditProduct.fxml");
    }

    // Funcionalidade para "Remover Produto"
    @FXML
    private void handleRemoveProduct() {
        loadFXML("RemoveProduct.fxml");
    }

    // Funcionalidade para "Registrar Venda"
    @FXML
    private void handleRegisterSale() {
        loadFXML("SaleRegistration.fxml");
    }

    // Funcionalidade para "Consultar Vendas"
    @FXML
    private void handleGenerateReport() {
        loadFXML("ReportGeneration.fxml");
    }
}
