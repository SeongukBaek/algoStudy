import java.io.*;

public class Main {
	static int N;
	static int[] tile;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		tile = new int[1001];
		
		System.out.println(tiling());
		
	}
	
	public static int tiling() {
		
		tile[1] = 1;
		tile[2] = 2;
		
		for(int i = 3; i <= N; i++) {
			tile[i] = (tile[i-1] + tile[i-2]) % 10007;
		}
		
		return tile[N];
	}
}
