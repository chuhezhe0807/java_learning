package optional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: OptionalTest
 * Package: optional
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 23:47
 * @Version 1.0
 */
public class OptionalTest {
    @Test
    public void test01() {
        Optional<String> optionalStr = Optional.of("Hello optional");

        System.out.println(optionalStr); // Optional[Hello optional]
    }

    @Test
    public void test02() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("xiaozhang", 11));
        list.add(new Student("xiaoli", 0));
        list.add(new Student("Tom", 10));
        list.add(new Student("Jerry", 19));
        list.add(new Student("Pi", 5));

        list.stream()
                .filter(s -> s.getAge() > 10)
                .findAny()
                .ifPresent(System.out::println);


        String str = null;

        if(Math.random() < 0.999) {
            str = "1123";
        }

        System.out.println(Optional.ofNullable(str).orElse("AAA"));

        System.out.println(Optional.ofNullable(str)
                .orElseGet(() -> {
                    // expensive compute....
                    for (int i = 0; i < 10000; i++) {

                    }

                    return "0";
                }));
    }

    @Test
    public void test03() {
        Student student = new Student("xiaozhang", 11);

        Optional.ofNullable(student)
                .map(Student::getAge)
                .ifPresent(age -> System.out.println(age + "岁"));
    }

    @Test
    public void test04() {
        String a = "hello2";
        final String b = "hello";
        String c = "hello";

        String d = b + 2;
        String e = c + 2;

        System.out.println(a == d); // true
        System.out.println(a == e); // false
    }

    @Test
    public void test05() {
        String a = new String("222");

        changeVal(a);

        System.out.println(a); // java 函数参数是按照值传递的，所以此处输出为 "222"
    }

    void changeVal(String a) {
        a = new String("11");
    }
}
