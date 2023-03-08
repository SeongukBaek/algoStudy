import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] selected;
	static int x, y, d1, d2;
	static int total = 0;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		/* 실행 */
		divideDistrict(0, new int[4]);
		
		/* 출력 */
		System.out.println(ans);
	}
	
	/* 선거구 나누기 */
	static void divideDistrict(int depth, int[] num) {
		if(depth == 4) {
			if(checkBoundary(num)) {
				selected = new boolean[n+1][n+1];
				countPopulation();
			};
			return;
		}
		
		for(int i=1; i<=n; i++) {
			num[depth] = i;
			divideDistrict(depth+1, num);
		}
	}
	
	/* 경계선 체크하기 */
	static boolean checkBoundary(int[] num) {
		x = num[0];
		y = num[1];
		d1 = num[2];
		d2 = num[3];
		if(x > x + d1 + d2 || x + d1 + d2 > n) return false;
		if(1 > y - d1 || y - d1 >= y || y >= y + d2 || y + d2 > n) return false;
		return true;
	}
	
	static void countPopulation() {
		int[] ppl = new int[5];
		setBoundary5();
		ppl[0] = countDistrict1();
		ppl[1] = countDistrict2();
		ppl[2] = countDistrict3();
		ppl[3] = countDistrict4();
		ppl[4] = total - ppl[0] - ppl[1] - ppl[2] - ppl[3];
		
		// 인구 차의 최소값 구하기
		int min = Integer.MAX_VALUE;
		int max = 0;
		for(int p : ppl) {
			min = min > p ? p : min;
			max = max < p ? p : max;
		}
		ans = ans > max - min ? max - min : ans;
	}
	
	/* 5번 선거구의 경계선 설정 */
	static void setBoundary5() {
		
		// 1번 경계선
		int i = x;
		int j = y;
		while(i<=x+d1 && j>=y-d1) {
			if(!selected[i][j]) {
				selected[i][j] = true;
			}
			i++;
			j--;
		}
		
		// 2번 경계선
		i = x; 
		j = y;
		while(i<=x+d2 && j<=y+d2) {
			if(!selected[i][j]) {
				selected[i][j] = true;
			}
			i++;
			j++;
		}
		
		// 3번 경계선
		i = x+d1;
		j = y-d1;
		while(i<=x+d1+d2 && j<=y-d1+d2) {
			if(!selected[i][j]) {
				selected[i][j] = true;
			}
			i++;
			j++;
		}
		
		// 4번 경계선
		i=x+d2;
		j=y+d2;
		while(i<=x+d2+d1 && j>=y+d2-d1) {
			if(!selected[i][j]) {
				selected[i][j] = true;
			}
			i++;
			j--;
		}
	}
	
	/* 1번 선거구의 인구 수 구하기 */
	static int countDistrict1() {
		int ppl = 0;
		for(int i=1; i<x+d1; i++) {
			for(int j=1; j<=y; j++) {
				if(selected[i][j]) {
					break;
				}
				ppl += map[i][j];
			}
		}
		return ppl;
	}
	
	/* 2번 선거구의 인구 수 구하기 */
	static int countDistrict2() {
		int ppl = 0;
		for(int i=1; i<=x+d2; i++) {
			for(int j=n; j>y; j--) {
				if(selected[i][j]) {
					break;
				}
				ppl += map[i][j];
			}
		}
		return ppl;
	}
	
	/* 3번 선거구의 인구 수 구하기 */
	static int countDistrict3() {
		int ppl = 0;
		for(int i=x+d1; i<=n; i++) {
			for(int j=1; j<y-d1+d2; j++) {
				if(selected[i][j]) {
					break;
				}
				ppl += map[i][j];
			}
		}
		return ppl;
	}
	
	/* 4번 선거구의 인구 수 구하기 */
	static int countDistrict4() {
		int ppl = 0;
		for(int i=x+d2+1; i<=n; i++) {
			for(int j=n; j>=y-d1+d2; j--) {
				if(selected[i][j]) {
					break;
				}
				ppl += map[i][j];
			}
		}
		return ppl;
	}
}
