import java.util.*;

class Solution {
    static class Music {
        int time;
        String title;
        String lyrics;

        Music(int time, String title, String lyrics) {
            this.time = time;
            this.title = title;
            this.lyrics = lyrics;
        }
    }

    public String solution(String m, String[] musicinfos) {
        Music[] musics = new Music[musicinfos.length];
        // 메세지 변환
        m = melodyConversion(m);

        System.out.println(m);

        // 노래 변환
        for (int i = 0; i < musicinfos.length; i++) {
            String[] temp = musicinfos[i].split(",");
            String startTime = temp[0];
            String endTime = temp[1];
            int playTime = calculatePlayTime(startTime, endTime);
            String title = temp[2];
            String lyrics = temp[3];
            lyrics = lyricsConversion(lyrics, playTime);
            musics[i] = new Music(playTime, title, lyrics);
        }
        return checkMusic(musics, m);
    }

    int calculatePlayTime(String startTime, String endTime) {
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int startMinute = Integer.parseInt(startTime.split(":")[1]);
        int endHour = Integer.parseInt(endTime.split(":")[0]);
        int endMinute = Integer.parseInt(endTime.split(":")[1]);
        return (endHour * 60) - (startHour * 60) + (endMinute - startMinute);
    }

    String melodyConversion(String msg) {
        Deque<String> temp = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        String convertedMsg = "";

        for (String m : msg.split("")) {
            String c = m;
            if (c.equals("#")) {
                temp.push(temp.pop().toLowerCase());
                continue;
            }
            temp.push(c);
        }

        while (!temp.isEmpty()) {
            sb.append(temp.pollLast());
        }
        convertedMsg = sb.toString();
        return convertedMsg;
    }

    String lyricsConversion(String lyrics, int time) {
        StringBuilder sb = new StringBuilder();
        lyrics = melodyConversion(lyrics);
        int length = lyrics.length();
        int quotient = time / length;
        int reminder = time % length;

        for (int i = 0; i < quotient; i++) {
            sb.append(lyrics);
        }
        for (int i = 0; i < reminder; i++) {
            sb.append(lyrics.charAt(i));
        }
        return sb.toString();
    }

    String checkMusic(Music[] musics, String msg) {
        String result = "(None)";
        int maxTime = 0;

        for (Music music : musics) {
            if (music.lyrics.contains(msg)) {
                if (music.time > maxTime) {
                    maxTime = music.time;
                    result = music.title;
                }
            }
        }
        return result;
    }
}