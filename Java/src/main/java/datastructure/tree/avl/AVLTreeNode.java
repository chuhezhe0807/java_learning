package datastructure.tree.avl;

import datastructure.tree.TreeNode;

/**
 * ClassName: AVLTreeNode
 * Package: datastructure.tree.avl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/24 19:29
 * @Version 1.0
 */
public class AVLTreeNode<E> extends TreeNode<E> {
    protected int height = 0;

    public AVLTreeNode(E e) {
        super(e);
    }
}
