import java.io.*;
import java.util.*;

public class Main {
	public static int l;
	public static int c;
	public static char[] alp;
	public static List<String> result = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		alp = new char[c];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			alp[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alp);
		
		dfs(0, "", 0);
		
		for (String str: result)
			System.out.println(str);
	}
	
	public static void dfs(int n, String code, int depth) {
		if (depth == l) {
			if (isValidate(code)) result.add(code);
			return;
		}
		
		for (int i = n; i < c; i++) {
			dfs(i + 1, code + alp[i], depth + 1);
		}
	}
	
	public static boolean isValidate(String str) {
		int mo = 0;
		int ja = 0;
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
				mo++;
			else
				ja++;
		}
		
		if (ja >= 2 && mo >= 1) return true;
		return false;
	}
}