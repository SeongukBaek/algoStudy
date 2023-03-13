import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    private static Map<String, String> musicInfos;

    public String solution(String m, String[] musicinfos) {
        musicInfos = new LinkedHashMap<>();

        // 우선 주어진 곡 정보들을 재생된 기간동안의 멜로디로 잘라 순서대로 저장
        for (String musicInfo : musicinfos) {
            String[] infos = musicInfo.split(",");

            int start = parseTime(infos[0]);
            int end = parseTime(infos[1]);

            String song = infos[2];
            String melody = infos[3];

            int time = end - start;
            // 끝나는 시간이 00:00이면, 재생 시간이 음수
            if (time < 0) {
                time *= -1;
            }

            saveCutOffMelody(song, melody, time);
        }

        String thatSong = "";
        int maxTime = 0;
        m = convertSharp(m);

        for (Map.Entry<String, String> music : musicInfos.entrySet()) {
            String song = music.getKey();
            String melody = music.getValue();

            // 기억하는 멜로디를 포함하지 않으면 패스
            if (!melody.contains(m)) {
                continue;
            }

            if (maxTime < melody.length()) {
                thatSong = song;
                maxTime = melody.length();
            }
        }

        return maxTime == 0 ? "(None)" : thatSong;
    }

    /**
     * 시간 파싱해서 분 단위로 반환
     */
    private static int parseTime(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    /**
     * 주어진 곡 정보를 가지고, 재생 기간동안 재생한 멜로디를 추출해 저장
     */
    private static void saveCutOffMelody(String song, String melody, int duration) {
        StringBuilder cutOffMelody = new StringBuilder();
        int melodyIndex = 0;

        for (int index = 0; index < duration; index++) {
            if (melodyIndex == melody.length()) {
                melodyIndex = 0;
            }

            char current = melody.charAt(melodyIndex);
            // 만약 다음 문자가 #이라면, 소문자로 치환해서 저장
            if (melodyIndex + 1 < melody.length() && melody.charAt(melodyIndex + 1) == '#') {
                current = Character.toLowerCase(current);
                melodyIndex++;
            }
            cutOffMelody.append(current);
            melodyIndex++;
        }

        musicInfos.put(song, cutOffMelody.toString());
    }

    /**
     * 기억하는 멜로디에서 #을 치환하여 반환
     */
    private static String convertSharp(String melody) {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < melody.length(); index++) {
            char current = melody.charAt(index);
            if (index + 1 < melody.length() && melody.charAt(index + 1) == '#') {
                current = Character.toLowerCase(current);
                index++;
            }
            result.append(current);
        }
        return result.toString();
    }
}