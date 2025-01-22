package com.pdv.padaria.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pdv.padaria.controller.UsuarioController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@Component
@Scope("prototype")
public class LoginView extends Application {

    @Autowired
    private UsuarioController usuarioController;

    private TextField emailField;
    private PasswordField senhaField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Layout principal
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.setVgap(10);

        // Campos de entrada
        emailField = new TextField();
        emailField.setPromptText("Email");

        senhaField = new PasswordField();
        senhaField.setPromptText("Senha");

        // Botão de login
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> autenticarUsuario(primaryStage));

        // Adicionar elementos ao layout
        layout.add(new Label("Email:"), 0, 0);
        layout.add(emailField, 1, 0);
        layout.add(new Label("Senha:"), 0, 1);
        layout.add(senhaField, 1, 1);
        layout.add(loginButton, 1, 2);

        // Configurar cena
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void autenticarUsuario(Stage stage) {
        String email = emailField.getText();
        String senha = senhaField.getText();

        try {
            boolean autenticado = usuarioController.autenticar(email, senha);

            if (autenticado) {
                // Abrir a tela principal (ProdutoView)
                ProdutoView produtoView = usuarioController.getContext().getBean(ProdutoView.class);
                produtoView.start(stage);
            } else {
                mostrarAlerta("Erro de Login", "Email ou senha inválidos!");
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao autenticar: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
