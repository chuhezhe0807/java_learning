package collection.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        System.out.println(arrayList.subList(2, arrayList.size())); // [3, null] subList(fromIndex, toIndex) 左闭右开，toIndex 不能大于list.size()

        System.out.println(arrayList.remove(2)); // 返回移除的元素的值

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
    }

    @Test
    public void test03() {
        class A {
            private int id;
            private String name;

            public A(int id, String name) {
                this.id = id;
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        List<A> arrayList = new ArrayList<>();

        arrayList.add(new A(1, "1"));
        arrayList.add(new A(2, "2"));
        arrayList.add(new A(1, "1"));
        arrayList.add(new A(3, "3"));

        arrayList.removeIf(e -> e.getId() > 2); // 是现在迭代过程中删除元素

        System.out.println(arrayList.stream().collect(Collectors.toMap(A::getId, e -> e, (o1, o2) -> o2)));
    }
}
