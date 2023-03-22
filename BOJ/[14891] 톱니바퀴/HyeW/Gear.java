import java.util.*;
import java.io.*;

public class Gear {
	static final int GEAR_COUNT = 4;
	static final int SAWTOOTH = 8;
	static char[][] gears;
	static boolean[] visited;
	//1 시계방향 
	//-1 반시계방향
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gears = new char[GEAR_COUNT][SAWTOOTH];
		for(int num = 0; num < GEAR_COUNT; num++) {
			gears[num] = br.readLine().toCharArray();
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			visited = new boolean[GEAR_COUNT];
			turnGear(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getScore());
		
	}

	static void turnGear(int gearNum, int dir) {
		
		visited[gearNum] = true;
		
        //왼쪽 기어 회전
		if(gearNum != 0) {
            //맞닿은 톱니의 극이 다르고 방문하지 않았다면 기어 돌리기
			if(gears[gearNum][6] != gears[gearNum-1][2] && !visited[gearNum-1]) {
				turnGear(gearNum-1, dir*(-1));
			}
		}
		
        //오른쪽 기어 회전
		if(gearNum != GEAR_COUNT-1) {
			if(gears[gearNum][2] != gears[gearNum+1][6] && !visited[gearNum+1]) {
				turnGear(gearNum+1, dir*(-1));
			}
		}
		
		if(dir == 1) {
			turnRight(gearNum);
		}else if(dir == -1) {
			turnLeft(gearNum);
		}
	}
	
	static void turnRight(int gearNum) {
		char last = gears[gearNum][SAWTOOTH-1];
		
		for(int i = SAWTOOTH-1; i > 0; i--) {
			gears[gearNum][i] = gears[gearNum][i-1]; 
		}
		
		gears[gearNum][0] = last;
	}
	
	static void turnLeft(int gearNum) {
		char first = gears[gearNum][0];
		
		for(int i = 0; i < SAWTOOTH-1; i++) {
			gears[gearNum][i] = gears[gearNum][i+1]; 
		}
		
		gears[gearNum][SAWTOOTH-1] = first;
	}
	
	private static int getScore() {
		int score = 0;
		
		for(int i = 0; i < GEAR_COUNT; i++) {
			if(gears[i][0] != '1') {
				continue;
			}
			score += Math.pow(2, i);
		}
		return score;
	}
}
