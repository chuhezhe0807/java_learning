package datastructure.map;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * ClassName: TreeMapTest
 * Package: datastructure.map
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/13 16:35
 * @Version 1.0
 */
public class TreeMapTest {
    // 通知字符串中各单词出现的次数
    @Test
    public void test01() {
        String text = "Good morning. Hava a good class. " + "Hava a good visit, Have fun!";
        Map<String, Integer> map = new TreeMap<>();
        // 文本使用 \s 空格或者 \p{P} 标点作为分隔
        String[] words = text.split("[\\s+\\p{P}]");

        for (String word : words) {
            String key = word.toLowerCase();

            if (key.length() > 0) {
                if (!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    map.put(key, map.get(key) + 1);
                }
            }
        }

        // TreeMap 默认顺序是 key(String)字母的升序排序的
        map.forEach((k, v) -> System.out.println(k + "\t\t" + v));

        System.out.println();

        // 按照单词出现的顺序升序排序
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue()); // Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        for(Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }
}
