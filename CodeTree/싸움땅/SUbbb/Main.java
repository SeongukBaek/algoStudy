import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Player {
        int order;
        int x;
        int y;
        int direction;
        int stat;
        int weapon;

	    Player(int order, int x, int y, int direction, int stat) {
	        this.order = order;
	        this.x = x;
	        this.y = y;
	        this.direction = direction;
	        this.stat = stat;
	    }
    }
	    
    private static int N;
    private static int M;
    private static Queue<Integer>[][] map;
    private static int[] playerPoints;
    private static List<Player> players;
    private static Player winner;
    private static Player loser;
    private static int point;
    private static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new PriorityQueue[N][N];
        playerPoints = new int[M];
        players = new ArrayList<>();

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
            	map[x][y] = new PriorityQueue<>(Collections.reverseOrder());
            	int weapon = Integer.parseInt(st.nextToken());
            	if (weapon == 0) {
            		continue;
            	}
                map[x][y].add(weapon);
            }
        }

        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players.add(new Player(index, x, y, direction, s));
        }

        for (int round = 0; round < K; round++) {
        	playGame();
        }

        print();
    }

    private static void playGame() {
        // 플레이어 한명씩 이동
        for (Player currentPlayer : players) {
            int x = currentPlayer.x;
            int y = currentPlayer.y;
            int direction = currentPlayer.direction;

            // 이동
            int nx = x + directions[direction][0];
            int ny = y + directions[direction][1];

            // 만약 맵 안이라면 해당 반복문으로는 진입하지 않음
            while (!isInBoundary(nx, ny)) {
                direction = reverseDirection(direction);
                nx = x + directions[direction][0];
                ny = y + directions[direction][1];
            }

            // 이동하려는 위치에 있는 플레이어 확인
            Player metPlayer = getPlayer(nx, ny);
            
            // 이동 완료
            currentPlayer.x = nx;
            currentPlayer.y = ny;
            currentPlayer.direction = direction;
            
            // 플레이어가 없는 경우
            if (metPlayer == null) {
                // 해당 위치에 무기가 있고, 플레이어의 현재 무기보다 더 센 무기가 있는 경우는 교체!
            	currentPlayer = updateWeapon(currentPlayer);
                continue;
            }

            // 플레이어가 있는 경우
            // 승자와 패자 결정
            determineWinnerAndLoser(currentPlayer, metPlayer);
            loserAction();
            winnerAction();
        }
    }
    
    /**
     * 패자는 무기를 버리고, 이동한다.
     * */
    private static void loserAction() {
    	 // 패자는 무기를 내려두고, 한 칸 이동
        if (loser.weapon != 0) {
            map[loser.x][loser.y].add(loser.weapon);
            loser.weapon = 0;
        }

        int nx = loser.x + directions[loser.direction][0];
        int ny = loser.y + directions[loser.direction][1];
        // 맵 밖이거나, 플레이어가 있는 경우 계속 방향을 바꿈
        while (!isInBoundary(nx, ny) || getPlayer(nx, ny) != null) {
        	loser.direction = rightDirection(loser.direction);
        	nx = loser.x + directions[loser.direction][0];
        	ny = loser.y + directions[loser.direction][1];
        }
        
        // 패자 위치 정보 갱신
        loser.x = nx;
        loser.y = ny;
        
        // 해당 위치에 있는 무기를 가지기
        if (!map[loser.x][loser.y].isEmpty()) {
        	loser.weapon = map[loser.x][loser.y].poll();
        }
    }

    /**
     * 승자는 포인트를 얻고, 무기를 갱신한다.
     * */
    private static void winnerAction() {
        // 승자는 포인트를 얻고, 무기를 갱신
        playerPoints[winner.order] += point;
        winner = updateWeapon(winner);
    }
    
    /**
     * 해당 플레이어의 위치를 이용해 무기 정보를 갱신
     * @param player 무기를 갱신할 플레이어
     * @return Player
     * */
    private static Player updateWeapon(Player player) {
    	if (!map[player.x][player.y].isEmpty() && player.weapon < map[player.x][player.y].peek()) {
        	if (player.weapon != 0) {
                map[player.x][player.y].add(player.weapon);
        	}
        	player.weapon = map[player.x][player.y].poll();
        }
    	return player;
    }
    
    /**
     * 주어진 플레이어 정보를 이용해 승자와 패자를 결정하고, 승자가 획득할 점수를 계산
     * */
    private static void determineWinnerAndLoser(Player currentPlayer, Player metPlayer) {
    	int currentPlayerPower = currentPlayer.stat + currentPlayer.weapon;
    	int metPlayerPower = metPlayer.stat + metPlayer.weapon;
    	
    	if (currentPlayerPower == metPlayerPower) {
            if (currentPlayer.stat > metPlayer.stat) {
                winner = currentPlayer;
                loser = metPlayer;
            } else {
                winner = metPlayer;
                loser = currentPlayer;
            }
        } else if (currentPlayerPower > metPlayerPower) {
            winner = currentPlayer;
            loser = metPlayer;
        } else {
            winner = metPlayer;
            loser = currentPlayer;
        }
    	
    	point = Math.abs(currentPlayerPower - metPlayerPower);
    }

    /**
     * 주어진 좌표에 위치한 플레이어를 반환
     * @param x x 좌표
     * @param y y 좌표
     * @return Player
     * */
    private static Player getPlayer(int x, int y) {
        for (Player player : players) {
            if (player.x == x && player.y == y) {
                return player;
            }
        }
        return null;
    }

    /**
     * 주어진 방향의 오른쪽 90도 회전한 방향을 반환
     * @param direction 주어지는 방향
     * @return int
     * */
    private static int rightDirection(int direction) {
        return (direction + 1) % 4;
    }

    /**
     * 주어진 방향의 정반대를 반환
     * 0 -> 2, 1 -> 3
     * @return int
     * */
    private static int reverseDirection(int direction) {
        if (direction <= 1) {
            return direction + 2;
        }
        return direction - 2;
    }

    private static void print() {
        StringBuilder result = new StringBuilder();
        for (int point : playerPoints) {
            result.append(point).append(" ");
        }
        System.out.print(result);
    }

    private static boolean isInBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}