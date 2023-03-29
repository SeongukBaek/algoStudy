import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int n, appleCnt, orderCnt;
    static List<Current> snake = new LinkedList<>();
    static boolean[][] isApple;
    static List<Order> orders = new ArrayList<>();
    static class Current {
        int x, y;
        char direction;
        public Current(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Order {
        int time;
        char direction;
        public Order(int time, char dir) {
            this.time = time;
            this.direction = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        
    	/* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        isApple = new boolean[n][n];
        appleCnt = Integer.parseInt(br.readLine());
        for(int i=0; i<appleCnt; i++) {
            String[] infor = br.readLine().split(" ");
            isApple[Integer.parseInt(infor[0])-1][Integer.parseInt(infor[1])-1] = true;
        }
        orderCnt = Integer.parseInt(br.readLine());
        for(int i=0; i<orderCnt; i++) {
            String[] infor = br.readLine().split(" ");
            orders.add(new Order(Integer.parseInt(infor[0]), infor[1].charAt(0)));
        }
        
        
        System.out.println(playDummy());
    }
    
    /* 더미 게임 실행 */
    private static int playDummy() {
    	int[] dx = {-1, 0, 1, 0};
    	int[] dy = {0, 1, 0, -1};
    	int x = 0, y = 0; // (0, 0)에서 시작
    	int head = 1; // head 오른쪽으로 초기화
    	int time = 0; // 시간
    	int orderIndex = 0; 
    	snake.add(new Current(x, y));
    	
        while(true) {
        	
        	// 시간 재기
        	time++;      	
        	
        	int nx = x + dx[head];
	        int	ny = y + dy[head];
        	
	        // 종료 조건
        	if(checkFinish(nx, ny)) break;
        	
        	snake.add(new Current(nx, ny));
        	
        	// 사과가 있을때
        	if(isApple[nx][ny]) isApple[nx][ny] = false;
        	// 없을때
        	else snake.remove(0);
        	
        	
        	// 회전 방향
        	if(orderIndex < orderCnt && time == orders.get(orderIndex).time) {
        		
        		// 왼쪽으로 90도
        		if(orders.get(orderIndex).direction == 'L') {
        			head -= 1;
        			if(head == -1) head = 3;
        		}
        		// 오른쪽으로 90도
        		else head = (head + 1) % 4;
        		
        		orderIndex++;
        	}
        	
        	x = nx;
        	y = ny;
        }
        return time;
    }

    /* 종료조건 : 범위 벗어나거나 몸통 만났을때 */
	private static boolean checkFinish(int nx, int ny) {
		if(nx < 0 || nx >= n || ny < 0 || ny >= n) return true;	
				
		for(int i=0; i<snake.size(); i++) {
    		if(snake.get(i).x == nx && snake.get(i).y == ny) return true;    		  		
    	}
		
    	return false;
	}
	

}