class Solution {
   public static String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = 0;
        
        // String에 있는 각 노래정보 ','로 구분하기
        for(int i = 0; i < musicinfos.length; i++) {
        	String[] music = musicinfos[i].split(",");
        	String[] start = music[0].split(":");
        	String[] end = music[1].split(":");
        	String title = music[2];
        	String melody = music[3];
        	
        	// 종료시간에서 시작시간 빼서 음악재생시간 구하기
        	int playTime = countPlaytime(start, end);

        	// 문자열이 #을 포함하는지 
        	boolean mContain = m.contains("#");
        	boolean melodyContain = melody.contains("#");
        	
        	// m에는 #문자를 포함하는데 melody에는 포함하지 않으면 이 노래는 아니므로 다음 노래로 넘어가기 
        	if(mContain && !melodyContain) {
        		continue;
        	}
        	
        	// melody가 #문자를 포함하면
        	if(melodyContain) {
        		melody = replaceChar(melody);
        	}
        	
        	// m이 #문자를 포함하면
        	if(mContain) {
        		m = replaceChar(m);
        	}
        	
        	int time = melody.length(); // 노래 길이 
        	StringBuilder sb = new StringBuilder();
        	
        	int repeat = Math.abs(playTime/time); // 노래를 반복재생할 횟수 
        	for(int j = 0; j < repeat; j++) {
        		sb.append(melody);
        	}
        	
        	// 재생시간을 노래길이로 나눈 나머지가 0이 아니면 
        	int rest = Math.abs(playTime%time);
        	if(rest != 0) {
        		for(int c = 0; c < rest; c++) { // 나머지만큼 멜로디 추가 
        			sb.append(melody.charAt(c));
        		}
        	}
        	
        	String totalMelody = sb.toString();
        	if(totalMelody.contains(m)) { // m이 최종적인 노래에 포함된다면 제목 반환
        		// 1. 재생된 시간이 젤 긴 음악
        		// 2. 먼저 입력된 음악 
        		if(playTime > maxTime) {
        			maxTime = playTime;
        			answer = title;
        		}
        	}
        }
        
        return answer;
    }
	
	// #들어간 문자 소문자로 바꾸기 
	static String replaceChar(String melody) {

		melody = melody.replace("C#", "c");
		melody = melody.replace("D#", "d");
		melody = melody.replace("F#", "f");
		melody = melody.replace("G#", "g");
		melody = melody.replace("A#", "a");
		
		return melody;
	}
	
	// 재생시간 계산하기 
	static int countPlaytime(String[] start, String[] end) {
		
		int hour = Integer.parseInt(end[0]) - Integer.parseInt(start[0]);
		int minute = Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
		
		return (hour * 60) + minute;
	}
}