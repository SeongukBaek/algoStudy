# [19237] 어른 상어

## :pushpin: **Algorithm**

구현, 시뮬레이션

## :round_pushpin: **Logic**

```java
	static int[][] map;
	static int[][] smell; // 냄새 저장할 배열
	static int[][] time; // 시간 저장할 배열
	static int[][][] dirSharks; // [상어][방향][우선순위]
```

- 상어를 움직일 배열 map, 냄새를 저장할 배열 smell, 시간을 저장할 배열 time을 각각 따로 만들어줬다.
- 각 상어별 방향에 따른 우선순위를 저장하기 위한 3차원 배열을 만들었다.

```java
static void countTime() {

		// 처음부터 한 마리만 있는 경우
		if(countEnd()) {
			System.out.println(0);
			return;
		}

		int second = 1;

		// 1000초까지만 반복
		while(second <= 1000) {

			// 상어 한마리씩 데려와서 이동위치 저장
			for(int i = 1; i <= M; i++) {

				if(sharks[i] == null) { // 쫓겨난 놈이면 x
					continue;
				}

				boolean moveCheck = moveSharkEmpty(i); // 이동할 곳이 있는지 없는지 체크

				if(!moveCheck) { // 이동할 빈공간이 없었으면
					// 자신의 냄새가 있는 곳으로 이동할 수 있는지 확인
					moveCheck = moveSharkSmell(i);
				}
			}

			// 시간 감소시키기 --> 냄새 사라짐
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(smell[i][j] != 0) { // 시간이 남아있으면
						time[i][j]--; // 1초 감소

						if(time[i][j] == 0) { // 시간이 0이 되면 냄새 사라진거니까
							smell[i][j] = 0; // smell 맵에 표시
						}
					}
				}
			}

			// 마지막에 해당 위치로 옮겨주기
			for(int i = 1; i <= M; i++) { // 각 상어 돌면서

				if(sharks[i] == null) { // 쫓겨난 상어면 넘어가기
					continue;
				}

				// 해당 상어에 대한 이동정보 가져오기
				int[] info = sharkNewPos.get(i);

				moveShark(i, info[0], info[1], info[2]); // nx, ny, d

			}

			// 1번 상어 한마리만 남으면 종료
			if(countEnd()) {
				break;
			}

			second++;
		}

		// 1000초가 넘어도 다른 상어가 남아있으면 출력 -1
		if(second > 1000) {
			System.out.println(-1);
		}
		else {
			System.out.println(second);
		}
	}
```

- 상어 배열에서 한 마리씩 이동할 빈 곳이 있는지 확인하고 빈 곳이 없으면 자신의 냄새가 있는 곳으로 이동할 수 있는지 확인한다.
- 이동할 자리를 HashMap에 저장해놓고 시간을 감소시켜서 냄새맵을 갱신한 후 상어를 이동시켰다.
- 1000초까지 반복하고 1000초가 넘어도 다른 상어가 남아있으면 -1을 출력한다.

## :black_nib: **Review**

- 이동방향, 우선순위, 냄새, 시간 등 고려할 조건이 많아서 힘든 문제였다.
- 상어와 우선순위를 입력 숫자 그대로 사용하려고 인덱스 1부터 입력 받았는데 처음에 인덱스 설정을 잘못해서 찾는데 오래걸렸다.. 앞으로 더 꼼꼼하게 확인해야겠다.
