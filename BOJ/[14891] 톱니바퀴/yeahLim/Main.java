import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] gear = new int[4][8];
    static int[][] tmpGear = new int[4][8]; 

    public static void main(String[] args) throws IOException {

        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            String tmp = br.readLine();
            for(int j=0; j<8; j++) {
                gear[i][j] = tmp.charAt(j) - '0';
            }
        }
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            String[] tmp = br.readLine().split(" ");
            checkPoles(Integer.parseInt(tmp[0])-1, Integer.parseInt(tmp[1]));
        }

        /* 출력 */
        System.out.println(getScore());
    }

    /* 같은/다른 극인지 확인하기 */
    static void checkPoles(int num, int dir) {
        int[] rotated; rotated = new int[4];
        rotated[num] = dir;

        // 왼쪽 톱니바퀴 확인
        for(int i=num; i>0; i--) {
            if(gear[i][6] != gear[i-1][2]) {
                rotated[i-1] = rotated[i]*-1;
            }
        }

        // 오른쪽 톱니바퀴 확인
        for(int i=num; i<3; i++) {
            if(gear[i][2] != gear[i+1][6]) {
               rotated[i+1] = rotated[i]*-1;
            }
        }

        // 극이 다른 톱니바퀴만 회전
        for(int i=0; i<4; i++) {
            if(rotated[i] == 0) continue;
            rotateGear(i, rotated[i]);
        }
    }

    /* 톱니바퀴 회전시키기 */
    static void rotateGear(int num, int dir) {

        // 시계 방향
        if(dir == 1) {
            tmpGear[num][0] = gear[num][7];
            for(int i=1; i<8; i++){
                tmpGear[num][i] = gear[num][i-1];
            }
        }

        // 반시계 방향
        else {
            for(int i=0; i<7; i++){
                tmpGear[num][i] = gear[num][i+1];
            }
            tmpGear[num][7] = gear[num][0];
        }

        gear[num] = tmpGear[num].clone(); // 배열 복사
    }

    /* 최종 톱니바퀴 상태 구하기 */
    static int getScore() {
        int score = 0;
        for(int i=0; i<4; i++) {
            if(gear[i][0] == 1) {
                score += Math.pow(2, i);
            }
        }
        return score;
    }
}
