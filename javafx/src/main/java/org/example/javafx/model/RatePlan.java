package org.example.javafx.model;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class RatePlan {

    CheckBox checkBox = new CheckBox();
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    JFXComboBox<String> comboBox = new JFXComboBox<>();

    public RatePlan() {
        choiceBox.setItems(FXCollections.observableArrayList("org/example/main", "world"));
        comboBox.setItems(FXCollections.observableArrayList("org/example/main", "world"));
    }

    public RatePlan init(boolean b) {
        checkBox.setSelected(b);
        return this;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public void setChoiceBox(ChoiceBox<String> choiceBox) {
        this.choiceBox = choiceBox;
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public void setComboBox(JFXComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public JFXComboBox<String> getComboBox() {
        return comboBox;
    }
}
