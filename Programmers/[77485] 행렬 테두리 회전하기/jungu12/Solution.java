class Solution {
    static int[][] arr;
    static int[][] queries;
    static int rows, columns;
    static int[] answer;
    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        arr = new int[rows][columns];
        this.queries = queries;
        
        //행렬에 값 삽입
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = i * columns + j + 1;
            }
        }
        
        //회전의 개수 만큼 회전 수행
        for (int i = 0; i < queries.length; i++) {
            rotateArr(i);
        }
        
        return answer;
    }
    
    void rotateArr(int idx) {
        int min = Integer.MAX_VALUE;
        int minRow = queries[idx][0] - 1;
        int minCol = queries[idx][1] - 1;
        int maxRow = queries[idx][2] - 1;
        int maxCol = queries[idx][3] - 1;
        
        int start = arr[minRow][minCol];
        for (int i = minRow; i < maxRow; i++) {
            arr[i][minCol] = arr[i + 1][minCol];
            min = Math.min(min, arr[i][minCol]);
        }
        
        for (int i = minCol; i < maxCol; i++) {
            arr[maxRow][i] = arr[maxRow][i + 1];
            min = Math.min(min, arr[maxRow][i]);
        }
        
        for (int i = maxRow; i > minRow; i--) {
            arr[i][maxCol] = arr[i - 1][maxCol];
            min = Math.min(min, arr[i][maxCol]);
        }
        
        for (int i = maxCol; i > minCol + 1; i--) {
            arr[minRow][i] = arr[minRow][i - 1];
            min = Math.min(min, arr[minRow][i]);
        }
        
        arr[minRow][minCol + 1] = start;
        min = Math.min(min, start);
        
        answer[idx] = min;
    }
}