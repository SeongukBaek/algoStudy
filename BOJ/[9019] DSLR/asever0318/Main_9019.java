import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Number{
		int num;
		String command;
		public Number(int num, String command) {
			super();
			this.num = num;
			this.command = command;
		}
	}
	
	static int n1, n2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			
			System.out.println(calcNum());
		}
	}
	
	static String calcNum() {
		String cmd = null;
		Queue<Number> queue = new LinkedList<>();
		// 한 숫자에 대해서 한 번에 모든 명령어 다 확인해줄 거이기 때문에 한 숫자는 다시 반복하지 않기 위해서 방문처리
		boolean[] visited = new boolean[10000]; 
		queue.add(new Number(n1, ""));
		visited[n1] = true;
		
		while(!queue.isEmpty()) {
			Number current = queue.poll();
			
			if(current.num == n2) { // n2와 일치하면 끝!
				return current.command;
			}
			
			for(int i = 0; i < 4; i++) {
				Number next = command(i, current.num);
				
				if(visited[next.num]) {
					continue;
				}
				
				queue.add(new Number(next.num, current.command+next.command));
				visited[next.num] = true;
			}
			
		}
		
		return cmd;
	}
	
	static Number command(int i, int n1) {
		if(i == 0) {
			return new Number(D(n1), "D");
		}else if(i == 1) {
			return new Number(S(n1), "S");
		}else if(i == 2) {
			return new Number(L(n1), "L");
		}else {
			return new Number(R(n1), "R");
		}
	}
	
	static int D(int num) { // num * 2, 만약 9999보다 큰 경우에는 10000으로 나눈 나머지
		return (num * 2) % 10000; // 10000미만일 때 10000으로 나눈 나머지는 자기자신이므로 따로 처리할 필요 x 
	}
	
	static int S(int num) { // num - 1, 만약 0이면 9999 반환
		if(num == 0) {
			return 9999;
		}
		return num - 1;
	}
	
	static int L(int num) { // 왼쪽으로 한 칸 이동 
		return ((num % 1000) * 10) + (num / 1000);		
	}
	
	static int R(int num) { // 오른쪽으로 한 칸 이동 
		return ((num % 10) * 1000) + (num / 10);
	}
}
