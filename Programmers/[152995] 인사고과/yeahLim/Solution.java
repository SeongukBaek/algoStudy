import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanhoScore = scores[0].clone();
        
        Arrays.sort(scores, (o1, o2) -> { return (o1[0] == o2[0]) ? o1[1] - o2[1] : o2[0] - o1[0]; });  // 근무점수 내림차순, 동료점수 오름차순
        PriorityQueue<int[]> rank = new PriorityQueue<>((o1, o2) -> { return (o2[0] + o2[1]) - (o1[0] + o1[1]); });
        rank.offer(scores[0]);
        int[] maxScore = scores[0]; // 동료점수 기준 최대값
        
        /* 인센티브 받을 수 있는 사람 구하기 */
        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] >= maxScore[1]) {
                rank.offer(scores[i]);
                maxScore = scores[i].clone();
            }
            else if (scores[i][0] == wanhoScore[0] && scores[i][1] == wanhoScore[1]) {
                return -1;
            }
        }
         
        /* 석차 구하기 */
        int index = 1;
        while (!rank.isEmpty()) {
            int[] current = rank.poll();
            if (current[0] + current[1] == wanhoScore[0] + wanhoScore[1]) {
                break;
            }
            index++;
        }
        return index;
    }
}