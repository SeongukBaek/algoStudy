import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Operation {
		int r;
		int c;
		int s;
		Operation(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	private static int N;
	private static int M;
	private static int K;
	private static int[][] map;
	private static int[][] copyMap;
	private static List<Operation> operations = new ArrayList<>();
	private static int min = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	map = new int[N][M];
    	
    	for (int x = 0; x < N; x++) {
    		st = new StringTokenizer(br.readLine());
    		for (int y = 0; y < M; y++) {
    			map[x][y] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for (int k = 0; k < K; k++) {
    		st = new StringTokenizer(br.readLine());
    		operations.add(new Operation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    	}
    	
    	makeOrders(new int[K], new boolean[K], 0);
    	
    	System.out.println(min);
    }
    
    /**
     * 연산 순서를 생성
     * @param int[] 만들어지는 순서
     * @param boolean[] 특정 연산의 포함 여부 저장
     * @param int 만들어진 순서의 개수
     * */
    private static void makeOrders(int[] order, boolean[] isSelected, int size) {
    	if (size == K) {
    		copy();
        	operateByOrders(order);
        	min = Math.min(min, computeRowSum());
        	return;
    	}
    	
    	for (int i = 0; i < K; i++) {
    		if (isSelected[i]) {
    			continue;
    		}
    		order[size] = i;
    		isSelected[i] = true;
    		makeOrders(order, isSelected, size + 1);
    		isSelected[i] = false;
    	}
    }
    
    /**
     * 주어진 연산 순서에 따라 회전을 수행
     * @param int[] 만들어진 연산 순서
     * */ 
    private static void operateByOrders(int[] order) {
    	for (int o : order) {
    		rotate(operations.get(o));
    	}
    }
    
    /**
     * 주어진 회전 연산을 수행
     * @param Operation 수행해야 할 회전 연산 정보
     * */
    private static void rotate(Operation operation) {
    	int r = operation.r;
    	int c = operation.c;
    	int s = operation.s;
    	
    	while (s > 0) {
    		int startX = r - s - 1;
        	int startY = c - s - 1;
        	int endX = r + s - 1;
        	int endY = c + s - 1;
        	
        	int lastValue = copyMap[startX][startY];
        	
        	for (int x = startX + 1; x <= endX; x++) {
        		copyMap[x - 1][startY] = copyMap[x][startY];
        	}
        	
        	for (int y = startY + 1; y <= endY; y++) {
        		copyMap[endX][y - 1] = copyMap[endX][y];
        	}

        	for (int x = endX - 1; x >= startX; x--) {
        		copyMap[x + 1][endY] = copyMap[x][endY];
        	}

        	for (int y = endY - 1; y > startY; y--) {
        		copyMap[startX][y + 1] = copyMap[startX][y];
        	}

        	copyMap[startX][startY + 1] = lastValue;
        	s--;
    	}
    }
    
    /**
     * 기존의 배열을 유지하기 위해 기존 배열을 복사한 배열을 생성
     * */
    private static void copy() {
    	copyMap = new int[N][M];
    	for (int x = 0; x < N; x++) {
    		for (int y = 0; y < M; y++) {
    			copyMap[x][y] = map[x][y];
    		}
    	}
    }
    
    /**
     * 배열의 각 행들의 합을 구하고, 가장 작은 행의 합 반환
     * @return int 가장 작은 행의 합
     * */
    private static int computeRowSum() {
    	int minSum = Integer.MAX_VALUE;
    	for (int x = 0; x < N; x++) {
    		int sum = 0;
    		for (int y = 0; y < M; y++) {
        		sum += copyMap[x][y];
        	}
    		minSum = Math.min(minSum, sum);
    	}
    	return minSum;
    }
}