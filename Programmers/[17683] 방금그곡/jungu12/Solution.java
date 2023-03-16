class Solution {
	public String solution(String m, String[] musicinfos) {
		int maxPlayTime = 0;
		String answer = "";

		// 멜로디에 들어있는 # 붙은 음 치환
		m = convertMelody(m);

		for (String musicInfo : musicinfos) {
			String[] info = musicInfo.split(",");
			String[] start = info[0].split(":");
			String[] end = info[1].split(":");
			String title = info[2];
			String convertedMelody = convertMelody(info[3]);

			int startTime = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
			int endTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
			int playTime = endTime - startTime;
			
			//재생시간이 악보보다 긴 경우
			if (playTime > convertedMelody.length()) {
				StringBuilder newMelody = new StringBuilder();
				for (int i = 0; i < playTime / convertedMelody.length(); i++) {
					newMelody.append(convertedMelody);
				}
				newMelody.append(convertedMelody.substring(0, playTime % convertedMelody.length()));
				convertedMelody = newMelody.toString();
			}
			
			//재생시간이 악보보다 짧은 경우
			if (playTime < convertedMelody.length()) {
				convertedMelody = convertedMelody.substring(0, playTime);
			}

			//기억하고 있는 멜로디를 포함하면서 재생시간이 가장 긴 음악을 찾음
			if (convertedMelody.contains(m) && playTime > maxPlayTime) {
				answer = title;
				maxPlayTime = playTime;
			}
		}
		
		return maxPlayTime != 0 ? answer : "(None)";
	}

	String convertMelody(String melody) {
		melody = melody.replaceAll("A#", "a");
		melody = melody.replaceAll("C#", "c");
		melody = melody.replaceAll("D#", "d");
		melody = melody.replaceAll("F#", "f");
		melody = melody.replaceAll("G#", "g");
		return melody;
	}
}