class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        int targetAlp = 0;
        int targetCop = 0;

        // 목표 알고력과 코딩력 찾기
        for (int[] problem : problems) {
            targetAlp = Math.max(problem[0], targetAlp);
            targetCop = Math.max(problem[1], targetCop);
        }

        // 이미 주어진 능력이 목표치 이상인 경우는 바로 반환
        if (targetAlp <= alp && targetCop <= cop) {
            return 0;
        }

        // 이미 알고력이나 코딩력 중 목표치를 달성한 것이 있다면 갱신
        if (alp >= targetAlp){
            alp = targetAlp;
        }
        if (cop >= targetCop){
            cop = targetCop;
        }

        int[][] dp = new int[targetAlp + 2][targetCop + 2];

        for (int i = alp; i <= targetAlp; i++) {
            for (int j = cop; j <= targetCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        // alp, cop부터 targetAlp, targetCop까지 최소 시간을 갱신
        for (int currentAlg = alp; currentAlg <= targetAlp; currentAlg++) {
            for (int currentCop = cop; currentCop <= targetCop; currentCop++) {
                // 먼저 공부로 갱신할 수 있는 알고력과 코딩력 계산
                // 알고력 늘리기
                dp[currentAlg + 1][currentCop] = Math.min(dp[currentAlg + 1][currentCop], dp[currentAlg][currentCop] + 1);
                // 코딩력 늘리기
                dp[currentAlg][currentCop + 1] = Math.min(dp[currentAlg][currentCop + 1], dp[currentAlg][currentCop] + 1);

                // 이후 문제를 풀어보기
                for (int[] problem : problems) {
                    // 해당 문제를 풀 수 없는 경우 패스
                    if (currentAlg < problem[0] || currentCop < problem[1]) {
                        continue;
                    }

                    // 해당 문제를 풀어서 갱신되는 알고력과 코딩력
                    int newAlp = currentAlg + problem[2];
                    int newCop = currentCop + problem[3];
                    // 이전 문제를 푸는데 걸린 최소 시간에 현재 문제를 푸는 데 걸리는 시간을 더한 값
                    int newCost = dp[currentAlg][currentCop] + problem[4];

                    // 문제를 품으로써 얻는 능력이 목표치보다 큰 경우
                    if (newAlp > targetAlp && newCop > targetCop) {
                        // 목표치를 달성하는데 걸리는 최소 시간을 갱신
                        dp[targetAlp][targetCop] = Math.min(dp[targetAlp][targetCop], newCost);
                    }
                    // 알고력만 큰 경우
                    else if (newAlp > targetAlp) {
                        dp[targetAlp][newCop] = Math.min(dp[targetAlp][newCop], newCost);
                    }
                    // 코딩력만 큰 경우
                    else if (newCop > targetCop) {
                        dp[newAlp][targetCop] = Math.min(dp[newAlp][targetCop], newCost);
                    }
                    // 알고력 코딩력 둘 다 목표치 이하인 경우
                    else {
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], newCost);
                    }
                }
            }
        }

        return dp[targetAlp][targetCop];
    }
}