package io.bio.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * UTF8 文件编码下，英文字符一个占一个字节，中文字符一个占三个字节，数字一个占一个字节
 *
 * ClassName: FileCreate
 * Package: io.bio.file
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/3 17:11
 * @Version 1.0
 */
public class FileCreate {
    // 方式1创建文件
    @Test
    public void test01() {
        // new File(String filePath) 一定要存在
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\news1.txt";
        File file = new File(filePath);

        try {
            file.createNewFile();
            System.out.println("文件创建成功..");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方式2创建文件
    @Test
    public void test02() {
        String parentPath = "D:\\";
        // new File(File fileParent, String path) 第一个参数也可以是文件对象
        File file = new File(parentPath,"WorkSpace_BackEnd\\TestFiles\\iofiles\\news2.txt");

        try {
            file.createNewFile();
            System.out.println("文件创建成功..");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 判断文件是否存在，如果存在就删除，在java中文件目录也被当作File文件
    @Test
    public void test03() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\news1.txt";
        File file = new File(filePath);

        if(file.exists()) {
            if(file.delete()) {
                System.out.println(filePath + "文件删除成功。");
            }
            else {
                System.out.println(filePath + "文件删除失败。");
            }
        }
        else {
            System.out.println(filePath + "文件不存在。");
        }
    }

    /**
     * 创建、删除功能
     *      public boolean createNewFile()` ：创建文件。若文件存在，则不创建，返回false。
     *      public boolean mkdir()` ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
     *      public boolean mkdirs()` ：创建文件目录。如果上层文件目录不存在，一并创建。
     *      public boolean delete()` ：删除文件或者文件夹
     *      删除注意事项：① Java中的删除不走回收站。② 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录。
     *
     * 判断功能的方法
     *      public boolean exists()` ：此File表示的文件或目录是否实际存在
     *      public boolean isDirectory()` ：此File表示的是否为目录。
     *      public boolean isFile()` ：此File表示的是否为文件。
     *      public boolean canRead() ：判断是否可读
     *      public boolean canWrite() ：判断是否可写
     *      public boolean isHidden() ：判断是否隐藏
     *
     */
    @Test
    public void test04() {
        String directoryPath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a\\c\\b";
        File file = new File(directoryPath);

        if(file.exists()) {
            System.out.println(directoryPath + "文件目录存在。");
        }
        else {
            if(file.mkdirs()) {
                System.out.println(directoryPath + "文件目录创建成功");
            }
            else {
                System.out.println(directoryPath + "文件目录创建失败");
            }
        }
    }

    // 获取文件的信息
    @Test
    public void test05() {
        File file1 = new File("D:\\io\\io1");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsoluteFile().getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());
    }
}
