import java.util.*;

class Solution {
    // 기본 시간
    private static int basicTime;
    // 기본 요금
    private static int basicFee;
    // 단위 시간
    private static int unitTime;
    // 단위 요금
    private static int unitFee;

    // 차 번호별 입차 시간을 저장하는 배열
    private final int[] carTimes = new int[10000];
    // 차 번호별 누적 주차 시간을 저장하는 map
    private final int[] cumulativeTimes = new int[10000];

    public int[] solution(int[] fees, String[] records) {
        init(fees);
        // 입차 시간이 00:00인 경우도 존재해서 -1로 초기화
        Arrays.fill(carTimes, -1);

        readRecords(records);

        processRemainingCars();

        return calculateFees();
    }

    /**
     * 요금표 정보 저장
     */
    private void init(int[] fees) {
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
    }

    /**
     * 입,출차 정보를 맵에 기록
     */
    private void readRecords(String[] records) {
        for (String record : records) {
            String[] info = record.split(" ");
            int time = timeToInt(info[0]);
            int carNumber = Integer.parseInt(info[1]);

            // 입차한 경우
            if (info[2].equals("IN")) {
                // 차 번호별 입차 시간 저장
                carTimes[carNumber] = time;
                continue;
            }

            // 출차한 경우
            // 주차 시간 계산
            time -= carTimes[carNumber];

            cumulativeTimes[carNumber] += time;
            carTimes[carNumber] = -1;
        }
    }

    private int timeToInt(String info) {
        String[] line = info.split(":");
        int hour = Integer.parseInt(line[0]) * 60;
        int min = Integer.parseInt(line[1]);

        return hour + min;
    }

    /**
     * 출차 기록이 없는 차 처리
     * */
    private void processRemainingCars() {
        for (int carNumber = 0; carNumber < 10000; carNumber++) {
            if (carTimes[carNumber] == -1) {
                continue;
            }
            cumulativeTimes[carNumber] += 1439 - carTimes[carNumber];
        }
    }

    // 차 번호별 누적 주차 시간을 이용해 주차 요금 계산
    private int[] calculateFees() {
        List<Integer> fees = new ArrayList<>();
        for (int time : cumulativeTimes) {
            if (time == 0) {
                continue;
            }

            // 기본 요금으로 세팅
            int fee = basicFee;

            // 기본 요금보다 큰 경우, 단위 요금으로 계산
            if (time > basicTime) {
                time = (int) Math.ceil((double) (time - basicTime) / unitTime);
                fee += (time * unitFee);
            }

            fees.add(fee);
        }

        return fees.stream().mapToInt(Integer::intValue).toArray();
    }
}