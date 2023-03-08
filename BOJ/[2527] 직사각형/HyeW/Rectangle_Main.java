import java.io.*;
import java.util.*;

public class Rectangle_Main {
	static final int TESTCASE = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < TESTCASE; i++) {
			
			st = new StringTokenizer(br.readLine());
			Rectangle first = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Rectangle second = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			distinctionRec(first, second);
		}
		
	}
	
	private static void distinctionRec(Rectangle first, Rectangle second) {
		
		Rectangle left = first;
		Rectangle right = second;
		if(second.y1 < first.y1) {
			left = second;
			right = first;
		}
		
		Rectangle top = first;
		Rectangle bottom = second;
		if(second.x2 < first.x2) {
			top = second;
			bottom = first;
		}
		
		int firstWidth = first.y2 - first.y1;
		int firstHeight = first.x2 - first.x1;
		int secondWidth = second.y2 - second.y1;
		int secondHeight = second.x2 - second.x1;
		//선분
		if((left.y2 == right.y1 && bottom.x2 - top.x1 <= (firstHeight+secondHeight)) 
				||(top.x2 == bottom.x1 && right.y2 - left.y1 <= (firstWidth+secondWidth)) ) {
			System.out.println("b");
			return;
		}

		//꼭짓점
		if(isSameSpot(first.x1, first.y1, second.x2, second.y2) || isSameSpot(first.x2, first.y1,second.x1, second.y2)
				|| isSameSpot(first.x2, first.y2, second.x1, second.y1) || isSameSpot(first.x1, first.y2, second.x2, second.y1)) {
			System.out.println("c");
			return;
		}
		
		//면
		if(left.y2 < right.y1 || top.x2 < bottom.x1) {
			System.out.println("d");
			return;
		}
		
		System.out.println("a");
	}

	private static boolean isSameSpot(int x1, int y1, int x2, int y2) {
		return ((x1 == x2) && (y1 == y2));
	}

	private static class Rectangle{
		int x1;
		int y1;
		int x2;
		int y2;
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

}