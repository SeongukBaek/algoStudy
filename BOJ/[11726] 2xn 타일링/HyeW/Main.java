import java.io.*;

public class Main {
	static int[] tile;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
        // 피보나치 수열 초기화
		tile = new int[n+1];
		tile[0] = 1;
		tile[1] = 1;
		
		System.out.println(tiling(n));
	}

	private static int tiling(int n) {
		if(tile[n] != 0) {
			return tile[n];
		}
		return tile[n] = (tiling(n-1) + tiling(n-2))%10007;
	}
}
