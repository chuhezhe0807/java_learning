package datastructure.tree.bst;

import datastructure.tree.TreeNode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * ClassName: BSTView
 * Package: datastructure.tree
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/24 15:36
 * @Version 1.0
 */
public class BSTView extends Pane {
    private final BST<Integer> tree;
    private final double vGap = 50; // 二叉树两个层级之间的间距


    protected BSTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty.");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane.

        if(tree.getRoot() != null) {
            // 递归地展示二叉树
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    // Display a subtree rooted at position (x, y).
    private void displayTree(TreeNode<Integer> root, double x, double y, double hGap) {
        if(root.left != null) {
            // 连接左节点的线
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // 递归地画出左边的子树
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if(root.right != null) {
            // 连接右节点的线
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // 递归地画出右边的子树
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        // 绘制当前节点
        Circle circle = new Circle(x, y, 15);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
}
