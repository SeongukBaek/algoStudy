# [삼성 SW 역량테스트] 싸움땅

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

문제에서 요구하는 순서대로 구현하였다.

```java
			// 현재 플레이어가 벽을 보고 있는 경우
			if (playerSeeWall(curPlayer.x, curPlayer.y, curPlayer.d)) {
				curPlayer.d = changeDirection(curPlayer.d);
			}
			// 보고 있는 방향에 적이 있나 확인한 이동
			int nx = curPlayer.x + dx[curPlayer.d];
			int ny = curPlayer.y + dy[curPlayer.d];
			int enemy = checkPlayer(nx, ny);

			curPlayer.x = nx;
			curPlayer.y = ny;
```

- 플레이어가 벽을 보고 있는 경우 뒤로 돌아서 적이 있는지 확인한 후 이동한다.

```java
			// 이동한 방향에 플레이어가 없는 경우
			if (enemy == -1) {
				// 이동한 방향에 총이 있는 경우
				if (!map[curPlayer.x][curPlayer.y].isEmpty()) {
					curPlayer.weaponPower = compare(curPlayer);
				}
			}
```

- 이동한 방향에 플레이어가 없다면 총이 있는지 확인한 후에 자신이 가지고 있는 무기와 비교하여 더 강한 무기를 가진다.

```java
	public static void fight(int p, int enemy) {
		Player player = players.get(p);
		Player enemyPlayer = players.get(enemy);
		int p1Power = player.s + player.weaponPower;
		int p2Power = enemyPlayer.s + enemyPlayer.weaponPower;

		if (p1Power > p2Power) {
			macthEnd(p, enemy);
		}
		else if (p1Power < p2Power) {
			macthEnd(enemy, p);
		}
		// 공격력이 같은 경우 원래 플레이어의 스텟으로 비교
		else if (p1Power == p2Power) {
			if (player.s > enemyPlayer.s) {
				macthEnd(p, enemy);
			}
			else if (player.s < enemyPlayer.s) {
				macthEnd(enemy, p);
			}
		}
	}
```

- 만약 이동한 방향에 플레이어가 있는 경우 두 플레이어가 싸운다.

```java
	public static void macthEnd(int winner, int loser) {
		Player winnerPlayer = players.get(winner);
		Player loserPlayer = players.get(loser);
		// 1. 이긴 사람은 점수 획득
		winnerPlayer.point += win(winnerPlayer, loserPlayer);
		// 2. 진 플레이어는 총을 내려놓고 이동한 후 그 위치의 총을 비교하여 높은 공격력의 총을 획득한다.
		loserPlayer = lose(loserPlayer);
		// 3. 이긴 플레이어는 승리한 칸에 총이 있는 경우 총들과 자신의 총을 비교해 높은 공격력의 총을 가진다.
		if (!map[winnerPlayer.x][winnerPlayer.y].isEmpty()) {
			winnerPlayer.weaponPower = compare(winnerPlayer);
		}

		players.set(winner, winnerPlayer);
		players.set(loser, loserPlayer);
	}
	/**
	 * 포인트를 얻는다.
	 * 얻을 포인트를 반환한다.
	 **/
	public static int win(Player winner, Player loser) {
		int point = (winner.s + winner.weaponPower) - (loser.s + loser.weaponPower);

		return point;
	}
```

- 싸움이 끝난 후 이긴 사람은 점수를 획득하고 진 사람은 총을 내려놓고 이동한 후 그 위치에서 총을 비교하고 높은 공격력을 가진 총을 가진다. 이후 이긴 플레이어는 그 위치에서 총들을 비교해 높은 공격력을 가진 총을 가진다.

## :black_nib: **Review**

- 아무래도 구현할 부분이 많다보니 중간에 순서가 꼬여서 실수하는 부분들이 생겼다. 구현 시간을 줄이기 위해서는 이러한 실수를 줄여야겠다고 생각했다.
- 어떤 방식으로 return 타입을 하는게 좋을지 많이 생각해볼 수 있었다.
