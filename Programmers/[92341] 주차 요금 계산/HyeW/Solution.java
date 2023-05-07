import java.util.*;

class Solution {
    int[] fees;
    public int[] solution(int[] fees, String[] records) {
        // 입차한 차 정보 (차번호, 입차 시간(분))
        Map<String,Integer> ins = new HashMap<>();
        // 주차 시간 정보 (차번호, 주차 시간(분))
        Map<String, Integer> totalTime = new HashMap<>();
        
        for(String record : records){
            String[] info = record.split(" |:"); // 시, 분, 차번호, 입출차 정보
            int minute = hourToMinute(info[0],info[1]);
            //입차한 차일 경우
            if(info[3].equals("IN")){
                ins.put(info[2],minute);
                continue;
            }
            //출차한 차일 경우
            //차번호 별 누적 주차시간 구하기 (출차 시간 - 입차 시간)
            totalTime.put(info[2],totalTime.getOrDefault(info[2], 0) + (minute - ins.get(info[2])));
            ins.remove(info[2]);
        }
        
        //남아있는 차 시간 계산하기
        int maxTime = hourToMinute("23","59");
        for(String key : ins.keySet()){
            totalTime.put(key,totalTime.getOrDefault(key, 0)+maxTime - ins.get(key));
        }
        
        // 차 번호 순으로 정렬
        List<String> numbers = new ArrayList<>();
        for(String key : totalTime.keySet()){
            numbers.add(key);
        }
        Collections.sort(numbers);
        
        // 리스트 배열로 변환
        int[] answer = new int[totalTime.size()];
        int index = 0;
        for(String number : numbers){
            answer[index++] = calFee(totalTime.get(number), fees);   
        }
        return answer;
    }
    // 시간을 분으로 변환하기
    private int hourToMinute(String hour, String minute){
        return Integer.parseInt(hour)*60 + Integer.parseInt(minute);
    }
    // 분을 요금으로 계산하기
    private int calFee(int time, int[] fees){
        int surcharge = 0;
        int overTime = time - fees[0];
        if(overTime > 0){
            surcharge = (int)Math.ceil((float)overTime/fees[2]) * fees[3];
        }
        return fees[1] + surcharge;
    }
    
}