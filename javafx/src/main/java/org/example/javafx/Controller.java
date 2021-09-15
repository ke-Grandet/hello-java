package org.example.javafx;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import org.example.javafx.model.Card;
import org.example.javafx.model.RatePlan;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TreeTableView<RatePlan> treeTableViewRatePlan;
    @FXML
    JFXTreeTableView<Card> jfxTreeTableViewCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initJFXTreeTableViewCard();
        initTreeTableViewRatePlan();
    }

    void initJFXTreeTableViewCard() {
        // 初始值
        jfxTreeTableViewCard.getColumns().clear();
        treeTableViewRatePlan.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Card> cards = FXCollections.observableArrayList(Arrays.asList(
                new Card("1", "100"), new Card("2", "200")
        ));
        TreeItem<Card> treeItemCard = new RecursiveTreeItem<>(cards, RecursiveTreeObject::getChildren);
        jfxTreeTableViewCard.setRoot(treeItemCard);
        jfxTreeTableViewCard.setEditable(true);
        jfxTreeTableViewCard.setShowRoot(false);
        // 第一列
        JFXTreeTableColumn<Card, String> iccIDColumn = new JFXTreeTableColumn<>("iccID");
        iccIDColumn.setPrefWidth(150);
        iccIDColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Card, String> param) -> {
            if (iccIDColumn.validateValue(param))
                return param.getValue().getValue().getIccID();
            else
                return iccIDColumn.getComputedValue(param);
        });
        // 第二列
        JFXTreeTableColumn<Card, String> costColumn = new JFXTreeTableColumn<>("cost");
        costColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Card, String> param) ->
                costColumn.validateValue(param)
                        ? param.getValue().getValue().getCost() : costColumn.getComputedValue(param)
        );
        // 单元格可编辑
        costColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<Card, String> t) -> {
            t.getTreeTableView().getTreeItem(
                    t.getTreeTablePosition().getRow()
            ).getValue().getCost()
                    .set(t.getNewValue());
        });
        costColumn.setCellFactory((TreeTableColumn<Card, String> param) ->
            new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder())
        );
        // 列加入表格
        jfxTreeTableViewCard.getColumns().setAll(Arrays.asList(iccIDColumn, costColumn));
    }

    void initTreeTableViewRatePlan() {
        TreeItem<RatePlan> itemParent = new TreeItem<>();
        itemParent.getChildren().addAll(Arrays.asList(
                new TreeItem<>(new RatePlan().init(false)),
                new TreeItem<>(new RatePlan().init(true))
        ));
        treeTableViewRatePlan.setShowRoot(false);
        treeTableViewRatePlan.setRoot(itemParent);

        treeTableViewRatePlan.getColumns().clear();
        treeTableViewRatePlan.setEditable(true);
        treeTableViewRatePlan.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

        TreeTableColumn<RatePlan, CheckBox> selectColumn = new TreeTableColumn<>();
        selectColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<RatePlan, CheckBox> p) ->
                new SimpleObjectProperty<>(p.getValue().getValue().getCheckBox()));
        treeTableViewRatePlan.getColumns().add(selectColumn);

        TreeTableColumn<RatePlan, ChoiceBox<String>> choiceBoxTableColumn = new TreeTableColumn<>();
        choiceBoxTableColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<RatePlan, ChoiceBox<String>> p) ->
                new SimpleObjectProperty<>(p.getValue().getValue().getChoiceBox()));
        treeTableViewRatePlan.getColumns().add(choiceBoxTableColumn);

        TreeTableColumn<RatePlan, JFXComboBox<String>> comboBoxTableColumn = new TreeTableColumn<>();
        comboBoxTableColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<RatePlan, JFXComboBox<String>> p) ->
                new SimpleObjectProperty<>(p.getValue().getValue().getComboBox()));
        treeTableViewRatePlan.getColumns().add(comboBoxTableColumn);
    }
}
