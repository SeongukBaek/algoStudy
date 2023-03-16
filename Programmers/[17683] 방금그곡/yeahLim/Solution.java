import java.util.*;

class Solution {
	
	static Map<Character, Integer> sharpKey = new HashMap<Character, Integer>(){
		{
			put('C', 1);
			put('D', 2);
			put('E', 3);
			put('F', 4);
			put('G', 5);
			put('A', 6);
			put('B', 7);
		}
	}; 

	public static String solution(String m, String[] musicInfos) {
		int answer = -1;
		String target = convertToIntKey(m);
		String[][] music = new String[musicInfos.length][2]; // 0:제목, 1:악보
		int[] time = new int[musicInfos.length];
		StringTokenizer st;
		for (int i = 0; i < musicInfos.length; i++) {
			st = new StringTokenizer(musicInfos[i], ",");
			time[i] = getPlayTime(st.nextToken().split(":"), st.nextToken().split(":"));
			music[i][0] = st.nextToken();
			music[i][1] = makeMusicScore(time[i], convertToIntKey(st.nextToken()));

			if (searchMusic(target, music[i][1])) {
				if (answer == -1 || time[answer] < time[i]) answer = i;	
			}
			
		}
		
		return (answer == -1) ? "(None)" : music[answer][0];
	}

	/* #키 숫자로 변환 */
	static String convertToIntKey(String score) {
		StringBuilder sb = new StringBuilder();
		int last = score.length()-1;
		for(int i=0; i<last; i++) {
			if(score.charAt(i+1) == '#') {
				sb.append(sharpKey.get(score.charAt(i)));
				i++;
			}
			else sb.append(score.charAt(i));
		}
		if(score.charAt(last) != '#') sb.append(score.charAt(last));
		return sb.toString();
	}
	
	/* 재생시간 구하기 */
	static int getPlayTime(String[] start, String[] end) {
		int startHour = Integer.parseInt(start[0]);
		int startMin = Integer.parseInt(start[1]);
		int endHour = Integer.parseInt(end[0]);
		int endMin = Integer.parseInt(end[1]);
		return (endHour - startHour - 1) * 60 + (60 - startMin + endMin);
	}

	/* 악보 만들기 */
	static String makeMusicScore(int length, String score) {
		int scoreLen = score.length();
		if (scoreLen > length)
			return score.substring(0, length);
		if (scoreLen < length)
			return score.repeat(length / scoreLen) + score.substring(0, length % scoreLen);
		return score;
	}

	/* 노래 찾기 */
	static boolean searchMusic(String target, String score) {
		return score.contains(target);
	}
	

}