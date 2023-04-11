import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] room;
    private static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int R, C, T;
    private static int cleaner1X, cleaner2X;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        for (int r = 0; r < R; r++) {
        	st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
            	int number = Integer.parseInt(st.nextToken());
            	room[r][c] = number;
                if (number == -1 && cleaner1X == 0) {
                    cleaner1X = r;
                    cleaner2X = r + 1;
                }
            }
        }

        System.out.println(goodByeDust());
    }
    
    private static int goodByeDust() {
        while(T-- > 0) {
            spreadDust();
            runCleaner();
        }
        return countDust();
    }

    private static void spreadDust() {
        int[][] tmp = new int[R][C];
        
        for (int r = 0; r < R; r++) {
        	for (int c = 0; c < C; c++) {
        		if (room[r][c] < 0) {
        			continue;
        		}
        		
        		int dust = room[r][c];
        		int spreadAmount = dust / 5;
                int spreadCount = 0;

                for (int[] move : moves) {
                	int nx = r + move[0];
                    int ny = c + move[1];

                    if (!isIn(nx, ny) || room[nx][ny] == -1) {
                    	continue;
                    }

                    // 사방에 미세먼지를 뿌리고,
                    tmp[nx][ny] += spreadAmount;
                    spreadCount++;
                }
                
                // 현 위치의 미세먼지 양을 수정
                tmp[r][c] += dust - (spreadAmount * spreadCount);
        	}
        }

        // 한 번에 미세먼지 이동 정보 수정
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
            	if (room[r][c] == -1) {
            		continue;
            	}
                room[r][c] = tmp[r][c];
            }
        }
    }
    
    private static boolean isIn(int x, int y) {
    	return x >= 0 && x < R && y >= 0 && y < C;
    }

    private static void runCleaner() {
		int index = 0;
		for (index = cleaner1X - 2; index >= 0; index--) {
			room[index + 1][0] = room[index][0];
		}
		for (index = 1; index < C; index++) {
			room[0][index - 1] = room[0][index];
		}
		for (index = 1; index <= cleaner1X; index++) {
			room[index - 1][C - 1] = room[index][C - 1];
		}
		for (index = C - 2; index > 0; index--) {
			room[cleaner1X][index + 1] = room[cleaner1X][index];
		}

		for (index = cleaner2X + 2; index < R; index++) {
			room[index - 1][0] = room[index][0];
		}
		for (index = 1; index < C; index++) {
			room[R - 1][index - 1] = room[R - 1][index];
		}
		for (index = R - 2; index >= cleaner2X; index--) {
			room[index + 1][C - 1] = room[index][C - 1];
		}
		for (index = C - 2; index > 0; index--) {
			room[cleaner2X][index + 1] = room[cleaner2X][index];
		}

        room[cleaner1X][1] = room[cleaner2X][1] = 0;
    }

    private static int countDust() {
        int count = 0;
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (room[r][c] > 0) {
                	count += room[r][c];
                }
            }
        }
        
        return count;
    }
}