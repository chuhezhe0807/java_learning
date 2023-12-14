package designed.principle.demeter;

/**
 * ClassName: Test
 * Package: designed.principle.demeter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/13 23:01
 * @Version 1.0
 */
public class Test {
    /**
     * 最少知道原则
     */
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        JavaProgrammer javaProgrammer = new JavaProgrammer();
        PythonProgrammer pythonProgrammer = new PythonProgrammer();

        teamLeader.setProgrammer(javaProgrammer);
        boss.meet(teamLeader);
    }
}
