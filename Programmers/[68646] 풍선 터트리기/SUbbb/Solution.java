class Solution {
    public int solution(int[] a) {
        int length = a.length;

        if (length == 1) {
            return 1;
        }

        // 각 인덱스별 왼쪽 원소의 최소값 저장
        int[] lefts = new int[length];
        // 각 인덱스별 오른쪽 원소의 최소값 저장
        int[] rights = new int[length];

        // 왼쪽 값 중 최소값
        int leftMin = a[0];
        // 오른쪽 값 중 최소값
        int rightMin = a[length - 1];

        // i일때 왼쪽 원소의 최소값 저장
        for (int index = 1; index < length - 1; index++) {
            if (leftMin > a[index]) {
                leftMin = a[index];
            }
            lefts[index] = leftMin;
        }

        // i일때 오른쪽 원소의 최소값 저장
        for (int index = length - 2; index > 0; index--) {
            if (rightMin > a[index]) {
                rightMin = a[index];
            }
            rights[index] = rightMin;
        }

        int answer = 2;
        for (int index = 1; index < length - 1; index++) {
            // 해당 풍선이 자신을 기준으로 왼쪽에 있는 최소 번호 풍선과, 오른쪽에 있는 최소 번호 풍선보다 크다면, 최후까지 남을 수 있다.
            if (a[index] > lefts[index] && a[index] > rights[index]) {
                continue;
            }
            answer++;
        }

        return answer;
    }
}