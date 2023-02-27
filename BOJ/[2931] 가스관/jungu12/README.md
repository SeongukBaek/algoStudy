# [2931] 가스관

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static void findDeletedBlockPos(Pos pos) {
	Pos nextPos = findNextPos(pos);
	if (pos == mos) {
		nextPos = findNextPosAtMos(mos);
		if (nextPos == pos) {
			deletedBlockPos = new Pos(nextPos.x, nextPos.y, nextPos.dir);
			return;
		}
	} else {
		if (map[nextPos.x][nextPos.y] == '.') {
			deletedBlockPos = new Pos(nextPos.x, nextPos.y, nextPos.dir);
			return;
		}
	}
	findDeletedBlockPos(nextPos);
}
```
   - 문제 조건에 쓸모 없는 파이프가 없다고 명시 되어있음 -> 파이프를 따라가면서 파이가 없어진 곳을 찾는 방법을 선택
   - 현재 Pos가 모스크바라면 방향이 존재하지 않기에 다른 Pos에서와 다른 task(findNextPosAtMos())를 수행해주어 다음 파이프를 찾음

```java
  static void findDeletedBlockType() {
		Set<Character> pipes = new HashSet<>(Arrays.asList('|', '-', '+', '1', '2', '3', '4'));
		for (int i = 0; i < 4; i++) {
			Pos nextPos = new Pos(deletedBlockPos.x + dx[i], deletedBlockPos.y + dy[i]);
			if (nextPos.x < 0 || nextPos.x >= R || nextPos.y < 0 || nextPos.y >= C) {
				continue;
			}
			if (map[nextPos.x][nextPos.y] == '.' || map[nextPos.x][nextPos.y] == 'M') {
				pipes.remove('+');
			}
			if (i == 0 && (map[nextPos.x][nextPos.y] == '|' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '1' || map[nextPos.x][nextPos.y] == '4')) {
				pipes.remove('1');
				pipes.remove('4');
				pipes.remove('-');
			} if (i == 1 && (map[nextPos.x][nextPos.y] == '|' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '2' || map[nextPos.x][nextPos.y] == '3')) {
				pipes.remove('2');
				pipes.remove('3');
				pipes.remove('-');
			} if (i == 2 && (map[nextPos.x][nextPos.y] == '-' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '1' || map[nextPos.x][nextPos.y] == '2')) {
				pipes.remove('|');
				pipes.remove('1');
				pipes.remove('2');
			}
			if (i == 3 && (map[nextPos.x][nextPos.y] == '-' || map[nextPos.x][nextPos.y] == '+'
					|| map[nextPos.x][nextPos.y] == '3' || map[nextPos.x][nextPos.y] == '4')) {
				pipes.remove('|');
				pipes.remove('3');
				pipes.remove('4');
			}
		}
        //파이프가 없어진 pos가 자그레브와 인접한 경우
		if(pipes.size() == 0) {
			for(int i = 0 ; i < 4; i++) {
				Pos nextPos = new Pos(deletedBlockPos.x + dx[i], deletedBlockPos.y + dy[i]);
				if(map[nextPos.x][nextPos.y] == 'Z') {
					if(i == 0) {
						if(deletedBlockPos.dir == 0) {
							pipes.add('|');
						}
						if(deletedBlockPos.dir == 2) {
							pipes.add('2');
						}
						if(deletedBlockPos.dir == 3) {
							pipes.add('3');
						}
					}
					if(i == 1) {
						if(deletedBlockPos.dir == 1) {
							pipes.add('|');
						}
						if(deletedBlockPos.dir == 2) {
							pipes.add('1');
						}
						if(deletedBlockPos.dir == 3) {
							pipes.add('4');
						}
					}
					if(i == 2) {
						if(deletedBlockPos.dir == 2) {
							pipes.add('-');
						}
						if(deletedBlockPos.dir == 1) {
							pipes.add('3');
						}
						if(deletedBlockPos.dir == 0) {
							pipes.add('4');
						}
					}
					if(i == 3) {
						if(deletedBlockPos.dir == 3) {
							pipes.add('-');
						}
						if(deletedBlockPos.dir == 1) {
							pipes.add('2');
						}
						if(deletedBlockPos.dir == 0) {
							pipes.add('1');
						}
					}
				}
			}
		}
		sb.append(pipes.iterator().next());
	}
```
   - Set에 모든 파이프 모양을 저장한다.
   - 사방탐색을 하여 해당 파이프와 연결 할 수 없는 모양의 파이프는 지워준다.
   - 자그레바 바로 전 파이프가 지워진 경우는 따로 구현해주었다.


## :black_nib: **Review**
 - 문제에서 모스크바와 자그레브에 인접한 파이프의 개수가 1개라고 하여 모스크바와 자그레브가 붙어있는 경우를 구현해주지 않았었다.. (문제가 다소 불친절하다..)
 - 파이프의 모양이 다양하다 보니 구현해야할 것들이 많았다. 끝까지 집중력을 잃지 않고 푸는 것이 중요했던 문제였다.

