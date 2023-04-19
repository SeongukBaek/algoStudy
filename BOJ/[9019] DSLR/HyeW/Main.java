import java.io.*;
import java.util.*;

public class Main {
	static final int MAXSIZE = 10000;
	static char[] orders = {'D','S','L','R'};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < TC; i++) {
			st = new StringTokenizer(br.readLine());
			
			Number origin = new Number(Integer.parseInt(st.nextToken()),"");
			int target = Integer.parseInt(st.nextToken());
			
			sb.append(getCommand(origin, target)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static String getCommand(Number origin, int target) {
		Queue<Number> commands = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		
		commands.add(origin);
		visited.add(origin.value);
		
        // 모든 명령어를 하나씩 해보며 target 수가 나오면 종료한다.
		while(!commands.isEmpty()) {
			Number cur = commands.poll();
			
			for(char order : orders) {
				int result = 0;
				if(order == 'D') {
					result = doDouble(cur.value);
				}else if(order == 'S') {
					result = doMinus(cur.value);
				}else if(order == 'L') {
					result = turnLeft(cur.value);
				}else {
					result = turnRight(cur.value);
				}
				
				if(result == target) {
					return cur.command + order;
				}
                // 한번 만든 수라면 큐에 담지 않는다.
				if(visited.contains(result)) {
					continue;
				}
				visited.add(result);
				commands.add(new Number(result, cur.command+order));
			}
		}
		return null;
	}
	/* 명령어 R를 수행한다. */
	private static int turnRight(int value) {
		int[] numberSlice = getNumberSlice(value);
		int end = numberSlice[0];
		for(int i = 1; i < 4; i++) {
			numberSlice[i-1] = numberSlice[i];
		}
		numberSlice[3] = end;
		return getNumber(numberSlice);
	}
    /* 명령어 L를 수행한다. */
	private static int turnLeft(int value) {
		int[] numberSlice = getNumberSlice(value);
		int first = numberSlice[3];
		for(int i = 2; i >= 0; i--) {
			numberSlice[i+1] = numberSlice[i];
		}
		numberSlice[0] = first;
		return getNumber(numberSlice);
	}
    /* 명령어 S를 수행한다. */
	private static int doMinus(int value) {
		return (value-1) == -1 ? MAXSIZE -1 : value - 1;
	}
    /* 명령어 D를 수행한다. */
	private static int doDouble(int value) {
		return (value*2)%MAXSIZE;
	}
    /* int[]에 담겨진 수를 int로 만든다. */
	private static Integer getNumber(int[] value) {
		int result = 0;
		for(int i = 3; i >= 0; i--) {
			result = value[i] + (result*10);
		}
		return result;
	}
    /* int형 수를 자리순으로 int[]에 담는다.  */
	private static int[] getNumberSlice(int value) {
		int[] number = new int[4];
		
		for(int i = 0; i < 4; i++) {
			 number[i] = value % 10;
			 value /= 10;
		}
		return number;
	}
	
	static class Number{
		int value;  // 수
		String command; // 만들어진 명령어
		
		public Number(int value, String command) {
			this.value = value;
			this.command = command;
		}
	}
}