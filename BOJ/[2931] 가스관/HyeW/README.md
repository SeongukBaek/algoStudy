# [2931] 가스관

## :pushpin: **Algorithm**

그래프 이론, 구현

## :round_pushpin: **Logic**
파괴된 부분 말고는 가스가 문제없이 흐른다는 것을 생각하며 문제를 해결하였다.

```java
static class Node {
    int x;
    int y;
    int dir;

    public Node(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
```
가스 정보로 좌표값, 방향을 저장했다.

```java
static boolean runGas(Node start) {
		boolean isFlow = true;
		
		visited = new boolean[N][M];
		gas.clear();
		gas.add(start);
		visited[start.x][start.y] = true;
		
		while (!gas.isEmpty()) {
			Node cur = gas.poll();

			switch (europe[cur.x][cur.y]) {
			case '|':
				isFlow = blockVer(cur);
				break;
			case '-':
				isFlow = blockHor(cur);
				break;
			

            //...
            //다른 블록 모양에 대해서도 수행

			}

			if (!isFlow) {
				return false;
			}
		}
		
		return true;
	}
```
1. 방향 정보를 통해 내가 가스가 흐르는 위치에 파이프가 있는지 확인한다.
2. 파이프가 있다면 다음 파이프로 이동하고 해당 파이프를 만났을 때 변화하는 가스방향을 포함하여 저장하여 큐에 삽입한다.
3. 파이프가 없다면 그 곳이 파괴된 블록의 좌표이다.
<br/>

```java
static char findBlockType(Node removedBlock) {
		
    char up = 'x';
    if(removedBlock.x-1 >= 0) {
        up = europe[removedBlock.x-1][removedBlock.y];
    }
    
    char down = 'x';
    if(removedBlock.x+1 < N) {
        down = europe[removedBlock.x+1][removedBlock.y];
    }
    
    //left, right에 대해서도 수행
    // ...

    boolean upCheck =  (up == '|' || up == '+' || up == '1' || up =='4');
    boolean downCheck =  (down == '|' || down == '+' || down == '2' || down == '3');
    boolean leftCheck = (left == '-' || left == '+' || left == '1' || left == '2');
    boolean rightCheck = (right == '-' || right == '+' || right == '2' || right == '4');

    //모양별 탐색
    if(upCheck && downCheck && leftCheck && rightCheck) {
        return '+';
    }
    if(upCheck && downCheck) {
        return '|';
    }
    if(leftCheck && rightCheck) {
        return '-';
    }
    
    //...
    // 나머지 블록 모양에 대해서도 수행
    
    return 'x';
}
```
파괸된 블록을 구하기 위해서 해당 블록 주변에 파이프 모양을 확인한다. 그래서 파이프 모양에 따라 파괴된 좌표와 연결되었는지를 경우를 나누어 삽입할 파이프를 고른다.
<br/>
ex ) 파괴된 블록 위에 `|`블록, 아래에 `3`블록이 있고 좌우에는 연결된 파이프가 없다면 파괴된 블록은 `|`이다.

## :black_nib: **Review**
- 가스가 **흐른다**는 것에 집중해서 가스 방향까지 생각하며 흐르는 느낌이 나게 그래프 이론을 사용해 코드를 작성했다.
    - 파괴된 블록을 찾는 것은 파괴된 블록 전까지 잘 흐르는 가스관이라서 무리없이 코드가 잘 동작했다.
- 처음에 파괸된 블록 좌표에 8가지 파이프를 차례대로 넣으며 다시 시작점부터 bfs를 사용해 가스가 흐르는지 체크하도록 코드를 작성했는데 파이프가 잘 연결됐는지 확인하지 못해 문제가 발생했다.
    - 처음에 작성한 코드는 `-``1`이 붙어있으면 가스가 흐르는 것으로 판단했다.
    - 뒤늦게 주변 파이프 모양에 따라 파이프 연결여부를 알 수 있는 방법을 알게 되었고 코드를 수정했다.
    - 처음에 생각했던 풀이인데 모든 경우를 나누는 게 복잡해보였고 코드가 길어질 것이라고 생각해서 안했는데 직관적이고 깔끔한 코드였다. 그래서 케이스를 나눠서 해결하는 것이 나쁘다는 생각을 앞으로 하지 않을 것이다.!