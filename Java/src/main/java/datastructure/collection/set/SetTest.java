package datastructure.collection.set;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ClassName: SetTest
 * Package: collection.set
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/25 21:01
 * @Version 1.0
 */
public class SetTest {
    @Test
    public void test01() {
        HashSet<Integer> hashSet = new HashSet<>();

        hashSet.add(11);
        hashSet.add(22);
        hashSet.add(33);

        for (Integer i:hashSet) {
            if(i > 15) {
                continue;
            }

            System.out.println(i);
        }

        Iterator<Integer> iterator = hashSet.iterator();
        while(iterator.hasNext()) {
            Integer integer = iterator.next();

            if(integer > 15) {
                continue;
            }

            System.out.println(integer);
        }
    }

    @Test
    public void test02() {
        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> o2 - o1);

        treeSet.add(4);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(1);
//        treeSet.add(1);

        // 使用增强for循环遍历
        for(Integer integer:treeSet) {
            System.out.println(integer);
        }

        // 使用迭代器方法遍历
        Iterator<Integer> iterator = treeSet.iterator();
        while(iterator.hasNext()) {
            Integer next = iterator.next();

            iterator.remove();
            System.out.println(next);
        }

        // 使用forEach遍历(推荐使用)
        treeSet.forEach(System.out::println);
    }

    // TreeSet
    @Test
    public void test03() {
        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("London");
        hashSet.add("Paris");
        hashSet.add("New York");
        hashSet.add("San Francisco");
        hashSet.add("New York");

        TreeSet<String> treeSet = new TreeSet<>(hashSet);
        System.out.println("Sorted tree set: " + treeSet);

        // Methods in Sorted interface.
        System.out.println(treeSet.first()); // 返回集合中第一个元素
        System.out.println(treeSet.last()); // 返回集合中最后一个元素
        // headSet(toElement, inclusive) inclusive 默认为false
        // tailSet(toElement, inclusive) inclusive 默认为true
        System.out.println(treeSet.headSet("New York", false)); // 返回规则集中小于 "New York" 的一部分
        System.out.println(treeSet.tailSet("New York", true)); // 返回规则集中大于等于 "New York" 的那一部分

        System.out.println("");
        // Methods in NavigableSet interface.
        System.out.println(treeSet.lower("P")); // 返回小于给定元素的最大元素 "New York"
        System.out.println(treeSet.higher("P")); // 返回大于给定元素的最小元素 "Paris"
        System.out.println(treeSet.floor("P")); // 返回小于或等于给定元素的最大元素元素 "New York"
        System.out.println(treeSet.ceiling("P")); // 返回大于或等于给定元素的最小元素 "Paris"
        System.out.println(treeSet.pollFirst()); // 删除并返回树形集第一个元素d
        System.out.println(treeSet.pollLast()); // 删除并返回树形集最后一个元素
    }

    // TreeSet 自定义排序
    @Test
    public void test04() {
        // Comparator.reverseOrder Comparator.naturalOrder
        TreeSet<Integer> treeSet = new TreeSet<>(Comparator.reverseOrder());
        treeSet.add(-1);
        treeSet.add(-2);
        treeSet.add(7);
        treeSet.add(4);
        treeSet.add(5);

        System.out.println(treeSet); // [7, 5, 4, -1, -2]
    }
}
