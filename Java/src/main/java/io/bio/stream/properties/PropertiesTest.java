package io.bio.stream.properties;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * ClassName: PropertiesTest
 * Package: io.bio.stream.properties
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/9 12:09
 * @Version 1.0
 */
public class PropertiesTest {
    // 使用普通方法读取 .properties 文件
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\info.properties";
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while((line = br.readLine()) != null) {
                String[] split = line.split("=");
                System.out.println(split[0] + ": " + split[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 使用 Properties 类来读取 .properties 文件
    @Test
    public void test02() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\info.properties";
        Properties properties = new Properties();

        try(FileReader fr = new FileReader(filePath)) {
            // 加载指定配置文件
            properties.load(fr);
            // 把k-v显示到控制台
            properties.list(System.out);

            System.out.println(properties.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 写入 .properties 文件
    @Test
    public void test03() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\info1.properties";
        Properties properties = new Properties();

        try(FileWriter fw = new FileWriter(filePath)) {
            properties.setProperty("name", "小张"); // 注意保存中文时，是保存的中文的unicode码
            properties.setProperty("age", "25");
            properties.setProperty("charset", "utf-16"); // 如果.properties中有这个key，则覆盖掉之前写的值

            properties.store(fw, "这是一行注释。。 把charset的值改为了 utf-6"); // 中文也会转换为unicode保存
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
