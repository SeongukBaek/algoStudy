import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<int[]> triangle;   // 삼각형 정보를 받는 리스트
	static List<int[]> maxCost;    // 각 위치의 최대 수의 합을 구한 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

        // 리스트 초기화
		triangle = new ArrayList<>();
		maxCost = new ArrayList<>();
		int pre = 0;
		for (int i = 0; i < N; i++) {
			triangle.add(new int[i+pre+1]);
			maxCost.add(new int[i+pre+1]);
			pre += i;
		}

        // 삼각형 input 값 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				triangle.get(i)[j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(getMaxCost());
	} 

    /* 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 구한다. */
	private static int getMaxCost() {
		// 꼭지점 초기화
		maxCost.get(0)[0] = triangle.get(0)[0];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j <= i; j++) {
                //왼쪽 변일 경우
				if(j == 0) {
					maxCost.get(i)[j] = maxCost.get(i-1)[j]+triangle.get(i)[j];
					continue;
				}
                //오른쪽 변일 경우
				if(j == i) {
					maxCost.get(i)[j] = maxCost.get(i-1)[j-1]+triangle.get(i)[j];
					continue;
				}
				maxCost.get(i)[j] = Math.max(maxCost.get(i-1)[j],  maxCost.get(i-1)[j-1])
						+triangle.get(i)[j];
			}
		}

		int max = 0;
		for(int[] costs : maxCost) {
			for(int cost : costs) {
				max = Math.max(max, cost);
			}
		}
		
		return max;
	}

}
