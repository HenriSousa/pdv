package com.pdv.padaria.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pdv.padaria.controller.ProdutoController;
import com.pdv.padaria.model.Produto;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@Component
@Scope("prototype")
public class ProdutoView extends Application {

    @Autowired
    private ProdutoController produtoController;

    private TableView<Produto> tableView;
    private ObservableList<Produto> produtoObservableList;

    private TextField nomeField;
    private TextField precoField;
    private TextField estoqueField;

    private VBox layout; // Layout principal

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestão de Produtos");

        // Configure a interface gráfica
        Parent root = criarLayout();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        carregarProdutos();
    }

    private Parent criarLayout() {
        // TableView
        tableView = new TableView<>();
        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));

        TableColumn<Produto, Double> precoColumn = new TableColumn<>("Preço");
        precoColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPreco()));

        TableColumn<Produto, Integer> estoqueColumn = new TableColumn<>("Estoque");
        estoqueColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getEstoque()));

        tableView.getColumns().addAll(nomeColumn, precoColumn, estoqueColumn);

        // Input Fields
        nomeField = new TextField();
        nomeField.setPromptText("Nome");

        precoField = new TextField();
        precoField.setPromptText("Preço");

        estoqueField = new TextField();
        estoqueField.setPromptText("Estoque");

        // Buttons
        Button adicionarButton = new Button("Adicionar");
        adicionarButton.setOnAction(e -> salvarProduto());

        Button atualizarButton = new Button("Atualizar");
        atualizarButton.setOnAction(e -> atualizarProduto());

        Button excluirButton = new Button("Excluir");
        excluirButton.setOnAction(e -> excluirProduto());

        // Layout
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(new Label("Nome:"), 0, 0);
        inputGrid.add(nomeField, 1, 0);
        inputGrid.add(new Label("Preço:"), 0, 1);
        inputGrid.add(precoField, 1, 1);
        inputGrid.add(new Label("Estoque:"), 0, 2);
        inputGrid.add(estoqueField, 1, 2);
        inputGrid.add(adicionarButton, 0, 3);
        inputGrid.add(atualizarButton, 1, 3);
        inputGrid.add(excluirButton, 2, 3);

        layout = new VBox(10, tableView, inputGrid); // Inicializa o layout principal
        layout.setPadding(new Insets(10));

        return layout;
    }

    public Parent getView() {
        // Retorna o layout principal para ser usado em outros lugares
        if (layout == null) {
            criarLayout(); // Certifique-se de que o layout foi inicializado
        }
        return layout;
    }

    private void carregarProdutos() {
        try {
            List<Produto> produtos = produtoController.listarTodos().getBody();
            produtoObservableList = FXCollections.observableArrayList(produtos);
            tableView.setItems(produtoObservableList);
        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao carregar produtos: " + e.getMessage());
        }
    }

    private void salvarProduto() {
        try {
            String nome = nomeField.getText();
            Double preco = Double.parseDouble(precoField.getText());
            Integer estoque = Integer.parseInt(estoqueField.getText());

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setEstoque(estoque);

            produtoController.salvar(produto);
            carregarProdutos();

            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Formato", "Por favor, insira valores válidos para preço e estoque.");
        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao salvar produto: " + e.getMessage());
        }
    }

    private void atualizarProduto() {
        Produto selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                String nome = nomeField.getText();
                Double preco = Double.parseDouble(precoField.getText());
                Integer estoque = Integer.parseInt(estoqueField.getText());

                selecionado.setNome(nome);
                selecionado.setPreco(preco);
                selecionado.setEstoque(estoque);

                produtoController.atualizar(selecionado.getId(), selecionado);
                carregarProdutos();

                limparCampos();
            } catch (NumberFormatException e) {
                mostrarAlerta("Erro de Formato", "Por favor, insira valores válidos para preço e estoque.");
            } catch (Exception e) {
                mostrarAlerta("Erro", "Falha ao atualizar produto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um produto para atualizar.");
        }
    }

    private void excluirProduto() {
        Produto selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                produtoController.excluir(selecionado.getId());
                carregarProdutos();
            } catch (Exception e) {
                mostrarAlerta("Erro", "Falha ao excluir produto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um produto para excluir.");
        }
    }

    private void limparCampos() {
        nomeField.clear();
        precoField.clear();
        estoqueField.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
