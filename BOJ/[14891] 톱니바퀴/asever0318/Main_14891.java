import java.io.*;

public class Main {
	
	static class Gear{
		int[] state;
		int left, right, top;
		
		public Gear(int[] state, int left, int right, int top) {
			super();
			this.state = state;
			this.left = left;
			this.right = right;
			this.top = top;
		}
		
		public void spin(int d) {
			this.top = findIndex(this.top, d);
			this.right = findIndex(this.right, d);
			this.left = findIndex(this.left, d);
		}
		
		public int findIndex(int index, int d) {
			if(d == -1) { // 반시계 방향일 때 
				if(index == 7) {
					return 0;
				}else {
					return ++index;
				}
			}
			
			else{ // 시계방향일 때
				if(index == 0) {
					return 7;
				}else {
					return --index;
				}
			}
		}
	}
	
	static Gear[] gears;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gears = new Gear[4];
		
		for(int i = 0; i < 4 ; i++) {
			String[] str = br.readLine().split("");
			int[] s = new int[str.length];
			for(int j = 0; j < str.length; j++) {
				s[j] = Integer.parseInt(str[j]);
			}
			gears[i] = new Gear(s, 6, 2, 0); // 상태, left, right 인덱스 
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < K; i++) {
			String[] temp = br.readLine().split(" ");
			int number = Integer.parseInt(temp[0]); // 회전시킬 톱니바퀴 번호 
			int direction = Integer.parseInt(temp[1]); // 회전 시킬 방향 
			
			spinGear(number, direction);
		}
		
		System.out.println(countScore());
	}
	
	
	static void spinGear(int number, int direction) {
		
		// 먼저 돌려버리면 다음 바퀴에서 비교할 극 인덱스가 바뀌어버리므로 돌릴 방향 먼저 저장 
		int[] spinInfo = new int[4]; 
		
		if(number == 1) { // 시작 톱니바퀴 
			spinInfo[0] = direction; // 첫 번째 회전 톱니바퀴 방향 저장 
			if(gears[0].state[gears[0].right] != gears[1].state[gears[1].left]) {
				// 1번 톱니바퀴와 2번 톱니바퀴의 극이 서로 다르면
				// 반대 방향으로 회전 
				spinInfo[1] = findDirection(spinInfo[0]);
			}else {
				spinInfo[1] = 0;
			}
			
			if(gears[1].state[gears[1].right] != gears[2].state[gears[2].left]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면 
				spinInfo[2] = findDirection(spinInfo[1]);
			}else {
				spinInfo[2] = 0;
			}
			
			if(gears[2].state[gears[2].right] != gears[3].state[gears[3].left]) {
				// 3번 톱니바퀴와 4번 톱니바퀴의 극이 서로 다르면 
				spinInfo[3] = findDirection(spinInfo[2]);
			}else {
				spinInfo[3] = 0;
			}
		}
		
		if(number == 2) { // 시작 톱니바퀴 
			spinInfo[1] = direction;
			// 오른쪽 
			if(gears[1].state[gears[1].right] != gears[2].state[gears[2].left]) {
				spinInfo[2] = findDirection(spinInfo[1]);
			}else {
				spinInfo[2] = 0;
			}
			
			if(gears[2].state[gears[2].right] != gears[3].state[gears[3].left]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면 
				spinInfo[3] = findDirection(spinInfo[2]);
			}else {
				spinInfo[3] = 0;
			}
			
			// 왼쪽 
			if(gears[1].state[gears[1].left] != gears[0].state[gears[0].right]) {
				// 3번 톱니바퀴와 4번 톱니바퀴의 극이 서로 다르면 
				spinInfo[0] = findDirection(spinInfo[1]);
			}else {
				spinInfo[0] = 0;
			}
		}
		
		if(number == 3) { // 시작 톱니바퀴 
			spinInfo[2] = direction;
			// 오른쪽 
			if(gears[2].state[gears[2].right] != gears[3].state[gears[3].left]) {
				spinInfo[3] = findDirection(spinInfo[2]);
			}else {
				spinInfo[3] = 0;
			}
			
			// 왼쪽
			if(gears[2].state[gears[2].left] != gears[1].state[gears[1].right]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면 
				spinInfo[1] = findDirection(spinInfo[2]);
			}else {
				spinInfo[1] = 0;
			}
			
			if(gears[1].state[gears[1].left] != gears[0].state[gears[0].right]) {
				// 3번 톱니바퀴와 4번 톱니바퀴의 극이 서로 다르면 
				spinInfo[0] = findDirection(spinInfo[1]);
			}else {
				spinInfo[0] = 0;
			}
		}
		
		if(number == 4) { // 시작 톱니바퀴 
			spinInfo[3] = direction;
			
			// 왼쪽
			if(gears[3].state[gears[3].left] != gears[2].state[gears[2].right]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면 
				spinInfo[2] = findDirection(spinInfo[3]);
			}else {
				spinInfo[2] = 0;
			}
			
			if(gears[2].state[gears[2].left] != gears[1].state[gears[1].right]) {
				// 2번 톱니바퀴와 3번 톱니바퀴의 극이 서로 다르면 
				spinInfo[1] = findDirection(spinInfo[2]);
			}else {
				spinInfo[1] = 0;
			}
			
			if(gears[1].state[gears[1].left] != gears[0].state[gears[0].right]) {
				// 3번 톱니바퀴와 4번 톱니바퀴의 극이 서로 다르면 
				spinInfo[0] = findDirection(spinInfo[1]);
			}else {
				spinInfo[0] = 0;
			}
		}

		
		// 찾아놓은 회전 방향에 맞게 회전 시키기 
		for(int i = 0; i < 4; i++) {
			if(spinInfo[i] != 0) {
				gears[i].spin(spinInfo[i]);
			}
		}
	}
	
	static int findDirection(int d) {
		if(d == 1) {
			return -1;
		}else if(d == -1) {
			return 1;
		}
		return 0;
	}
	
	static int countScore() {
		if(gears[0].state[gears[0].top] == 1) {
			answer += 1;
		}
		
		if(gears[1].state[gears[1].top] == 1) {
			answer += 2;
		}
		
		if(gears[2].state[gears[2].top] == 1) {
			answer += 4;
		}
		
		if(gears[3].state[gears[3].top] == 1) {
			answer += 8;
		}
		
		return answer;
	}
}