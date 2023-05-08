import java.util.*;

class Solution {
    // 광물을 순서대로 5개씩 묶고, 그 피로도 합을 구해서 함께 저장한다. (class Group)
    // 피로도 내림차순으로 정렬
    // 다이아 곡괭이부터 정렬된 광물 그룹을 채굴
    class Group implements Comparable<Group> {
        List<String> mineralList;
        int fatigue;

        Group(List<String> mineralList, int fatigue) {
            this.mineralList = mineralList;
            this.fatigue = fatigue;
        }

        @Override
        public int compareTo(Group group) {
            return group.fatigue - this.fatigue;
        }
    }

    private Queue<Group> mineralFatigues = new PriorityQueue<>();
    private String[] minerals;
    private int allPicks;
    private final int SIZE = 5;

    public int solution(int[] picks, String[] minerals) {
        this.minerals = minerals;
        allPicks = Arrays.stream(picks).sum();

        // 광물 그룹화, 피로도 내림차순 정렬
        groupMinerals();

        int fatigues = 0;

        // 다이아 곡괭이부터 채굴 수행
        for (int index = 0; index < 3; index++) {
            int pick = picks[index];

            boolean isEnd = false;

            // 해당 곡괭이 개수만큼 수행
            for (int count = 0; count < pick; count++) {
                // 더 이상 채굴할 광물 그룹이 없는 경우
                if (mineralFatigues.isEmpty()) {
                    isEnd = true;
                    break;
                }

                Group current = mineralFatigues.poll();

                for (String mineral : current.mineralList) {
                    fatigues += computeFatigue(index, mineral);
                }
            }

            if (isEnd) {
                break;
            }
        }

        return fatigues;
    }

    private void groupMinerals() {
        int groups = minerals.length / SIZE;
        int remains = minerals.length % SIZE;

        // 곡괭이 수보다 더 많이 그룹을 나누면 안됨
        // 실제로는 채굴할 수 없는 광물 그룹인데 우선순위에 의해 채굴하게 될 수도 있음
        if (groups > allPicks) {
            groups = allPicks;
            remains = 0;
        }

        int index = 0;
        for (int count = 0; count < groups; count++) {
            groupMinerals(index, SIZE);
            index += SIZE;
        }

        // 남은 광물들 그룹으로 묶기
        groupMinerals(index, remains);
    }

    /**
     * 주어진 제한만큼 광물을 그룹핑, 피로도 계산 후 큐에 삽입
     */
    private void groupMinerals(int index, int limit) {
        List<String> mineralList = new ArrayList<>();
        int fatigue = 0;

        for (int mineral = 0; mineral < limit; mineral++) {
            if (mineral + index == minerals.length) {
                break;
            }

            String current = minerals[mineral + index];

            mineralList.add(current);
            fatigue += getFatigue(current);
        }

        mineralFatigues.add(new Group(mineralList, fatigue));
    }

    /**
     * 돌 곡괭이로 채굴하는 경우 발생하는 피로도 반환
     */
    private int getFatigue(String mineral) {
        if (mineral.equals("diamond")) {
            return 25;
        }
        if (mineral.equals("iron")) {
            return 5;
        }
        return 1;
    }

    /**
     * 곡괭이 종류에 따른 피로도 반환
     */
    private int computeFatigue(int pick, String mineral) {
        // 철 곡괭이 && 광물: 다이아몬드
        if (pick == 1 && mineral.equals("diamond")) {
            return 5;
        }

        // 돌 곡괭이 && 광물: 다이아몬드
        if (pick == 2 && mineral.equals("diamond")) {
            return 25;
        }

        // 돌 곡괭이 && 광물: 철
        if (pick == 2 && mineral.equals("iron")) {
            return 5;
        }

        // 나머지 경우
        return 1;
    }
}