import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	
	final static long MOD = 1000000007;
	public static long[][] origin = {{1, 1} , {1, 0}};	// 초기값을 갖고있는 행렬
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		long[][] A = {{1, 1} , {1, 0}};	
		long N = Long.parseLong(br.readLine());
		
		System.out.println(pow(A, N - 1)[0][0]);
	}
	
	public static long[][] pow(long[][] A, long exp) {
		if(exp == 1 || exp == 0) {
			return A;
		}
		
		long[][] ret = pow(A, exp / 2);

		ret = multiply(ret, ret);

		if(exp % 2 == 1L) {
			ret = multiply(ret, origin);
		}
		
		return ret;
	}
	
	
	public static long[][] multiply(long[][] o1, long[][] o2) {
		long[][] ret = new long[2][2];
 
		ret[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD;
		ret[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD;
		ret[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD;
		ret[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD;
 
		return ret;
	}
 
}