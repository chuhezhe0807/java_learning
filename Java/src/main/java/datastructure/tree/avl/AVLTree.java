package datastructure.tree.avl;

import datastructure.tree.TreeNode;
import datastructure.tree.bst.BST;

import java.util.ArrayList;

/**
 * AVLTree 平衡二叉搜索树（平衡的树，即每个节点的两棵子树高度基本一致）
 *
 * 节点的平衡因子
 *      节点的右子树高度减去左子树高度，如果二叉树的所有结点的平衡因子绝对值小于等于1的称为良好平衡二叉树
 *
 *  插入或删除一个元素后，计算插入或删除时查找的路径的平衡因子，如果有元素平衡因子绝对值超过1了，则需要调整
 *      按照元素插入的位置分为LL, RR, LR, RR四种情况(参考 AVL树重新平衡的四种方法.png 和 AVL树重新平衡的四种方法（结果）.png)
 *
 *  AVL树的高度为O(logn)
 *      search、insert及delete方法只涉及树中沿着一条路径上的结点，updateHeight和getBalanceFactor方法对于路径上的每一个结点
 *      来说执行常量时间，balancePath 对于路径上的点也是执行常量的时间 所以 search、insert及delete方法 的时间复杂度为O(logn)
 *
 * ClassName: AVLTree
 * Package: datastructure.tree.avl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/24 17:57
 * @Version 1.0
 */
public class AVLTree<E extends Comparable<E>> extends BST<E> {
    public AVLTree() {}

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    protected TreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);

        if(!successful) {
            return false;
        }
        else {
            balancePath(e); // 如果需要平衡的话，平衡二叉树
        }

        return true;
    }

    // 获取指定节点的平衡因子
    private int getBalanceFactor(AVLTreeNode<E> node) {
        if(node.right == null) {
            return -node.height;
        }
        else if(node.left == null) {
            return node.height;
        }
        else {
            return ((AVLTreeNode<E>) node.right).height - ((AVLTreeNode<E>) node.left).height;
        }
    }

    // 更新 AVLTreeNode 的高度值
    private void updateHeight(AVLTreeNode<E> node) {
        if(node.left == null && node.right == null) { // node is a leaf.
            node.height = 0;
        }
        else if(node.left == null) { // node has not left subtree.
            node.height = 1 + ((AVLTreeNode<E>) node.right).height;
        }
        else if(node.right == null) {
            node.height = 1 + ((AVLTreeNode<E>) node.left).height;
        }
        else {
            node.height = 1 + Math.max(((AVLTreeNode<E>) node.right).height, ((AVLTreeNode<E>) node.left).height);
        }
    }

    // 从指定的元素开始到root结束，通过旋转操作平衡二叉树
    private void balancePath(E e) {
        // this.path(E e) 方法返回的是从root到指定元素的查找路径，所以list其中元素的前一个就是元素的父节点
        ArrayList<TreeNode<E>> path = path(e);

        for(int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) path.get(i);
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == getRoot()) ? null : ((AVLTreeNode<E>) path.get(i - 1));

            switch(getBalanceFactor(A)) {
                case -2:
                    if(getBalanceFactor(((AVLTreeNode<E>) A.left)) <= 0) {
                        balanceLL(A, parentOfA);
                    }
                    else {
                        balanceLR(A, parentOfA);
                    }

                    break;

                case +2:
                    if(getBalanceFactor(((AVLTreeNode<E>) A.right)) >= 0) {
                        balanceRR(A, parentOfA);
                    }
                    else {
                        balanceRL(A, parentOfA);
                    }

                    break;
                default:
                    break;
            }
        }
    }

    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;

        if(A == root) {
            root = B;
        }
        else {
            if(parentOfA.left == A) {
                parentOfA.left = B;
            }
            else {
                parentOfA.right = B;
            }
        }

        A.left = B.right;
        B.right = A;
        updateHeight(((AVLTreeNode<E>) A));
        updateHeight(((AVLTreeNode<E>) B));
    }

    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left; // A是左偏重的
        TreeNode<E> C = B.right; // B是右偏重的

        if(A == root) {
            root = C;
        }
        else {
            if(parentOfA.left == A) {
                parentOfA.left = C;
            }
            else {
                parentOfA.right = C;
            }
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        // 调整节点的高度
        updateHeight(((AVLTreeNode<E>) A));
        updateHeight(((AVLTreeNode<E>) B));
        updateHeight(((AVLTreeNode<E>) C));
    }

    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;

        if(A == root) {
            root = B;
        }
        else {
            if(parentOfA.left == A) {
                parentOfA.left = B;
            }
            else {
                parentOfA.right = B;
            }
        }

        A.right = B.left;
        B.left = A;
        updateHeight(((AVLTreeNode<E>) A));
        updateHeight(((AVLTreeNode<E>) B));
    }

    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right; // A是右偏重的
        TreeNode<E> C = B.left; // B是左偏重的

        if(A == root) {
            root = C;
        }
        else {
            if(parentOfA.left == A) {
                parentOfA.left = C;
            }
            else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        updateHeight(((AVLTreeNode<E>) A));
        updateHeight(((AVLTreeNode<E>) B));
        updateHeight(((AVLTreeNode<E>) C));
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

                // Balance the tree if necessary.
                balancePath(parent.element);
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

            // Balance the tree if necessary.
            balancePath(parentOfRightMost.element);
        }

        size--;
        return true;
    }
}
