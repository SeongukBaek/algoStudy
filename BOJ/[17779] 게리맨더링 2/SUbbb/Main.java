import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int[][] map;
	private static boolean[][] isFifth;
	private static int N;
	private static int minDifference = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    	StringTokenizer st;
        
        map = new int[N + 1][N + 1];
        
        for (int x = 1; x <= N; x++) {
        	st = new StringTokenizer(br.readLine());
        	for (int y = 1; y <= N; y++) {
        		map[x][y] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for (int x = 1; x <= N; x++) {
        	for (int y = 1; y <= N; y++) {
        		for (int d1 = 1; d1 < N; d1++) {
                	for (int d2 = 1; d2 < N; d2++) {
                		if (x + d1 + d2 > N || 1 > y - d1 || y - d1 >= y || y + d2 <= y || y + d2 > N) {
                			continue;
                		}
                		divideVotingZone(x, y, d1, d2);
                	}
                }
        	}
        }
        
        System.out.println(minDifference);
    }
    
    /**
     * 각 선거구의 인원을 구하고, 최대와 최소의 차이를 구해 정답을 갱신한다.
     * */
    private static void divideVotingZone(int x, int y, int d1, int d2) {
    	List<Integer> populations = new ArrayList<>();
    	isFifth = new boolean[N + 1][N + 1];

    	populations.add(fifthZone(x, y, d1, d2));
    	populations.add(firstZone(x, y, d1));
    	populations.add(secondZone(x, y, d2));
    	populations.add(thirdZone(x, y, d1, d2));
    	populations.add(fourthZone(x, y, d1, d2));
    	
    	minDifference = Math.min(minDifference, Collections.max(populations) - Collections.min(populations));
    }
    
    /**
     * 첫번째 선거구의 선거 인원을 구한다.
     * */
    private static int firstZone(int x, int y, int d1) {
    	int sum = 0;
    	for (int r = 1; r < x + d1; r++) {
    		for (int c = 1; c <= y; c++) {
    			if (isFifth[r][c]) {
    				break;
    			}
    			sum += map[r][c];
    		}
    	}
    	return sum;
    }
    
    /**
     * 두번째 선거구의 선거 인원을 구한다.
     * */
    private static int secondZone(int x, int y, int d2) {
    	int sum = 0;
    	for (int r = 1; r <= x + d2; r++) {
    		for (int c = y + 1; c <= N; c++) {
    			if (isFifth[r][c]) {
    				continue;
    			}
    			sum += map[r][c];
    		}
    	}
    	return sum;
    }
    
    /**
     * 세번째 선거구의 선거 인원을 구한다.
     * */
    private static int thirdZone(int x, int y, int d1, int d2) {
    	int sum = 0;
    	for (int r = x + d1; r <= N; r++) {
    		for (int c = 1; c < y - d1 + d2; c++) {
    			if (isFifth[r][c]) {
    				break;
    			}
    			sum += map[r][c];
    		}
    	}
    	return sum;
    }
    
    /**
     * 네번째 선거구의 선거 인원을 구한다.
     * */
    private static int fourthZone(int x, int y, int d1, int d2) {
    	int sum = 0;
    	for (int r = x + d2 + 1; r <= N; r++) {
    		for (int c = y - d1 + d2; c <= N; c++) {
    			if (isFifth[r][c]) {
    				continue;
    			}
    			sum += map[r][c];
    		}
    	}
    	return sum;
    }
    
    /**
     * 다섯번째 선거구의 경계선을 긋고, 선거 인원을 구한다.
     * */
    private static int fifthZone(int x, int y, int d1, int d2) {
    	int sum = 0;
    	int r = 0;
    	int c = 0;
    	
    	// 경계선을 먼저 그어둔다.
    	for (r = x, c = y; r <= x + d1 && c >= y - d1; r++, c--) {
    		sum += map[r][c];
    		isFifth[r][c] = true;
    	}
    	for (r = x + 1, c = y + 1; r <= x + d2 && c <= y + d2; r++, c++) {
    		sum += map[r][c];
    		isFifth[r][c] = true;
    	}
    	for (r = x + d1 + 1, c = y - d1 + 1; r <= x + d1 + d2 && c <= y - d1 + d2; r++, c++) {
    		sum += map[r][c];
    		isFifth[r][c] = true;
    	}
    	for (r = x + d2 + 1, c = y + d2 - 1; r < x + d2 + d1 && c >= y + d2 - d1; r++, c--) {
    		sum += map[r][c];
    		isFifth[r][c] = true;
    	}
    	
    	// 경계선 내부가 다섯번째 선거구임을 저장
    	for (r = x + 1; r < x + d1 + d2; r++) {
    		boolean isStart = false;
    		for (c = 1; c <= N; c++) {
    			if (isStart && !isFifth[r][c]) {
    				sum += map[r][c];
    				isFifth[r][c] = true;
    				continue;
    			}
    			
    			if (isStart && isFifth[r][c]) {
    				break;
    			}
    			
    			if (isFifth[r][c]) {
    				isStart = true;
    			}
    		}
    	}
    	
    	return sum;
    }
}   