package datastructure.collection;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 比较 Set List 各具体实现类的性能
 *      Set: HashSet LinkedHashSet TreeSet
 *      List: ArrayList LinkedList
 *
 * ClassName: SetListPerformanceTest
 * Package: datastructure.collection
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/11 18:19
 * @Version 1.0
 */
public class SetListPerformanceTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        Collection<Integer> hashSet = new HashSet<>(list);
        System.out.println("hashSet contains time " + getTestTime(hashSet));
        System.out.println("hashSet remove time " + getRemoveTime(hashSet));

        Collection<Integer> linkedHashSet = new LinkedHashSet<>(list);
        System.out.println("linkedHashSet contains time " + getTestTime(linkedHashSet));
        System.out.println("linkedHashSet remove time " + getRemoveTime(linkedHashSet));

        Collection<Integer> treeSet = new TreeSet<>(list);
        System.out.println("treeSet contains time " + getTestTime(treeSet));
        System.out.println("treeSet remove time " + getRemoveTime(treeSet));

        Collection<Integer> arrayList = new ArrayList<>(list);
        System.out.println("arrayList contains time for" + getTestTime(arrayList));
        System.out.println("arrayList remove time " + getRemoveTime(arrayList));

        Collection<Integer> linkedList = new LinkedList<>(list);
        System.out.println("linkedList contains time " + getTestTime(linkedList));
        System.out.println("linkedList remove time " + getRemoveTime(linkedList));
    }

    private static final int N = 50000;

    public static long getTestTime(Collection<Integer> c) {
        long start = System.currentTimeMillis();

        for(int i = 0; i < N; i++) {
            c.contains((int) (Math.random() * 2 * N));
        }

        return System.currentTimeMillis() - start;
    }

    public static long getRemoveTime(Collection<Integer> c) {
        long start = System.currentTimeMillis();

        for(int i = 0; i < N; i++) {
            c.remove(i);
        }

        return System.currentTimeMillis() - start;
    }

    public static long getRunningTime(Consumer<?> consumer) {
        long start = System.currentTimeMillis();

        consumer.accept(null);

        return System.currentTimeMillis() - start;
    }
}
