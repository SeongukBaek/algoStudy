import java.io.*;
import java.util.StringTokenizer;

public class Main_2527 {
	static int x1, y1, p1, q1;
	static int x2, y2, p2, q2;
	static char answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 사각형 1
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			p1 = Integer.parseInt(st.nextToken());
			q1 = Integer.parseInt(st.nextToken());
			
			// 사각형 2
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			p2 = Integer.parseInt(st.nextToken());
			q2 = Integer.parseInt(st.nextToken());
			
			checkSquare();

		}
	}
	
	static void checkSquare() {
		
		// 안겹칠 때 
		if (p1 < x2 || q1 < y2 || p2 < x1 || q2 < y1) {
			System.out.println("d");
		} // 점일 때
		else if ((x1 == p2 && y1 == q2) || (x1 == p2 && q1 == y2) || (p1 == x2 && q1 == y2) || (p1 == x2 && y1 == q2)) {
			System.out.println("c");
		} // 선분일 때 
		else if (p1 == x2 || q1 == y2|| p2 == x1 || y1 == q2){
			System.out.println("b");
		} // 직사각형으로 겹칠 때 
		else {
			System.out.println("a");
		}
	}
}
