package elasticsearch.entity;

/**
 * ClassName: User
 * Package: elasticsearch.entity
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/11/7 22:21
 * @Version 1.0
 */
public class User {
    private String name;
    private String sex;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
