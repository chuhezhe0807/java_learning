package datastructure.tree.bst;

import datastructure.tree.Tree;
import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Binary search tree
 *      二叉搜索树
 *
 * 中序遍历、前序遍历和后序遍历的时间复杂度都是O(n) (递归子问题等规模都符合master公式应用条件 log(2, 2) > 0 -> O(N^log(b, a)) --> O(n))
 * 查找、插入和删除的时间复杂度是树的高度，在最差情况下，树的高度为O(n)，平均而言树的高度为O(logn)，因此平均时间为O(logn)
 *
 * ClassName: BST
 * Package: datastructure.tree
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/22 22:01
 * @Version 1.0
 */
public class BST<E extends Comparable<E>> implements Tree<E>{
    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {}

    public BST(E[] objects) {
        for (E object : objects) {
            add(object);
        }
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

        while(current != null) {
            if(e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if(e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean insert(E e) {
        if(root == null) {
            root = createNewNode(e);
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while(current != null) {
                if(e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if(e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else {
                    // 重复的Node不允许添加
                    return false;
                }
            }

            if(e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            }
            else {
                parent.right = createNewNode(e);
            }
        }

        size++;

        return true;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while(current != null) {
            if(e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if(e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else {
                // 找到该元素了
                break;
            }
        }

        if(current == null) {
            return false;
        }

        if(current.left == null) {
            if(parent == null) {
                root = current.right;
            }
            else {
                if(e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                }
                else {
                    parent.right = current.right;
                }
            }
        }
        else {
            // 找到要删除的节点的左侧最大的元素和其父元素
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while(rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            // 替换要删除的元素为该元素的左侧最大值
            current.element = rightMost.element;

            // 删除左侧最大值
            if(parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            }
            else {
                // 这种情况是要删除的元素的左侧最大值就是该元素的左子节点
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<E> {
        private final ArrayList<E> list = new ArrayList<>();
        private int current = 0; // list中指向当前元素的指针

        public InorderIterator() {
            // 遍历二叉树并在list中存储元素
            inorder();
        }

        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if(root == null) {
                return;
            }

            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            if(current == 0) {
                throw new IllegalStateException();
            }

            delete(list.get(--current));
            list.clear();
            // 每次在迭代其中remove元素都会重新build一次list，性能不好，所以应该避免在迭代器中删除元素
            inorder(); // Rebuild the list.
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if(root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root) {
        if(root == null) {
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    public void preorder(TreeNode<E> root) {
        if(root == null) {
            return;
        }

        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    // 查找指定元素需要查找的路径（从root开始到目标元素的查找路径）
    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while(current != null) {
            list.add(current);

            if(e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if(e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else {
                break;
            }
        }

        return list;
    }
}
