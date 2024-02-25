package datastructure.tree.avl;

import datastructure.tree.bst.BST;
import org.junit.jupiter.api.Test;

/**
 * ClassName: AVLTreeTest
 * Package: datastructure.tree.avl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/24 23:51
 * @Version 1.0
 */
public class AVLTreeTest {
    @SuppressWarnings("rawtypes")
    public static void printTree(BST tree) {
        // Traverse tree.
        System.out.print("\nInorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder: ");
        tree.postorder();
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.println("\nThe number of nodes is " + tree.getSize());
    }

    @Test
    public void test01() {
        // Create an AVL tree.
        AVLTree<Integer> tree = new AVLTree<>(new Integer[]{25, 20, 5});
        System.out.print("After inserting 25, 20, 5: ");
        printTree(tree);

        tree.insert(34);
        tree.insert(50);
        System.out.print("\nAfter inserting 34, 50");
        printTree(tree);

        tree.insert(30);
        System.out.print("\nAfter inserting 30");
        printTree(tree);

        tree.insert(10);
        System.out.print("\nAfter inserting 10");
        printTree(tree);

        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.print("\nAfter removing 34, 30, 50");
        printTree(tree);

        tree.delete(5);
        System.out.print("\nAfter removing 5");
        printTree(tree);

        System.out.println("Traverse the tree.");
        for(Integer e : tree) {
            System.out.print(e + " ");
        }
    }

    @Test
    public void test02() {
        AVLTree<Integer> tree = new AVLTree<>(new Integer[]{25, 20, 5});
    }
}
