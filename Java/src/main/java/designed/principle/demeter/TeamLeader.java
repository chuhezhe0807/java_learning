package designed.principle.demeter;

/**
 * ClassName: TeamLeader
 * Package: designed.principle.demeter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/13 22:57
 * @Version 1.0
 */
public class TeamLeader {
    private Programmer programmer;

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public void assignTasks() {
        if(null != programmer) {
            programmer.work();
        }
    }
}
