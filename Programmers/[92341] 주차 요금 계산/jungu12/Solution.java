import java.util.*;
class Solution {  
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> carInfo = new HashMap<>();
        List<Integer> answerList = new ArrayList<>();
        
        for(int i = 0; i < records.length; i++) {
            String[] recordIn = records[i].split(" ");
            String[] strInTime = recordIn[0].split(":");
            if(recordIn[2].equals("IN")) {
                int inTime = Integer.parseInt(strInTime[0]) * 60 + Integer.parseInt(strInTime[1]);
                //출차 기록이 없다면 24:00(1439) 저장됨
                int outTime = 1439;
                
                for(int j = i + 1; j < records.length; j++) {
                    String[] recordOut = records[j].split(" ");
                    //입차된 차의 출차 정보를 찾음
                    if(recordOut[1].equals(recordIn[1])) {
                        String[] strOutTime = recordOut[0].split(":");
                        outTime = Integer.parseInt(strOutTime[0]) * 60 + Integer.parseInt(strOutTime[1]);
                        break;
                    }
                }
                //여러번 입차한 경우
                if(carInfo.containsKey(recordIn[1])) {
                    carInfo.put(recordIn[1], carInfo.get(recordIn[1]) + (outTime - inTime));
                    continue;
                }
                
                //처음 입차한 경우
                carInfo.put(recordIn[1], outTime - inTime);
                
            }
        }
        
        List<String> carNumSet = new ArrayList<>(carInfo.keySet());
        Collections.sort(carNumSet);
        
        int[] answer = new int[carNumSet.size()];
        int idx = 0;
        //차량별 요금 계산
        for(String carNum : carNumSet) {
            int parkingTime = carInfo.get(carNum);
            System.out.println(carNum + " : " + parkingTime);
            
            if(parkingTime < fees[0]) {
                answer[idx] = fees[1];
                continue;
            }

            answer[idx] = fees[1] + (int)Math.ceil((parkingTime - fees[0]) / (double)fees[2]) * fees[3];
            idx++;
        }
        
        return answer;
    }
}