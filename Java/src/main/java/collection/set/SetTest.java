package collection.set;

import org.junit.jupiter.api.Test;

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

        for(Integer integer:treeSet) {
            System.out.println(integer);
        }
    }
}
