class Solution {
    int[] deliveries;
    int[] pickups;
    int cap;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 이동 거리
        long move = 0;
        this.deliveries = deliveries;
        this.pickups = pickups;
        this.cap = cap;

        int delIdx = skipNoBoxHouse(deliveries, n-1);
        int pickIdx = skipNoBoxHouse(pickups, n-1);
        while(!isFinished(delIdx, pickIdx)){
            // 이동 거리 구하기
            move += Math.max(delIdx+1,pickIdx+1);
            // 택배 배달하기
            delIdx = carryBox(deliveries, delIdx);
            // 택배 수거하기
            pickIdx = carryBox(pickups, pickIdx);
            // 배달할 택배가 없는 집 스킵하기
            delIdx = skipNoBoxHouse(deliveries, delIdx);
            // 수거할 택배가 없는 집 스킵하기
            pickIdx = skipNoBoxHouse(pickups, pickIdx);
        }

        return move*2;
    }

    private boolean isFinished(int delIdx,int pickIdx) {
        return delIdx == 0 && pickIdx == 0 && deliveries[delIdx] == 0 && pickups[pickIdx] == 0;
    }
    // 택배를 수거하거나 배달하지 않아도 되는 집은 건너띈다.
    // 즉, 배열의 값이 0인 인덱스를 건너띈다.
    private int skipNoBoxHouse(int[] boxes,int idx){
        while(idx > 0 && boxes[idx] == 0){
            idx--;
        }
        return idx;
    }
    // 택배 상자를 수거하거나 배달한다.
    // 수거/배달를 해야하는 가장 먼 집의 위치를 반환한다.
    private int carryBox(int[] boxes,int idx) {
        // truck 변수로 현재 옮길 수 있는 택배의 양을 표시한다.
        int truck = cap;
        // 택배를 옮길 수 있고 현재 가장 먼 집이 0이상이면 택배를 옮긴다.
        while(truck > 0 && idx >= 0){
            // 수거/배달할 택배 개수보다 트럭에 더 공간이 많다면 다 트럭에 싣는다.
            if(truck > boxes[idx]){
                truck -= boxes[idx];
                boxes[idx--] = 0;
                continue;
            }
            boxes[idx] -= truck;
            truck = 0;
        }
        return idx < 0? 0: idx;
    }
}