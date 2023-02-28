import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    private static int N;
    private static List<int[]> stairs;
    private static List<int[]> people;
    // 해당 사람들이 계단을 올랐을 때 걸리는 시간을 저장
    private static Map<String, Integer> stair1times;
    private static Map<String, Integer> stair2times;

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	    StringTokenizer st;
	    StringBuilder answer = new StringBuilder();
	
	    for (int test = 1; test <= T; test++) {
	        answer.append("#").append(test).append(" ");
	        st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        stairs = new ArrayList<>();
	        people = new ArrayList<>();
	        stair1times = new HashMap<>();
	        stair2times = new HashMap<>();
	
	        for (int x = 0; x < N; x++) {
	            st = new StringTokenizer(br.readLine());
	            for (int y = 0; y < N; y++) {
	                int number = Integer.parseInt(st.nextToken());
	                if (number >= 2) {
	                    stairs.add(new int[] {x, y, number});
	                }
	                if (number == 1) {
	                    people.add(new int[] {x, y});
	                }
	            }
	        }
	
	        for (int count = 0; count <= people.size(); count++) {
	            makeSubset(new StringBuilder(), new boolean[people.size()], 0, count);
	        }
	
	        int minTime = Integer.MAX_VALUE;
	        // 계단 1을 0,1이 오른다면, 계단 2는 그 반대인 2,3,4,5가 오르게 된다.
	        for (Map.Entry<String, Integer> entry : stair1times.entrySet()) {
	            minTime = Math.min(minTime, Math.max(entry.getValue(), stair2times.get(getOpposite(entry.getKey()))));
	        }
	
	        answer.append(minTime + 1).append("\n");
	    }
	
	    System.out.print(answer);
	}
	
	/**
	 * 계단을 오르게 될 사람들을 조합 형태로 뽑기
	 * 다 뽑게 되면, 해당 사람들이 계단 1을 오를 때의 최소 시간과 계단 2를 오를 때의 최소 시간을 저장
	 * @param selected 뽑힌 사람들
	 * @param isVisited 해당 사람이 이미 뽑혔는지 여부 저장
	 * @param start 다음으로 뽑을 수 있는 첫번째 사람
	 * @param count 총 뽑아야 하는 사람 수
	 * */
	private static void makeSubset(StringBuilder selected, boolean[] isVisited, int start, int count) {
	    if (selected.length() == count) {
	        // selected -> 계단 1, selected -> 계단 2 다 구하기
	        String key = selected.toString();
	        stair1times.put(key, goDownStairs(key, 0));
	        stair2times.put(key, goDownStairs(key, 1));
	        return;
	    }
	
	    for (int index = start; index < people.size(); index++) {
	        if (isVisited[index]) {
	            continue;
	        }
	        isVisited[index] = true;
	        selected.append(index);
	        makeSubset(selected, isVisited, index + 1, count);
	        selected.deleteCharAt(selected.length() - 1);
	        isVisited[index] = false;
	    }
	}
	
	/**
	 * 해당 type의 계단을 오르게 될 사람들이 주어졌을 때 걸리는 최소 시간을 반환
	 * @param selected 해당 계단을 오르게 될 사람들의 번호를 붙여만든 문자열
	 * @param type 계단 정보 (1 or 2)
	 * @return int
	 * */
	private static int goDownStairs(String selected, int type) {
		Queue<Integer> times = new PriorityQueue<>();
		int time[] = new int[100];
		
		int max = 0;
		int[] stair = stairs.get(type);
		int k = stair[2];
		
		// 우선순위 큐에 해당 계단을 사용할 사람들의 도착 시간 저장
		for (int index = 0; index < selected.length(); index++) {
	        times.add(getDistance(stair, people.get(selected.charAt(index) - '0')));
	    }
		
		while (!times.isEmpty()) {
			int currentPersonStart = times.poll();
			int currentPersonEnd = currentPersonStart + k;
			
			// 누적합과 비슷하게, time은 해당 시간대에 계단 위에 있는 사람 수를 의미
			for (int index = currentPersonStart; index < currentPersonEnd; index++) {
				// 만약 해당 시간에 계단 위에 3명이 있다면, 현재 current 사람은 1분을 대기해야 하므로, currentPersonEnd가 1 증가!
				if (time[index] == 3) {
					currentPersonEnd++;
					continue;
				}
				time[index]++;
			}
			
			if (max < currentPersonEnd) {
				max = currentPersonEnd;
			}
		}
		return max;
	}
	
	/**
	 * 계단과 사람의 거리 반환
	 * @param stair 계단 위치 정보
	 * @param location 사람 위치 정보
	 * @return int
	 * */
	private static int getDistance(int[] stair, int[] location) {
	    return Math.abs(stair[0] - location[0]) + Math.abs(stair[1] - location[1]);
	}
	
	/**
	 * 주어진 문자열에 포함된 숫자를 포함하지 않는 문자열 반환
	 * @param numbers 주어지는 문자열
	 * @return String
	 * */
	private static String getOpposite(String numbers) {
	    StringBuilder opposites = new StringBuilder();
	    int index = 0;
	    for (int number = 0; number < people.size(); number++) {
	        if (numbers.length() <= index || numbers.charAt(index) - '0' != number) {
	            opposites.append(number);
	        } else {
	            index++;
	        }
	    }
	    return opposites.toString();
	}
}