import java.io.*;
import java.util.StringTokenizer;

// [2931] 가스관
public class Main_2931 {
   
   static int R, C;
   static char[][] pipeMap;
   static int[] M, Z, item; // 가스관 넣은 위치 
   static int[] dx = {-1, 0, 1, 0};
   static int[] dy = {0, 1, 0, -1};
   static boolean[][] visited;
   static char[] pShape = {'|', '-', '+', '1', '2', '3', '4'}; // 가스관 모양 
   static boolean pCheck, findResult;
   static char selected;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      M = new int[2]; // 모스크바 좌표
      Z = new int[2]; // 자그레브 좌표 
      pipeMap = new char[R][C];
      visited = new boolean[R][C];
      item = new int[2];
      
      // 입력 받기 
      for(int i = 0; i < R; i++) {
         pipeMap[i] = br.readLine().toCharArray();
         for(int j = 0; j < C; j++) {
            if(pipeMap[i][j] == 'M') {
               M[0] = i;
               M[1] = j;
            }
            
            if(pipeMap[i][j] == 'Z') {
               Z[0] = i;
               Z[1] = j;
            }
         }
      }
      
      findPipe(M); // 시작지점 좌표 가지고 넘어감 
      
      br.close();
   }
   
   
   // 시작점에서 갈 수 있는 곳 찾아서 dfs 타고 가기 
   static void findPipe(int[] start) {
      
	  // 모스크바 좌표 
      int x = start[0];
      int y = start[1];
      
      visited[x][y] = true;
      
      // M을 기준으로 4방향 중 파이프가 있는 곳 
      // 그 방향으로 가다가 파이프를 만나면 방향 바뀜
      // '.'을 만나면 파이프 넣어보기 
      for(int d = 0; d < 4; d++) {
    	  
    	  if(findResult == true) {
    		  return;
    	  }
         
         int nx = x + dx[d];
         int ny = y + dy[d];
         
         if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
            continue;
         }

         // 방향을 가지고 dfs로 넘어감 
         dfs(nx, ny, d, pipeMap[nx][ny]);
      }
   }
   
   static void dfs(int x, int y, int d, char c) {
      
	   // 답을 찾았으면 더 안봐도 됨 
	   if(findResult == true) {
		   return;
	   }
	   
	  if(x == Z[0] && y == Z[1]) { // 목적지에 도착하고 
		  if(pCheck == true) { // 파이프 하나를 사용했으면 
			  System.out.println((item[0]+1)+" "+(item[1]+1)+" "+selected);
			  findResult = true; // 답을 찾았으면 true 표시
			  return;
		  }
	  }
		  
      // 파이프 모양과 현재 방향에 따라 다음 방향 설정 
      int nd = direction(d, c);
      
      if(nd == -1) { // 빈 공간이면 return
    	  return;
      }
      
      int nx = x + dx[nd]; 
      int ny = y + dy[nd];
      
      if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
         return;
      }
      
      if(pipeMap[nx][ny] == '.' && pCheck == false) { // 만약 도착한 방향이 빈 공간이면, 파이프 아직 안썼으면
         for(int i = 0; i < 7; i++) { // 7개 파이프 넣어보기 
        	 pCheck = true; // 파이프 사용 표시하고 
        	 selected = pShape[i]; // 선택한 파이프 
        	 item[0] = nx; // 파이프 넣는 곳 좌표 
        	 item[1] = ny;
        	 
        	 visited[nx][ny] = true;
        	 pipeMap[nx][ny] = selected;
        	 dfs(nx, ny, nd, pShape[i]);
        	 visited[nx][ny] = false;
        	 pipeMap[nx][ny] = '.';
        	 pCheck = false; // 빠져나오면 다시 사용 x로 표시 
         }
      }
      else { // 빈 공간이 아니면
         if(!visited[nx][ny]) { // 방문하지 않은 곳이면 
            visited[nx][ny] = true;
            dfs(nx, ny, nd, pipeMap[nx][ny]);
            visited[nx][ny] = false;
         }
         else { // 방문 한 곳이면 ('+' 모양 때문에 방문한 곳 또 방문할 수 있음)
        	 dfs(nx, ny, nd, pipeMap[nx][ny]); // 그냥 지나가도 됨 
         }
      }
   }
   
   // 파이프 모양에 따른 방향 전환 
   static int direction(int d, char p) {
      if(p == '|') {
         if(d == 0 || d == 2) { // 이때 방향이 위거나 아래이면 그대로
            return d;
         }
      }else if(p == '-') {
         if(d == 1 || d == 3) {
            return d;
         }
      }else if(p == '+') {
         return d;
      }else if(p == '1') {
         if(d == 0) {
            return 1;
         }else if(d == 3) {
            return 2;
         }
      }else if(p == '2') {
         if(d == 2) {
            return 1;
         }else if(d == 3) {
            return 0;
         }
      }else if(p == '3') {
         if(d == 1) {
            return 0;
         }else if(d == 2) {
            return 3;
         }
      }else if(p == '4') {
         if(d == 1) {
            return 2;
         }else if(d == 0) {
            return 3;
         }
      }
      
      return -1; // 빈 공간 '.'
   }
}