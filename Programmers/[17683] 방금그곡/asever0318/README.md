# [17683] 방금그곡 

## :pushpin: **Algorithm**

문자열

## :round_pushpin: **Logic**

```java
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
```
- 기억하고 있는 문자열 m에는 '#'이 포함되는데 노래 멜로디에는 '#'이 포함되지 않는다면 그 노래는 정답이 아니기 때문에 다음 노래로 넘겨준다.
- 노래나 멜로디에 '#'이 들어갈 경우에는 해당하는 문자를 소문자로 바꿔줬다.
- 노래 재생시간(playTime)에 맞춰 노래를 이어주고(totalMelody) 그 멜로디 안에 m이 포함되는지 확인한다. 
- 정답이 여러 개일 경우 재생시간이 제일 긴 음악, 먼저 입력된 음악을 반환하도록 한다.


## :black_nib: **Review**
- 은근 빼먹는 조건이 많아서 여러 번 수정했다. 문제 읽으면서 조건을 잘정리하는 습관을 들여야겠다.
