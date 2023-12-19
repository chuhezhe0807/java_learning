package designed.pattern.structural.adapter;

/**
 * ClassName: MediaPlayerAdapter
 * Package: designed.pattern.structural.adapter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/18 22:10
 * @Version 1.0
 */
public class MediaPlayerAdapter implements MediaPlayer{
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaPlayerAdapter(String audioType) {
        if("vlc".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer = new VlcPlayer();
        }
        else if("mp4".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        // 判断当前的文件格式
        if("vlc".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer.playVlc(fileName);
        }
        else if("mp4".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
