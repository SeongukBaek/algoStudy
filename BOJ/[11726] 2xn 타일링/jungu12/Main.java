import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int prevPrevNum = 1;
		int prevNum = 1;
		int curNum = 1;
		
		for(int i = 2; i <= N ; i++) {
			prevPrevNum = prevNum;
			prevNum = curNum;
			curNum = (prevPrevNum + prevNum) % 10007;
		}
		
		System.out.println(curNum);
	}
}