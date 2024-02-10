package generic;

import java.util.ArrayList;

/**
 * ClassName: NumberClass
 * Package: generic
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/9 16:12
 * @Version 1.0
 */
public class GenericStack<E> {
    private final ArrayList<E> list = new ArrayList<>();

    public E peek() {
        return list.get(list.size() - 1);
    }

    public void push(E o) {
        list.add(o);
    }

    public E pop() {
        E o = list.get(list.size() - 1);
        list.remove(o);

        return o;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "GenericStack{" +
                "list=" + list +
                '}';
    }
}
