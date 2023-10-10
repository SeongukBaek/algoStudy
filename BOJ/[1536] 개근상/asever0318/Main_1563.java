import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static final int D = 1000000;
    static int[][][] attend;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        attend = new int[N+1][2][3]; // [일][지각][결석]

        attendance();
        System.out.println((attend[N][0][0] + attend[N][0][1] + attend[N][0][2] + attend[N][1][0] + attend[N][1][1] + attend[N][1][2]) % D);
    }

    static void attendance(){

        attend[1][0][0] = 1;
        attend[1][0][1] = 1;
        attend[1][1][0] = 1;

        for(int i = 2; i <= N; i++){
            // 1. i일까지 지각을 안한 경우
            // i번째 날에 지각 0, 결석 0일일 경우
            attend[i][0][0] = (attend[i-1][0][0] + attend[i-1][0][1] + attend[i-1][0][2]) % D;
            attend[i][0][1] = attend[i-1][0][0] % D; // i번째 날에 결석을 연속 1번 하려면 전날에는 결석을 했으면 안됨
            attend[i][0][2] = attend[i-1][0][1] % D; // i번째 날에 결석을 연속 2번 하려면 전날에 결석을 연속 1번 했어야 함

            // 2. i일까지 지각을 1번 한 경우
            attend[i][1][0] = (attend[i-1][0][0] + attend[i-1][0][1] + attend[i-1][0][2] + attend[i-1][1][0] + attend[i-1][1][1] + attend[i-1][1][2]) % D;
            attend[i][1][1] = attend[i-1][1][0] % D; // 지각과 결석을 동시에 못함주의 -> 결석만 고려
            attend[i][1][2] = attend[i-1][1][1] % D;
        }
    }
}
// 개근상 받을 수 없는 사람 : 1. 지각 2번 이상, 2. 결석 연속 세 번이상
// result => 개근상을 받을 수 있는 출결정보의 개수
