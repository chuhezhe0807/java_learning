package datastructure.tree.bst;

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
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
        this.element = e;
    }
}
