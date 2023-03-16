import java.util.*;

class Solution {
	int maxAlp, maxCop;
	int[][] minTime; // row : algo / col : cop

	public int solution(int alp, int cop, int[][] problems) {

    //dp배열의 크기를 정하기 위해 가장 큰 능력을 구한다.
		maxAlp = alp;
		maxCop = cop;
		for (int[] problem : problems) {
			maxAlp = Math.max(problem[0], maxAlp);
			maxCop = Math.max(problem[1], maxCop);
		}

    /*배열 인덱스는 (1,1)부터 시작한다.
    그리고 마지막 인덱스일 경우를 따로 처리하지 않기 위해 배열의 사이즈를 1만큼 더 늘렸다.*/
		minTime = new int[maxAlp + 2][maxCop + 2];
		for (int r = 0; r <= maxAlp; r++) {
			Arrays.fill(minTime[r], Integer.MAX_VALUE);
		}

		getMinTime(alp, cop, problems);

		return minTime[maxAlp][maxCop];
	}

  //DP사용
	private void getMinTime(int alp, int cop, int[][] problems) {
		minTime[alp][cop] = 0;
		
		for (int r = alp; r <= maxAlp; r++) {
			for (int c = cop; c <= maxCop; c++) {
				int cur = minTime[r][c];

				// 알고리즘 공부를 했을 경우
				minTime[r + 1][c] = Math.min(minTime[r + 1][c], cur + 1);
				// 코딩 공부를 했을 경우
				minTime[r][c + 1] = Math.min(minTime[r][c + 1], cur + 1);

				// 문제를 풀었을 경우
				for (int[] problem : problems) {
					if (r < problem[0] || c < problem[1]) {
						continue;
					}

					int dr = Math.min(r + problem[2], maxAlp);
					int dc = Math.min(c + problem[3], maxCop);
					
					minTime[dr][dc] = Math.min(minTime[dr][dc], cur + problem[4]);
				}

			}
		}
	}
}