import java.util.*;
import java.time.*;

class Solution {
  List<Music> infos = new ArrayList<>();
  Music correctMusic;
  
  public String solution(String m, String[] musicinfos) {
      StringTokenizer st;
      m = changeMelody(m);
      
      //정보 파싱
      for(int index = 0; index < musicinfos.length; index++){
          st = new StringTokenizer(musicinfos[index],",");
          infos.add(new Music(getMinutes(st), st.nextToken(), changeMelody(st.nextToken())));
      }

      for(Music music : infos) {
        if(correctMusic != null && correctMusic.playTime >= music.playTime) {
          continue;
        }
        
        if(checkSameMelody(m, music)) {
          correctMusic = music;
        }
      }
      
      return correctMusic != null ? correctMusic.name : "(None)";
  }
  
  int getMinutes(StringTokenizer time) {
      //시작 시간구하기
      StringTokenizer timeTokenizer = new StringTokenizer(time.nextToken(),":");
      LocalTime startTime = LocalTime.of(Integer.parseInt(timeTokenizer.nextToken()),Integer.parseInt(timeTokenizer.nextToken()));
      //끝난 시간 구하기
      timeTokenizer = new StringTokenizer(time.nextToken(),":");
      LocalTime endTime = LocalTime.of(Integer.parseInt(timeTokenizer.nextToken()),Integer.parseInt(timeTokenizer.nextToken()));
      //실행시간 구하기
      Duration duration = Duration.between(startTime, endTime);
      
      return (int)(duration.getSeconds()/60);
  }
  
  boolean checkSameMelody(String m, Music music){

      int mIndex = 0;
      int sheetIndex = 0;
      int preIndex = 0;
      int time = 0;
      int sheetLen = music.sheet.length();
      //현재 문자열이 같은지
      boolean isSame = false;
    while(music.playTime > time){
      //현재 시간의 음 뽑기
      char curTone = music.sheet.charAt(sheetIndex);
    
      //같다면 
      if(m.charAt(mIndex) == curTone) {
        if(!isSame) {
          preIndex = sheetIndex;
          isSame = true;
        }
        mIndex++;
        sheetIndex = (sheetIndex+1)%sheetLen;
      }else { // 다르면
        if(isSame) {
          sheetIndex = (preIndex+1)%sheetLen;
          time = time - mIndex;
          isSame = false;
        }else {
          sheetIndex = (sheetIndex+1)%sheetLen;
        }
        mIndex = 0;
      }
      
      //주어진 멜로디와 일치하면
      if(mIndex == m.length()) {
        return true;
      }
        time++;
    }
      
      return false;
  }
  
  /* #붙은 음을 소문자로 바꿔준다. */
  String changeMelody(String origin) {
    List<Character> tones = new LinkedList<>();
    boolean isSemiTone = false;
    
    for(int i = origin.length()-1; i >= 0; i-- ) {
      if(origin.charAt(i) == '#') {
        isSemiTone = true;
        continue;
      }
      if(isSemiTone) {
        tones.add(0, Character.toLowerCase(origin.charAt(i)));
      }else {
        tones.add(0,origin.charAt(i));
      }
      isSemiTone = false;
    }
    
    StringBuilder sb = new StringBuilder();
    for(char tone : tones) {
      sb.append(tone);
    }
    
    return sb.toString();
  }
  
  class Music{
      int playTime;
      String name;
      String sheet;
      
      Music(int playTime, String name, String sheet){
          this.playTime = playTime;
          this.name = name;
          this.sheet = sheet;
      }
  }
}