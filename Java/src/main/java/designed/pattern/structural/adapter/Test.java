package designed.pattern.structural.adapter;

/**
 * ClassName: Test
 * Package: designed.pattern.structural.adapter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 22:24
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "晴天");
        audioPlayer.play("vlc", "晴天.vlc");
        audioPlayer.play("mp4", "晴天.mp4");
    }
}
