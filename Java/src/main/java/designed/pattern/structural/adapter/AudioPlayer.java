package designed.pattern.structural.adapter;

/**
 * ClassName: AudioPlayer
 * Package: designed.pattern.structural.adapter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 22:19
 * @Version 1.0
 */
public class AudioPlayer implements MediaPlayer{
    @Override
    public void play(String audioType, String fileName) {
        if("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("播放 MP3 文件 ：" + fileName);
        }
        else if("vlc".equalsIgnoreCase(audioType) || "mp4".equalsIgnoreCase(audioType)) {
            MediaPlayer mediaPlayer = new MediaPlayerAdapter(audioType);
            mediaPlayer.play(audioType, fileName);
        }
    }
}
