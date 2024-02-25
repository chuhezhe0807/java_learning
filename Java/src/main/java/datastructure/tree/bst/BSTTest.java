package datastructure.tree.bst;

import datastructure.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * ClassName: BSTTest
 * Package: datastructure.tree
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/23 22:07
 * @Version 1.0
 */
public class BSTTest {
    @Test
    public void test01() {
        BST<String> tree = new BST<>();
        tree.insert("A");
        tree.insert("B");
        tree.insert("C");
        tree.insert("D");
        tree.insert("E");
        tree.insert("O");
        tree.insert("F");
        tree.insert("G");
        tree.insert("H");

        System.out.println("Inorder ");
        tree.inorder();
        System.out.println("\nPostorder");
        tree.postorder();
        System.out.println("\npreorder");
        tree.preorder();

        System.out.println("\n节点元素的个数 " + tree.getSize());

        // Get a path from root to D.
        ArrayList<TreeNode<String>> pathList = tree.path("D");
        pathList.forEach((node) -> System.out.print(node.element + " "));
    }

    @Test
    public void test02() {
        Integer[] numbers = new Integer[]{2, 4, 3, 1, 8, 5, 6, 7};
        BST<Integer> tree = new BST<>(numbers);

        System.out.println("Inorder ");
        tree.inorder();

        System.out.println("\nPostorder ");
        tree.postorder();

        System.out.println("\nPreorder ");
        tree.preorder();
    }

    @Test
    public void test03() {
        Integer[] numbers = new Integer[]{2, 4, 3, 1, 8, 5, 6, 7};
        BST<Integer> tree = new BST<>(numbers);

        // 删除元素
//        tree.delete(8);
//        tree.inorder();

        // 使用迭代器遍历二叉树
        for(Integer node : tree) {
            System.out.println(node);
        }
    }
}
