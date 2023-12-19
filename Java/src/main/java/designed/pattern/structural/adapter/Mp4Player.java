package designed.pattern.structural.adapter;

/**
 * ClassName: Mp4Player
 * Package: designed.pattern.structural.adapter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 21:59
 * @Version 1.0
 */
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        // do nothing.
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("播放 Mp4 文件 ：" + fileName);
    }
}
