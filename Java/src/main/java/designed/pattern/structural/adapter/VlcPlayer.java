package designed.pattern.structural.adapter;

/**
 * ClassName: VlcPlayer
 * Package: designed.pattern.structural.adapter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 21:58
 * @Version 1.0
 */
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("播放 vlc 文件 ：" + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // do nothing.
    }
}
