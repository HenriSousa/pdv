package com.pdv.padaria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

public class ReportGenerationController {
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    // Método para gerar o relatório
    @FXML
    private void handleGenerateReport() {
        // Lógica para gerar o relatório baseado nas datas
        String startDate = startDatePicker.getValue().toString();
        String endDate = endDatePicker.getValue().toString();
        showAlert("Relatório Gerado", "Relatório gerado de " + startDate + " até " + endDate);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
