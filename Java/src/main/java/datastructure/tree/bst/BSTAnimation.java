package datastructure.tree.bst;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ClassName: BSTAnimation
 * Package: datastructure.tree
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/24 15:27
 * @Version 1.0
 */
public class BSTAnimation extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BST<Integer> tree = new BST<>();
        BSTView view = new BSTView(tree);
        BorderPane pane = new BorderPane();
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsert, btDelete);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());

            if(tree.search(key)) {
                view.displayTree();
                view.setStatus(key + " is already in the tree.");
            }
            else {
                tree.insert(key);
                view.displayTree();
                view.setStatus(key + " is inserted in the tree.");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());

            if(!tree.search(key)) {
                view.displayTree();
                view.setStatus(key + " is not in the tree.");
            }
            else {
                tree.delete(key);
                view.displayTree();
                view.setStatus(key + " is deleted from the tree.");
            }
        });

        // Create a scene and place the pane in the stage.
        Scene scene = new Scene(pane, 450, 250);
        stage.setTitle("BSTAnimation");
        stage.setScene(scene);
        stage.show();
    }
}
