package datastructure.collection.list;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * ClassName: ListTest
 * Package: collection.list
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/25 21:49
 * @Version 1.0
 */
public class ListTest {
    @Test
    public void test01() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(null);

        Integer[] subListArr = arrayList.subList(2, arrayList.size()).toArray(new Integer[0]);

        System.out.println(Arrays.toString(subListArr)); // [3, null] subList(fromIndex, toIndex) 左闭右开，toIndex 不能大于list.size()

        // 删除指定元素后，再打印 subList 会抛出 ConcurrentModificationException 异常
        // 如果确实需要在 subList 不转为Array时候，删除、修改 arrayList 的某一部分数据，则建议使用 CopyOnWriteArrayList
        // CopyOnWriteArrayList 对 ArrayList 其中所有可变操作(add, set, remove 等等)都是通过创建基础数组的新副本来实现的。
        // CopyOnWriteArrayList 通常太耗费性能，但当遍历操作的数量远远超过可变操作时，
        // 这可能比替代方法更有效，并且在您不能或不想同步遍历但需要排除并发线程之间的干扰时非常有用。
        // “快照”样式迭代器方法使用对创建迭代器时数组状态的引用。该数组在迭代器的生命周期内永远不会更改，
        // 因此不可能发生干扰，并且保证迭代器不会抛出 ConcurrentModificationException 。
        System.out.println(arrayList.removeIf(i -> i != null && i == 3)); // 返回元素是否移除成功

        System.out.println(Arrays.toString(subListArr));

        // equals 一般用于重写，比较时一般不适用 == 运算符比较地址
        System.out.println(arrayList.contains(null)); // true contains使用indexOf(object) > 0 来判断是否包含该对象，使用 == 运算符
    }

    @Test
    public void test02() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(5);

        // 返回list中符合条件的第一个数据
        System.out.println(arrayList.stream().filter(e -> e > 3).findFirst());
        // 不关心返回的是哪一个数据，只要符合条件的就行，那就用findAny 而且在并行流上，findAny限制更少
        System.out.println(arrayList.stream().filter(e -> e > 3).findAny());

        System.out.println(arrayList.stream().anyMatch(e -> e.equals(-1))); // 是否有一条数据满足条件
        System.out.println(arrayList.stream().allMatch(e -> e > 100)); // 是否都满足条件
        System.out.println(arrayList.stream().noneMatch(e -> e > 100)); // 是否都不满足条件

        // 使用迭代器方法遍历（逆序）
        ListIterator<Integer> iterator = arrayList.listIterator(arrayList.size());

        while(iterator.hasPrevious()) {
            Integer previous = iterator.previous();
            System.out.println(previous);
        }
    }

    @Test
    public void test03() {
        class A {
            private final int id;
            private final int age;
            private final String name;

            public A(int id, String name, int age) {
                this.id = id;
                this.name = name;
                this.age = age;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            @Override
            public String toString() {
                return "A{" +
                        "id=" + id +
                        ", age=" + age +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        List<A> arrayList = new ArrayList<>();

        arrayList.add(new A(1, "1", 24));
        arrayList.add(new A(2, "2", 1));
        arrayList.add(new A(1, "1", 23));
        arrayList.add(new A(3, "3", 56));

//        arrayList.removeIf(e -> e.getId() > 2); // 删除指定的元素

        // (o1, o2) -> o2 如果两个键重复则使用后面的键对应的值覆盖
//        System.out.println(arrayList.stream().collect(Collectors.toMap(A::getId, e -> e, (o1, o2) -> o2)));

//        arrayList.sort(Comparator.comparingInt(A::getId)); // 按照对象中指定的元素进行排序

        // 先按照id顺序排序，在id相同时按照age倒序排
        arrayList.sort(Comparator.comparing(A::getId)
                .thenComparing((o1, o2) -> o2.getAge() - o1.getAge()));

        System.out.println(Arrays.toString(arrayList.toArray()));
    }

    @Test
    public void test04() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(0, -1); // 会覆盖之前的元素（如果有的话）
        arrayList.add(1, -2);

        System.out.println(Arrays.toString(arrayList.toArray()));
    }

    // LinkedList
    @Test
    public void test05() {
        LinkedList<Integer> list = new LinkedList<>();
        List<Integer> list1 = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(0);

        list1.add(-1);
        list1.add(-2);
        list1.add(-3);
        list1.add(-4);

        // 遍历链表时，应使用增强for循环或forEach，不要使用 for循环 + list.get(i) 的方法遍历（效率很低）
//        list.forEach(System.out::println);

        // 实现了 RandomAccess 接口的线性表，遍历时使用for + list.get(i) 效率高于iterator + 方式遍历

//        list.sort(Integer::compareTo); // 升序排列
//        list.sort((o1, o2) -> o2.compareTo(o1)); // 降序排列
//        list.sort(Comparator.reverseOrder()); // 降序排列

        list.offer(1);

//        Collections.shuffle(list); // 打乱线性表。
        Collections.shuffle(list, new Random(20)); // 使用指定的Random可以产生相同元素序列的线性表

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Collections.disjoint(list1, list)); // 两个集合如果没有相同的元素则返回true
        System.out.println(Collections.frequency(list, 1f)); // 集合中元素出现的频率
    }
}
