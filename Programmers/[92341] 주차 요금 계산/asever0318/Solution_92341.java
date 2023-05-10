package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_92341 {
	static class Car{
		String inTime, state;
		int cost, time;

		public Car(String inTime, int cost, String state, int time) {
			super();
			this.inTime = inTime;
			this.cost = cost;
			this.state = state;
			this.time = time;
		}
	}
	
	static Map<String, Car> map;
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1, 461, 1, 10}, new String[] {"00:00 1234 IN"})));
	}
	
	static int[] solution(int[] fees, String[] records) {
		map = new HashMap<>();
		
		for(int i = 0; i < records.length; i++) {
			String[] str = records[i].split(" ");
			
			if(map.containsKey(str[1])) { // 해당 차량번호가 등록 되어있으면
				// 상태 확인하고 요금계산해서 저장 
				Car car = map.get(str[1]); 
				
				if(str[2].length() == 3) { // OUT --> 주차요금 계산해서 cost 갱신 
					int time = getTime(fees, car.inTime, str[0]);
					car.time += time;
				}
				
				// IN인 경우 or 주차요금 갱신 끝난 경우 map 값 갱신 
				map.replace(str[1], new Car(str[0], car.cost, str[2], car.time));  
				continue;
			}
			
			// 차량 번호가 등록 되어있지 않으면 
			map.put(str[1], new Car(str[0], 0, str[2], 0)); // 차량등록
		}
		
		// 차량번호가 작은 자동차부터 cost 출력 (정렬)
		List<String> list = new ArrayList<>(map.keySet());
		list.sort((s1, s2)->s1.compareTo(s2));
		
		int[] answer = new int[map.size()];
		int i = 0;
		
		// 주차 요금 계산 
		for(String key : list) {
			Car car = map.get(key);
			if(car.state.equals("IN")) { // 출차하지 않아서 상태가 IN에 머물러 있는 차량
				int time = getTime(fees, car.inTime, "23:59");
				car.time += time;
			}
			
			answer[i] = getCost(fees, car.time);
			i++;
		}
		
		return answer;
	}
	
	// 주차시간 계산
	static int getTime(int[] fees, String inTime, String outTime) {
		
		String[] tempIn = inTime.split(":");
		String[] tempOut = outTime.split(":");
		
		int in = Integer.parseInt(tempIn[0]) * 60 + Integer.parseInt(tempIn[1]);
		int out = Integer.parseInt(tempOut[0]) * 60 + Integer.parseInt(tempOut[1]);
		return out - in;
	}
	
	
	// 주차요금 계산 
	static int getCost(int[] fees, int time) {
		int totalFee = 0;
		if(time <= fees[0]) { // 주차시간이 기본 시간보다 작거나 같으면
			totalFee = fees[1]; // 기본 요금
		}
		
		if(time > fees[0]) { // 주차시간이 기본 시간보다 크면
			totalFee = fees[1]; // 기본요금에 초과 시간만큼 단위요금 더하기 
			totalFee += Math.ceil((float)(time-fees[0])/fees[2]) * fees[3]; // 단위요금*(주차시간/단위시간)
		}
		
		return totalFee;
	}
}
