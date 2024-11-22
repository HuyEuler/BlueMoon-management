package com.example.bluemoonmanagement.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TabController {

    public void initialize() {
        System.out.println("Call TabController");
    }

    public void loadHome() throws IOException{
        System.out.println("loadHome done");
    }

    public void loadResident() {
        System.out.println("loadResident done");
    }

    public void loadFee() {
        System.out.println("loadFee done");
    }

    public void loadAccount() {
        System.out.println("loadAccount done");
    }





}
