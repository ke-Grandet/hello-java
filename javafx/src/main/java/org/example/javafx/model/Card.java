package org.example.javafx.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card extends RecursiveTreeObject<Card> {
    StringProperty iccID;
    StringProperty cost;

    public Card(String iccID, String cost) {
        this.iccID = new SimpleStringProperty(iccID);
        this.cost = new SimpleStringProperty(cost);
    }

    public StringProperty getIccID() {
        return iccID;
    }

    public StringProperty getCost() {
        return cost;
    }

    public void setCost(StringProperty cost) {
        this.cost = cost;
    }
}
