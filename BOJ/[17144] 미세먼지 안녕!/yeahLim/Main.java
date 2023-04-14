import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static int[][] dust;
    static int airCleaner1, airCleaner2; // 공기청정기 위치
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        dust = new int[r][c];
        for(int i=0; i<r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++) {
                dust[i][j] = Integer.parseInt(st.nextToken());
                if(dust[i][j] == -1) {
                    airCleaner2 = i;
                }
            }
        }
        airCleaner1 = airCleaner2 - 1;

        /* t초 동안 실행 */
        while(t-- > 0) {
            spreadDust();
            purifyAir();
        }

        /* 출력 */
        System.out.println(getDustAmout());
    }

    /* 미세먼지 확산 */
    private static void spreadDust() {
        int[][] newDust = new int[r][c];
        // 공기청정기 복사
        newDust[airCleaner1][0] = -1;
        newDust[airCleaner2][0] = -1;

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                // 미세먼지가 있는 경우
                if(dust[i][j] > 0) {
                    newDust[i][j] += dust[i][j];
                    for(int k=0; k<4; k++) {
                        int x = dx[k] + i;
                        int y = dy[k] + j;

                        if(x<0 || x>=r || y<0 || y>=c) continue; // 범위 벗어날 경우
                        if(dust[x][y] == -1) continue; // 공기 청정기일 경우

                        newDust[x][y] += dust[i][j]/5;
                        newDust[i][j] -= dust[i][j]/5;
                    }
                }
            }
        }
        dust = cloneDust(newDust);
    }

    /* dust 배열 복사 */
    private static int[][] cloneDust(int[][] cpDust) {
        int[][] tmpDust = new int[r][c];
        for(int i=0; i<r; i++) {
            tmpDust[i] = cpDust[i].clone();
        }
        return tmpDust;
    }

    /* 공기 청정기 작동 */
    private static void purifyAir() {
        int[][] newDust = cloneDust(dust);

        /* 시계방향으로 작동 */
        dust[airCleaner2][1] = 0;
        // 좌 -> 우
        for(int j=2; j<c; j++) {
            dust[airCleaner2][j] = newDust[airCleaner2][j-1];
        }
        // 상 -> 하
        for(int i=airCleaner2+1; i<r; i++) {
            dust[i][c-1] = newDust[i-1][c-1];
        }
        // 우 -> 좌
        for(int j=c-2; j>=0; j--) {
            dust[r-1][j] = newDust[r-1][j+1];
        }
        // 하 -> 상
        for(int i=r-2; i>airCleaner2; i--) {
            dust[i][0] = newDust[i+1][0];
        }

        /* 반시계방향으로 작동 */
        dust[airCleaner1][1] = 0;
        // 좌 -> 우
        for(int j=2; j<c; j++) {
            dust[airCleaner1][j] = newDust[airCleaner1][j-1];
        }
        // 하 -> 상
        for(int i=airCleaner1-1; i>=0; i--) {
            dust[i][c-1] = newDust[i+1][c-1];
        }
        // 우 -> 좌
        for(int j=c-2; j>=0; j--) {
            dust[0][j] = newDust[0][j+1];
        }
        // 상 -> 하
        for(int i=1; i<airCleaner1; i++) {
            dust[i][0] = newDust[i-1][0];
        }
    }

    /* 미세먼지의 양 출력*/
    private static int getDustAmout() {
        int amount = 0;
        for(int[] dst : dust) {
            for(int d : dst) {
                if(d == -1) continue;
                amount += d;
            }
        }
        return amount;
    }
}
