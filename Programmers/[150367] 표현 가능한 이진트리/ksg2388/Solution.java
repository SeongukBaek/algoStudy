class Solution {
    public boolean[] target; //2진법으로 변환한 배열 (좌측 0 패딩 포함
    public int result;

    //Root 부터 탐색
    public void solve(int s, int e, boolean isEnd) {
        int mid = (s + e) / 2;
        boolean cur = target[mid];
        //중간에 0이 나왔는데 현재 노드가 1이라면
        if (isEnd && cur) {
            result = 0;
            return;
        }
        //마지막 노드가 아닐 경우, 계속 진행
        if (s != e) {
            solve(s, mid-1, !cur);
            solve(mid+1, e, !cur);
        }
    }

    public int[] solution(long[] numbers) {
        System.out.println((int)Math.floor(Math.log(4) / Math.log(2)) + 1);
        int[] res = new int[numbers.length];
        for (int ind = 0; ind < numbers.length; ind++) {
            result = 1;
            long num = numbers[ind];
            //2진법 변환한 target 배열 생성
            //해당 수의 2진법 길이 계산
            int len = (int)Math.floor(Math.log(num) / Math.log(2)) + 1;
            //해당 수의 포화 이진트리 길이 계산
            int exp = 1;
            int treeLen = 0;
            while(true) {
                treeLen = (int)Math.pow(2, exp++) - 1;
                if (treeLen >= len) break;
            }

            target = new boolean[treeLen];
            int i = treeLen - 1;
            while(true) {
                long div = num / 2;
                long mod = num % 2;
                num = div;
                target[i--] = (mod == 1);
                if (div == 1) {
                    target[i] = true;
                    break;
                } else if (div == 0)
                    break;
            }
            solve(0, treeLen - 1, false);
            res[ind] = result;
        }
        return res;
    }
}