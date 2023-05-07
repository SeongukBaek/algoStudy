import java.util.*;

class Solution {

    static class Car implements Comparable<Car> {
		int idx;
		int carNum; 
		String inTime; 
		int parkingTime = 0;
		int parkingFee = 0;
		boolean isOut = false;
		
		public Car(int idx, int carNum, String time) {
			this.idx = idx;
			this.carNum = carNum;
			this.inTime = time;
		}
		
		/* 주차 시간 계산 */
		void setParkingTime(String outTime) {
	        String[] inTimeInfo = inTime.split(":");
	        String[] outTimeInfo = outTime.split(":");
	        int fromHour = Integer.parseInt(inTimeInfo[0]);
	        int fromMin = Integer.parseInt(inTimeInfo[1]);
	        int toHour = Integer.parseInt(outTimeInfo[0]);
	        int toMin = Integer.parseInt(outTimeInfo[1]);
	        this.parkingTime += (toHour - fromHour) * 60 + toMin - fromMin;
	        this.isOut = true;
		}

		@Override
		public int compareTo(Car o) {
			return this.carNum - o.carNum;
		}
	}
	
    static int[] feeTable;
    
    public static int[] solution(int[] fees, String[] records) {
        feeTable = fees.clone();
        int idx = 0;
        int[] carIndex = new int[10000]; // 차 인덱스 확인
        Arrays.fill(carIndex, -1);
        List<Car> cars = new ArrayList<>();
        
        for(String record : records) {
            String[] recordInfo = record.split(" ");
            int carNum = Integer.parseInt(recordInfo[1]);
            
            // 입차한 경우
            if (recordInfo[2].equals("IN")) {
            	
            	// 처음 입차한 경우
            	if (carIndex[carNum] == -1) {
            		cars.add(new Car(idx, carNum, recordInfo[0]));
            		carIndex[carNum] = idx++;
            	}
            	// 아닌 경우
            	else {
            		cars.get(carIndex[carNum]).inTime = recordInfo[0];
            		cars.get(carIndex[carNum]).isOut = false;
            	}
                
            }
            
            // 출차한 경우
            if (recordInfo[2].equals("OUT")) {
            	cars.get(carIndex[carNum]).setParkingTime(recordInfo[0]);
            }
        }
        
        // 주차 정산
        for (int i = 0; i < cars.size(); i++) {
        	Car car = cars.get(i);
        	
        	 // 출차 정보가 없는 경우 (23:59에 출차)
        	if(!car.isOut) {
        		car.setParkingTime("23:59");
        	}
        	
            car.parkingFee = calculateFee(car.parkingTime);
        }
        
        // 차 번호 순으로 정렬
        Collections.sort(cars);
        
        /* 출력 */
        int[] answer = new int[cars.size()];
        for(int i = 0; i < cars.size(); i++) {
            answer[i] = cars.get(i).parkingFee;
        }
        return answer;
    }
    
    /* 주차 요금 계산 */
    public static int calculateFee(int parkingTime) {
    	
        // 주차 시간이 0인 경우
        if (parkingTime == 0) {
            return 0;
        }
        
        // 기본 시간 미만인 경우
        if (parkingTime <= feeTable[0]) {
            return feeTable[1];
        }

        // 기본 시간 초과한 경우
        parkingTime -= feeTable[0];
        if (parkingTime % feeTable[2] == 0) {
            return feeTable[1] + parkingTime / feeTable[2] * feeTable[3];
        }
        return feeTable[1] + (parkingTime / feeTable[2] + 1) * feeTable[3];
    }
}