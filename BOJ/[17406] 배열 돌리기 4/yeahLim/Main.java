import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int[][] orgnArr; // 원본 배열
	static int[][] tmp; // 임시 배열
	static int min = Integer.MAX_VALUE;
	static int[] r, c, s;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {

		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		orgnArr = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				orgnArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		tmp = new int[n][m]; 
		
		/* 명령어 순서 돌리기 */
		r = new int[k];
		c = new int[k];
		s = new int[k];
		visited = new boolean[k];
		int[] order = new int[k];
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			r[i] = Integer.parseInt(st.nextToken()) - 1;
			c[i] = Integer.parseInt(st.nextToken()) - 1;
			s[i] = Integer.parseInt(st.nextToken());
		}
		permutation(0, order);
		
		/* 출력 */
		System.out.println(min);
	}
	
	/* 순열 */
	static void permutation(int depth, int[] order) {
		if(depth == k) {			
			tmp = cloneArray(orgnArr); //orgn 배열 복사
			for(int i=0; i<k; i++) {
				rotateArray(r[order[i]]-s[order[i]], c[order[i]]-s[order[i]], r[order[i]]+s[order[i]], c[order[i]]+s[order[i]], tmp, cloneArray(tmp));
			}
			getMinValue(tmp);
			return ;
		}
		for(int i=0; i<k; i++) {
			if(!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				permutation(depth+1, order);
				visited[i] = false;
			}
		}
		
	}
	
	/* 배열 복사 */
	static int[][] cloneArray(int[][] array) {
		int[][] cpArr = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				cpArr[i][j] = array[i][j];
			}
		}
		return cpArr;
	}
	
	
	/* 배열 돌리기 */       									// 임시 배열	// tmp 배열 복사
	static void rotateArray(int x1, int y1, int x2, int y2, int[][] tmp, int[][] cpArr) {
		if(x1 == x2 && y1 == y2) {
			return ;
		}

		// 좌 -> 우
		for(int j=y1+1; j<=y2; j++) {
			tmp[x1][j] = cpArr[x1][j-1];
		}
		

		// 상 -> 하
		for(int i=x1+1; i<=x2; i++) {
			tmp[i][y2] = cpArr[i-1][y2];
		}

		// 우 -> 좌
		for(int j=y2-1; j>=y1; j--) {
			tmp[x2][j] = cpArr[x2][j+1];
		}

		// 하 -> 상
		for(int i=x2-1; i>=x1; i--) {
			tmp[i][y1] = cpArr[i+1][y1];
		}
		
		rotateArray(x1+1, y1+1, x2-1, y2-1, tmp, cloneArray(tmp));
	}
	
	/* 최소값 찾기 */
	static void getMinValue(int[][] array) {
	
		for(int i=0; i<n; i++) {
			int sum = 0;
			for(int j=0; j<m; j++) {
				sum += array[i][j];
			}
			if(min > sum) {
				min = sum;
			}
		}
	}
}
