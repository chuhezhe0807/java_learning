package designed.principle.demeter;

/**
 * ClassName: Boss
 * Package: designed.principle.demeter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/13 22:57
 * @Version 1.0
 */
public class Boss {
    public void meet(TeamLeader teamLeader) {
        System.out.println("老板开会，分配工作");
        teamLeader.assignTasks();
    }
}
