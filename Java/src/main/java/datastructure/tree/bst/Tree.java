package datastructure.tree.bst;

import java.util.Collection;

/**
 * ClassName: Tree
 * Package: datastructure.tree
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/22 21:42
 * @Version 1.0
 */
public interface Tree<E> extends Collection<E> {
    boolean search(E e);

    boolean insert(E e);

    boolean delete(E e);

    int getSize();

    // 中序遍历
    default void inorder() {}

    // 前序遍历
    default void postorder() {}

    // 后序遍历
    default void preorder() {}

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    default boolean add(E e) {
        return insert(e);
    }

    @Override
    @SuppressWarnings("unchecked")
    default boolean remove(Object o) {
        return delete((E) o);
    }

    @Override
    default int size() {
        return getSize();
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    default Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    default <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
}
