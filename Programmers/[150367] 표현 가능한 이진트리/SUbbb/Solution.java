class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int index = 0; index < numbers.length; index++) {
            answer[index] = canRepresent(numbers[index]) ? 1 : 0;
        }

        return answer;
    }

    /**
     * 해당 숫자가 이진트리로 표현되는지 여부를 반환
     */
    private boolean canRepresent(long number) {
        StringBuilder toProcessBinary = toFullyBinary(Long.toBinaryString(number));
        int length = toProcessBinary.length();

        // 중간 인덱스 구하고, 이를 기준으로 나눔
        // 중간 인덱스는 해당 트리의 루트 노드를 의미
        int mid = length / 2;
        String left = toProcessBinary.substring(0, mid);
        String right = toProcessBinary.substring(mid + 1);

        // 루트는 0일 수 없기에 바로 반환
        if (toProcessBinary.charAt(mid) == '0') {
            return false;
        }

        return isBinaryTree(left) & isBinaryTree(right);
    }

    /**
     * 해당 문자열이 포화 이진 트리가 되도록 맨 앞에 0을 추가
     */ 
    private StringBuilder toFullyBinary(String binary) {
        int length = binary.length();
        return new StringBuilder(binary).insert(0, "0".repeat(nearOdd(length) - length));
    }

    /**
     * 해당 문자열이 이진트리로 표현가능한지 확인
     */
    private boolean isBinaryTree(String binary) {
        // 트리가 없다면 true 반환
        if (binary.length() == 0) {
            return true;
        }

        int length = binary.length();

        // 중간 인덱스 = 루트 인덱스 구하기
        int mid = length / 2;
        String left = binary.substring(0, mid);
        String right = binary.substring(mid + 1);

        // 루트가 0이라면 그 왼쪽, 오른쪽 서브 트리 모두 0이어야 한다. 즉 1이 없어야 한다.
        // 더미 노드의 자식 노드에 실제 값이 있는 노드가 올 수 없기 때문
        if (binary.charAt(mid) == '0') {
            return isAllZero(left) & isAllZero(right);
        }

        // 그게 아니라면 양 서브 트리 모두 이진트리인지 확인
        return isBinaryTree(left) & isBinaryTree(right);
    }

    private int nearOdd(int number) {
        // 무조건 아래의 홀수들로 길이를 변경
        // 3, 7, 15, 31, ...
        int leaves = 1;
        int increase = 1;
        while (leaves < number) {
            increase *= 2;
            leaves += increase;
        }

        return leaves;
    }

    private boolean isAllZero(String binary) {
        return !binary.contains("1");
    }
}