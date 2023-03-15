public class Solution {

    public static int solution(int alp, int cop, int[][] pro) {

        int maxAlp = pro[0][0];
        int maxCop = pro[0][1];
        for(int i=1; i<pro.length; i++) {
            if(maxAlp < pro[i][0]) maxAlp = pro[i][0];
            if(maxCop < pro[i][1]) maxCop = pro[i][1];
        }

        // 이미 목표한 알고력/코딩력을 달성했는 경우
        alp = (maxAlp < alp) ? maxAlp : alp;
        cop = (maxCop < cop) ? maxCop : cop;

        // dp에 최대값으로 채우기
        int[][] dp = new int[maxAlp+2][maxCop+2];
        for(int i=alp; i<=maxAlp; i++) {
            for(int j=cop; j<=maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;

        // dp를 이용해서 최단 시간 구하기
        for(int i=alp; i<=maxAlp; i++) {
            for(int j=cop; j<=maxCop; j++) {

                // 알고력+1 올리기
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                // 코딩력+1 올리기
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

                // 문제들로 실력 올리기
                for(int k=0; k<pro.length; k++) {

                    // 현재의 알고력과 코딩력으로 문제를 풀 수 없는 경우
                    if(i < pro[k][0] || j < pro[k][1]) continue;

                    int x = (i + pro[k][2] > maxAlp) ? maxAlp : i + pro[k][2];
                    int y = (j + pro[k][3] > maxCop) ? maxCop : j + pro[k][3];

                    dp[x][y] = Math.min(dp[x][y], dp[i][j]+pro[k][4]);
                }
            }

        }

        return dp[maxAlp][maxCop];
    }
}