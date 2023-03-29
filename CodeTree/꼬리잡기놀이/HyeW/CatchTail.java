import java.io.*;
import java.util.*;

public class CatchTail {
	static int n, m, k;
	static Block[][] board;
	static List<Person>[] teams;
	static boolean[] isReversed; // 꼬리와 머리가 바뀌었는지 확인하는 배열
	static int[] scores;
	//우상좌하
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		board = new Block[n][n];
		scores = new int[m];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				board[r][c] = new Block(Integer.parseInt(st.nextToken()));
			}
		}

		initTeam();
		for(int round = 0; round < k; round++) {
			moveTeam();
			catchBall(round);
		}
		
		int totalCnt = 0;
		for(int i = 0; i < m; i++) {
			totalCnt += scores[i];
		}
		
		System.out.println(totalCnt);
	}

    private static void initTeam() {
		teams = new LinkedList[m];
		isReversed = new boolean[m];

		for (int i = 0; i < m; i++) {
			teams[i] = new LinkedList<>();
		}

		boolean[][] visited = new boolean[n][n];
		int teamNum = 0;

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (board[r][c].value == 1) {
					getTeam(visited, teamNum, r, c);
					teamNum++;
				}
			}
		}
	}

	/* 트랙에 있는 팀원을 팀리스트에 넣어준다. */
	private static void getTeam(boolean[][] visited, int teamNum, int x, int y) {
		Deque<Track> track = new ArrayDeque<>();
		track.add(new Track(x, y));
		visited[x][y] = true;

		int num = 1;
		while (!track.isEmpty()) {
			Track cur = track.poll();
			// 팀원이라면 팀에 넣기
			if (board[cur.x][cur.y].value != 4) {
				teams[teamNum].add(new Person(cur.x, cur.y, num));
				board[cur.x][cur.y].value = num++;
			} else { // 트랙이라면
				board[cur.x][cur.y].value = -1;
			}   
                // 트랙에도 팀번호 삽입
				board[cur.x][cur.y].team = teamNum;
			for (int i = 0; i < 4; i++) {
				int dx = cur.x + dr[i];
				int dy = cur.y + dc[i];

				if (!arrayBoundsValidation(dx, dy) || visited[dx][dy]) {
					continue;
				}
                // 트랙이 아니면 패스.
                // 머리와 꼬리가 붙어있을 경우 머리부터 꼬리까지 차례대로 보기위한 조건
                // 머리(1) -> 꼬리(3)이라면 패스
				if (board[dx][dy].value == 0 || board[dx][dy].value == board[cur.x][cur.y].value + 2) {
					continue;
				}

				track.add(new Track(dx, dy));
				visited[dx][dy] = true;
			}
		}
	}
	
    /* 주어진 팀번호의 머리와 꼬리를 바꾼다. */
	private static void reverse(int team) {
		int num = teams[team].size();
		for (int p = 0; p < teams[team].size(); p++) {
			teams[team].get(p).num = num--;
		}
		Collections.sort(teams[team]);
	}

    /* 모든 팀이 한 칸 이동한다. */
	private static void moveTeam() {
		for (int team = 0; team < m; team++) {
			moveFront(team);
		}
	}

    /* 앞으로 한 칸 이동한다. */
	private static void moveFront(int team) {
		Person cur;
        // 몸통과 꼬리 이동
        // 앞 팀원의 자리로 이동하면 된다.
		for (int p = teams[team].size() - 1; p > 0; p--) {
			cur = teams[team].get(p);
			// 꼬리일때
			if (p == teams[team].size() - 1) {
				board[cur.x][cur.y].value = -1;
			}

			cur.x = teams[team].get(p - 1).x;
			cur.y = teams[team].get(p - 1).y;
			board[cur.x][cur.y].value = cur.num;
		}

		// 머리가 한 칸 앞으로 이동
        // 머리는 앞 번호가 있었던 자리로 이동하는 것이 아니라 
        // 트랙을 따라 이동해야 해서 사방탐색을 통해 트랙을 찾고 이동한다.
		cur = teams[team].get(0);
		for (int i = 0; i < 4; i++) {
			int dx = cur.x + dr[i];
			int dy = cur.y + dc[i];

			if (!arrayBoundsValidation(dx, dy)) {
				continue;
			}

			// 트랙일 때 이동
			if (board[dx][dy].value == -1) {
				board[dx][dy].value = 1;
				cur.x = dx;
				cur.y = dy;
				break;
			}
		}
	}

    private static void catchBall(int round) {
            //시작점이 1, n, 2n, 3n인지 찾기
            int nRound = (round % (n*4))/n;
            //한 변에서 몇번째 줄인지 찾기
            int lineOrder = (round % (n*4))%n;
            Block catcher = null;
            
            int dx = 0;
            int dy = 0;
            int ballDir = nRound;
            //시작점이 1일때
            if(nRound == 0) {
                dx = lineOrder;
                dy = 0;
            }
            //시작점이 n일때
            if(nRound == 1) {
                dx = n-1;
                dy = lineOrder;
            }
            //시작점이 2n일때
            if(nRound == 2) {
                dx = n-1-lineOrder;
                dy = n-1;
            }
            //시작점이 3n일때
            if(nRound == 3) {
                dx = 0;
                dy = n-1-lineOrder;
            }

            //공던지기
            for(int i = 0; i < n; i++) {
                if(board[dx][dy].value > 0) {
                    catcher = board[dx][dy];
                    //공 잡은 팀 머리와 꼬리 바꾸기
                    reverse(board[dx][dy].team);
                    break;
                }
                
                dx = dx+dr[ballDir];
                dy = dy+dc[ballDir];
            }

            //점수 계산하기
            if(catcher == null) {
                return;
            }
            scores[catcher.team] += (int)Math.pow(catcher.value,2);
        }

	private static boolean arrayBoundsValidation(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}

	static class Track {
		int x;  
		int y;

		public Track(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Block{
		int value;  // 0: 빈공간 , 양수: 팀원 번호, -1: 트랙
		int team;   // 팀 번호

		public Block(int value) {
			this.value = value;
		}
	}
	
	static class Person implements Comparable<Person> {
		int x;
		int y;
		int num;    // 머리에서부터 몇 번째 팀원인지
		int team;   // 팀 번호

		public Person(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Person o) {
			return this.num - o.num;
		}
	}

}