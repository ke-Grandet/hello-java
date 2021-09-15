package org.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFxApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/layout.fxml"));
        fxmlLoader.setController(new Controller());
        primaryStage.setTitle("草稿");

        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.CHOCOLATE);
        primaryStage.setScene(scene);
        primaryStage.show();

//        Scene scene = new Scene(new Group(), 500, 500);
//        Group g = (Group) scene.getRoot();
//        g.getChildren().add(new Temp().init());
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}
