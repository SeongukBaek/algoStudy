import java.util.*;
import java.io.*;

public class Main {
	private static int k;		// 부등호 개수
	private static boolean[] isVisited;
	private static char[] signs;
	private static List<String> result = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		k = Integer.parseInt(br.readLine());
		isVisited = new boolean[10];
		signs = new char[k];
		
		// 부등호 초기화
		String str = br.readLine();
		st = new StringTokenizer(str);
		
		for (int i = 0; i < k; i++) {
			signs[i] = st.nextToken().charAt(0);
		}
		dfs("", 0);
		Collections.sort(result);
		System.out.println(result.get(result.size() - 1));
		System.out.println(result.get(0));
	}
	
	private static void dfs(String num, int depth) {
		if (depth == k + 1) {
			result.add(num);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (depth == 0 || !isVisited[i] && compare(num.charAt(num.length() - 1) - '0', i, signs[depth - 1])) {
				isVisited[i] = true;
				dfs(num + i, depth + 1);
				isVisited[i] = false;
			}
		}
	}
	
	private static boolean compare(int a, int b, char c) {
		if (c == '<') return a < b;
		else return a > b;
	}
}