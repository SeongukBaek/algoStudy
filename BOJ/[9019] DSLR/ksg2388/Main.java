import java.io.*;
import java.util.*;

/*
 * 각 팀을 배열 arrayList에 저장
 */

public class Main {
	static int n;
	static boolean[] visited;
	static String[] codes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        
        while (n-- > 0) {
        	visited = new boolean[10000];
        	codes = new String[10000];
        	st = new StringTokenizer(br.readLine());
        	Queue<Integer> queue = new LinkedList<>();
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	queue.add(start);
        	visited[start] = true;
        	codes[start] = "";
        	
        	while(!queue.isEmpty()) {
        		int cur = queue.poll();
        		if (cur == end) {
        			answer.append(codes[end]).append("\n");
        			break;
        		}
        		
        		for (int i = 0; i < 4; i++) {
        			int next = cur;
        			String code = codes[cur];
        			
        			if (i == 0) {
        				next = next * 2 > 9999 ? next * 2 % 10000 : next * 2;
        				code += "D";
        			}
        			if (i == 1) {
        				next = next - 1 < 0 ? 9999 : next - 1;
        				code += "S";
        			}
        			if (i == 2) {
        				int temp = next / 1000;
        				next = next * 10 % 10000 + temp;
        				code += "L";
        			}
        			if (i == 3) {
        				int temp = next % 10;
        				next = next / 10 + temp * 1000;
        				code += "R";
        			}
        			
        			if (visited[next]) {
        				continue;
        			}
        			queue.add(next);
        			visited[next] = true;
        			codes[next] = code.toString();
        		}
        	}
        }
        
        System.out.println(answer);
    }
 
}
