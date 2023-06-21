import java.util.*;

class Solution {
    private final int SIZE = 50;
    // 각 셀별 값
    private final String[][] values = new String[SIZE][SIZE];
    // 각 셀별 부모 좌표 번호
    private final int[][] parents = new int[SIZE][SIZE];
    private final List<String> answer = new ArrayList<>();

    public String[] solution(String[] commands) {
        init();

        for (String command : commands) {
            processOption(command.split(" "));
        }

        return answer.toArray(String[]::new);
    }

    private void init() {
        int number = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                parents[r][c] = number++;
            }
        }
    }

    private void processOption(String[] info) {
        String option = info[0];

        if ("PRINT".equals(option)) {
            answer.add(print(Integer.parseInt(info[1]) - 1, Integer.parseInt(info[2]) - 1));
            return;
        }

        if ("UPDATE".equals(option)) {
            if (info.length == 4) {
                // update()
                int r = Integer.parseInt(info[1]) - 1;
                int c = Integer.parseInt(info[2]) - 1;

                String value = info[3];

                update(r, c, value);
            }

            if (info.length == 3) {
                // updateAll()
                updateAll(info[1], info[2]);
            }
        }

        if ("MERGE".equals(option)) {
            int r1 = Integer.parseInt(info[1]) - 1;
            int c1 = Integer.parseInt(info[2]) - 1;
            int r2 = Integer.parseInt(info[3]) - 1;
            int c2 = Integer.parseInt(info[4]) - 1;

            merge(r1, c1, r2, c2);
        }

        if ("UNMERGE".equals(option)) {
            int r = Integer.parseInt(info[1]) - 1;
            int c = Integer.parseInt(info[2]) - 1;

            unmerge(r, c);
        }
    }

    private String print(int r, int c) {
        // 부모 좌표의 값 가져오기
        String value = getValue(parents[r][c]);
        if (value == null) {
            return "EMPTY";
        }
        return value;
    }

    private void update(int r, int c, String value) {
        // 해당 좌표의 부모 좌표의 값을 변경
        int number = parents[r][c];

        values[number / SIZE][number % SIZE] = value;
    }

    private void updateAll(String original, String target) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (values[r][c] == null || !values[r][c].equals(original)) {
                    continue;
                }

                update(r, c, target);
            }
        }
    }

    private void merge(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2 || parents[r1][c1] == parents[r2][c2]) {
            return;
        }

        int parent1 = parents[r1][c1];
        int parent2 = parents[r2][c2];

        // 인접한 경우와 그렇지 않은 경우를 나눌 필요가 없지 않을까
        // parent1에 해당하는 값이 null이면, parent2에 해당하는 값을 가진다.
        if (getValue(parent1) == null) {
            updateParents(parent1, parent2);
            values[r1][c1] = null;
        } else {
            updateParents(parent2, parent1);
            values[r2][c2] = null;
        }
    }

    private void updateParents(int original, int target) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (parents[r][c] == original) {
                    parents[r][c] = target;
                }
            }
        }
    }

    private void unmerge(int mr, int mc) {
        int parent = parents[mr][mc];
        String value = getValue(parent);

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (parents[r][c] == parent) {
                    parents[r][c] = r * SIZE + c;
                    values[r][c] = null;
                }
            }
        }

        values[mr][mc] = value;
    }

    /**
     * 번호에 해당하는 좌표의 값 반환
     */
    private String getValue(int number) {
        return values[number / SIZE][number % SIZE];
    }
}