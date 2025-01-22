package com.pdv.padaria;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pdv.padaria.view.LoginView;

import javafx.application.Application;
import javafx.stage.Stage;

public class SpringJavaFXApp extends Application {
    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(PadariaApplication.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    LoginView loginView = springContext.getBean(LoginView.class);
    loginView.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
