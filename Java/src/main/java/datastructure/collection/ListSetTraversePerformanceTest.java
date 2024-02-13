package datastructure.collection;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * ClassName: ListSetTraversePerformaceTest
 * Package: datastructure.collection
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/12 13:14
 * @Version 1.0
 */

/**
 * 实现了 RandomAccess 接口的线性表，遍历时使用for + list.get(i) 效率高于iterator + 方式遍历 （for-each 底层使用了iterator实现）
 */
public class ListSetTraversePerformanceTest {

    public static final int size = 100000;

    // 测试 ArrayList 的 forEach、for+list.get(i)、for-each 遍历的性能
    // 虽然 for+list.get(i) 性能稍优于forEach，但是基于可读性考虑，还是推荐使用forEach
    @Test
    public void test01() {
        List<Integer> integerList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            integerList.add(i);
        }

        // 迭代器访问（为了避免偶然性，取10000次的平均值）
        long count = 0;
        for (int j = 0; j < 10000; j++) {
            long startTime = System.nanoTime();
            integerList.forEach(integer -> {
            });
            count += System.nanoTime() - startTime;
        }
        System.out.println(count / 10000); // 39338


        // get访问（为了避免偶然性，取10000次的平均值）
        count = 0;
        for (int j = 0; j < 10000; j++) {
            long startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                integerList.get(i);
            }
            count += System.nanoTime() - startTime;
        }
        System.out.println(count / 10000); // 38687


        // for-each访问（为了避免偶然性，取1000次的平均值）
        count = 0;
        for (int j = 0; j < 10000; j++) {
            long startTime = System.nanoTime();
            for (Integer integer : integerList) {
            }
            count += System.nanoTime() - startTime;
        }
        System.out.println(count / 10000); // 145477
    }

    // 测试 HashSet 的 forEach、for-each、iterator 遍历的性能
    // 推荐使用forEach遍历 HashSet
    @Test
    public void test02() {
        Set<Integer> integerSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            integerSet.add(i);
        }

        // 迭代器访问（为了避免偶然性，取10000次的平均值）
        long count = 0;
        for (int j = 0; j < 10000; j++) {
            long startTime = System.nanoTime();
            integerSet.forEach(integer -> {
            });
            count += System.nanoTime() - startTime;
        }
        System.out.println(count / 10000); // 545819


        // for-each访问（为了避免偶然性，取1000次的平均值）
        count = 0;
        for (int j = 0; j < 10000; j++) {
            long startTime = System.nanoTime();
            for (Integer integer : integerSet) {
            }
            count += System.nanoTime() - startTime;
        }
        System.out.println(count / 10000); // 592438

        count = 0;
        for (int j = 0; j < 10000; j++) {
            long startTime = System.nanoTime();
            Iterator<Integer> iterator = integerSet.iterator();
            while(iterator.hasNext()) {
                iterator.next();
            }
            count += System.nanoTime() - startTime;
        }
        System.out.println(count / 10000); // 782004
    }

}
