package datastructure.tree;

/**
 * ClassName: TreeNode
 * Package: datastructure.tree
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/22 22:23
 * @Version 1.0
 */
public class TreeNode<E> {
    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E e) {
        this.element = e;
    }
}
