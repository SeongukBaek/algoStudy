class Solution {

    private int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        init(rows, columns);

        int index = 0;
        for (int[] query : queries) {
            answer[index++] = rotation(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
        }

        return answer;
    }

    /**
     * 배열 초기화
     */
    private void init(int rows, int columns) {
        map = new int[rows][columns];
        int num = 1;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                map[row][col] = num++;
            }
        }
    }

    private int rotation(int x1, int y1, int x2, int y2) {
        int w = y2 - y1;
        int h = x2 - x1;
        int value = map[x1][y1];
        int min = value;

        // 왼쪽 줄 이동
        for (int index = x1 + 1; index <= x1 + h; index++) {
            int moveN = map[index][y1];
            if (min > moveN) {
                min = moveN;
            }
            map[index - 1][y1] = moveN;
        }

        // 아랫 줄 이동
        for (int index = y1 + 1; index <= y1 + w; index++) {
            int moveN = map[x2][index];
            if (min > moveN) {
                min = moveN;
            }
            map[x2][index - 1] = moveN;
        }

        // 오른쪽 줄 이동
        for (int index = x2 - 1; index >= x1; index--) {
            int moveN = map[index][y2];
            if (min > moveN) {
                min = moveN;
            }
            map[index + 1][y2] = moveN;
        }

        // 윗 줄 이동
        for (int index = y2 - 1; index > y1; index--) {
            int moveN = map[x1][index];
            if (min > moveN) {
                min = moveN;
            }
            map[x1][index + 1] = moveN;
        }

        map[x1][y1 + 1] = value;

        return min;
    }
}