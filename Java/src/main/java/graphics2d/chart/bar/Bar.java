package graphics2d.chart.bar;

/**
 * ClassName: Bar
 * Package: graphics2d.chart.bar
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/22 22:54
 * @Version 1.0
 */
class Bar{
    private final String name;
    private final int value;

    public Bar(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}