import java.io.*;
import java.util.*;

public class VillageNumber {
	static List<List<Integer>> village;
	static boolean[] connected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			village = new ArrayList<>();
			connected = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				village.add(new ArrayList<>());
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int p1 = Integer.parseInt(st.nextToken()) - 1;
				int p2 = Integer.parseInt(st.nextToken()) - 1;
				
				village.get(p1).add(p2);
				village.get(p2).add(p1);
			}
			
			int cnt = 0;
			
			for(int i = 0; i < N; i++) {
				if(!connected[i]) {
					cnt++;
					checkGroup(i);
				}
			}
			System.out.println("#"+t+" "+cnt);
		}	
	}
	
	static void checkGroup(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		connected[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int nxt : village.get(cur)) {
				
				if(connected[nxt]) {
					continue;
				}
				
				q.add(nxt);
				connected[nxt] = true;
				
			}
		}
	}

}