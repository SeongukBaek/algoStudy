package algo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_꼬리잡기놀이 {
    
    static class Pos{
        int x, y;

        public Pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    
    static int N, M, K;
    static int[][] map;
    static int[][] route; // 길을 표시할 배열 
    static List<Pos>[] teams; // 머리사람 위치 
    static int totalScore;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자의 크기
        M = Integer.parseInt(st.nextToken()); // 팀의 개수 
        K = Integer.parseInt(st.nextToken()); // 라운드 수 
        teams = new ArrayList[M];
        
        for(int i = 0; i < M; i++) {
            teams[i] = new ArrayList<Pos>();
        }
        
        map = new int[N][N];
        int n = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if(map[i][j] == 1) { // 만약에 머리사람이 나타나면 위치 저장해놓기
                    teams[n].add(new Pos(i, j));
                    n++;
                }
            }
        }
       
        // 각 팀의 길 찾기 
        for(int i = 0; i < M; i++) {
            findTeam(teams[i].get(0).x, teams[i].get(0).y, i);            
        }
        
        for(int k = 0; k < K; k++) { // K라운드만큼 반복 
        	
        	// 1. 팀별로 한 칸씩 이동하기 
            for(int i = 0; i < M; i++) { 
                moveTeam(teams[i]); 
            }
          
            // 2. 공 던지고 점수 계산 
            throwBall(k);
        }
        
        System.out.println(totalScore);
    }
    
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    
    static void findTeam(int x, int y, int n) {
        
        visited = new boolean[N][N];
        
        // 모든 팀원 찾아주기 
        while(true) { 
        	
            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                // 맵을 벗어나면 못감 
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }    
                
                // 이미 방문한 곳이면 못감 
                if(visited[nx][ny]) {
                    continue;
                }
                
                // 벽이면 못감, 2나 3쪽으로만 가야함 
                if(map[nx][ny] == 0 || map[nx][ny] == 4 || map[nx][ny] == 1) {
                    continue;
                }
                
                boolean check = true;
                
                if(map[nx][ny] == 3) { // 꼬리를 만났을 경우 

                	// 인접한 칸에 2가 있는지 확인하고 2가 있으면 continue --> 경로에 4가 없는 경우 
                	for(int i = 0; i < 4; i++) {
                		int nx2 = nx + dx[i];
                        int ny2 = ny + dy[i];
                        
                     // 맵을 벗어나면 못감 
                        if(nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= N) {
                            continue;
                        }    
                        
                        // 이미 방문한 곳이면 못감 
                        if(visited[nx2][ny2]) {
                            continue;
                        }
                        
                        
                        if(map[nx2][ny2] == 2) { // 만약에 몸을 만나면 
                        	check = false;
                        	break;
                        }
                	}
                }
                
                if(check == false) {
            		continue;
            	}
                
                x = nx;
                y = ny;

                break;
            }
            
            // 맵을 벗어나지 않고, 이전에 왔던 좌표가 아니고, 갈 수 있는 길이면 팀멤버이므로 추가
            teams[n].add(new Pos(x, y));
            visited[x][y] = true;
            
            if(map[x][y] == 3) {
            	break;
            }
        }
    }
    
    
    // 한 팀이 한 칸씩 이동하는 함수 
    // 이동하고 리스트에 저장된 좌표도 갱신해줘야 함 
    static void moveTeam(List<Pos> team) {
        // 1은 4가 있는 방향으로 가고, 이후 번호는 이전에 있는 위치로 한 칸씩 땡겨주면 됨 
        int check1 = 0;
        int check2 = 0;
        
        // 4가 있는 위치 찾기 
        Pos head = team.get(0);
        for(int d = 0; d < 4; d++) {
            int nx = head.x + dx[d];
            int ny = head.y + dy[d];
            
            // 맵을 벗어나면 안됨
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            
            if((map[nx][ny] == 4 && map[team.get(1).x][team.get(1).y] != 4)) {
            	check1 = 1;
            }
            
            if((map[nx][ny] == 3 && map[team.get(1).x][team.get(1).y] == 2)) {
            	check2 = 1;
            }
            
            if(check1 == 1 || check2 == 1) {
                // 팀의 머리부터 꼬리까지 돌면서 
                for(int i = 0; i < team.size(); i++) {
                    // 현재 위치
                    int cx = team.get(i).x;
                    int cy = team.get(i).y;
                    
                    if(i == team.size()-1) {
                    	if(check2 == 1) {
                    		map[nx][ny] = 3;
                    		 // 리스트에 저장된 자표도 바꿔줘야 함 
                            team.get(i).x = nx;
                            team.get(i).y = ny;
                            break;
                    	}
                    }
                    
                    // 한 칸씩 밀면서 전체적으로한 칸씩 땡겨줌 
                    int temp = map[nx][ny];
                    map[nx][ny] = map[cx][cy];
                    map[cx][cy] = temp;
                    
                    // 리스트에 저장된 자표도 바꿔줘야 함 
                    team.get(i).x = nx;
                    team.get(i).y = ny;
                    
                    nx = cx;
                    ny = cy;
                }
                return;
            }
        }
    }
    
    
    // 공 던지는 함수 --> 충돌하는 위치를 반환 
    static void throwBall(int round) {
    	int r = round / N % 4; // 방향 
    	int n =  round % N; // 몇 번째인지 
    	
    	if(r == 0) { // 왼 --> 오
    		for(int i = 0; i < N; i++) {
    			if(1 <= map[n][i] && map[n][i] <= 3) { // 사람(1,2,3)을 만날 때
    				//System.out.println(n + " " + i);
    				totalScore += getScore(n, i);
                	return;
    			}
    		}
    	}
    	
    	if(r == 1) { // 아 --> 위
    		for(int i = N-1; i >= 0; i--) {
    			if(1 <= map[i][n] && map[i][n] <= 3) { // 사람(1,2,3)을 만날 때
    				totalScore += getScore(i, n);
                	return;
    			}
    		}
    	}
    	
    	if(r == 2) { // 오 --> 왼
    		for(int i = N-1; i >= 0; i--) {
    			if(1 <= map[N - n - 1][i] && map[N - n - 1][i] <= 3) { // 사람(1,2,3)을 만날 때
    				//System.out.println("N : " + N);
    				//System.out.println(n + " " + i);
    				totalScore += getScore(N - n -1, i);
                	return;
    			}
    		}
    		
    	}
    	
    	if(r == 3) { // 위 --> 아
    		for(int i = 0; i < N; i++) {
    			if(1 <= map[i][N - n -1] && map[i][N -n-1] <= 3) { // 사람(1,2,3)을 만날 때
    				totalScore += getScore(i, N-n-1);
                	return;
    			}
    		}
    	}
    }
    
    // 무슨 팀의 몇번째 사람인지 확인해서 점수 획득 
    static int getScore(int x, int y) {
        
    	for(int i = 0; i < teams.length; i++) { // 팀
    		for(int j = 0; j < teams[i].size(); j++) { // 번호
    			Pos current = teams[i].get(j);
    			
    			if(current.x == x && current.y == y) {
    				 // 만약 좌표가 같으면 무슨 팀의 몇번 째 사람인지 알아내기
    				int score = (j+1)*(j+1);
    				reverse(i); // 해당되는 팀의 앞뒤 바꾸기 
    				return score; // 해당 사람의 번호를 제곱한 값을 점수로 얻음 
    			}
    		}
    	}
        return 0;
    }
    
    // 팀의 순서 바꾸기 
    static void reverse(int teamNum) {

    	List<Pos> temp = new ArrayList<>();
    	
    	// 새 리스트에 거꾸로 넣어주기
    	for(int i = teams[teamNum].size() -1; i >= 0; i--) { 
    		Pos p = teams[teamNum].get(i);
    		temp.add(p);
    	}
    	
    	// 뒤집은 리스트를 다시 원래 리스트에 넣어주기 
    	for(int i = 0; i < teams[teamNum].size(); i++) {
    		teams[teamNum].set(i, temp.get(i));
    	}
    	
    	// 보드 표기도 바꾸기 
    	Pos head = teams[teamNum].get(0);
    	Pos tail = teams[teamNum].get(teams[teamNum].size()-1);
    	
    	// 해당 팀의 머리 좌표를 3으로
    	map[head.x][head.y] = 1;
    	// 해당 팀의 꼬리 좌표를 1로 
    	map[tail.x][tail.y] = 3;
    }
}


