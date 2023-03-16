# [118668] 코딩 테스트 공부

## :pushpin: **Algorithm**

DP

## :round_pushpin: **Logic**

```java
		// 처음에 이미 가지고 있는 알고력, 코딩력이므로 걸리는 시간 = 0
		minTime[alp][cop] = 0;

		for(int i = alp; i <= alpMax; i++) {
			for(int j = cop; j <= copMax; j++) {
				// 1의 시간을 들여 알고력을 1 얻기
				// 현재 위치에서 알고력을 1얻었을 때의 시간과 이미 저장되어 있는 알고력+1까지의 최단시간을 비교 --> 더 적은 시간 저장
				if(i+1 <= alpMax) {
					minTime[i+1][j] = Math.min(minTime[i+1][j], minTime[i][j] + 1);
				}

				if(j+1 <= copMax) {
					minTime[i][j+1] = Math.min(minTime[i][j+1], minTime[i][j] + 1); // 1의 시간을 들여 코딩력 1얻기 --> 똑같이 비교 더 적은 시간 저장
				}

				// 각 문제에 대해서 문제를 풀 수 있으면 최단 시간 구해주기
				for(int p = 0; p < problems.length; p++) {
					// 문제를 풀 수 있으면 --> i = alp / j = cop이므로 problem이 요구하는 alp, cop값 이상이면 가능
					if(problems[p][0] <= i && problems[p][1] <= j) {
						int alpRwd = problems[p][2];
						int copRwd = problems[p][3];
						int time = problems[p][4];

                        // 문제를 풀고 난 후 알고력, 코딩력이 Max(목표값)를 넘으면 Max값에 넣어줘야 됨
						// alpMax = 20, i+alpRwd = 23이라면 20에 최소값을 넣어줘야 함!
						alpRwd = Math.min(i+alpRwd, alpMax);
						copRwd = Math.min(j+copRwd, copMax);

						minTime[alpRwd][copRwd] = Math.min(minTime[alpRwd][copRwd], minTime[i][j]+time);
					}
				}
			}
		}
```

- minTime[alp][cop] 배열을 만들어서 처음 가진 알고력, 코딩력부터 1의 시간을 들여 알고력을 1얻기, 1의 시간을 들여 코딩력 1얻기, 문제를 풀어서 알고력/코딩력 얻기 3가지 모든 경우로 나누어서 더 적은 시간을 저장해주는 방식으로 해결했다.
- 이때 문제를 푸는 경우에는 문제를 풀고 난 후의 알고력, 코딩력이 Max(목표값)을 넘으면 안된다는 조건을 주어야 한다. alpMax가 20이고, 문제를 풀고 난 후의 알고력이 23이라고 했을 때 최소 시간은 20에 저장되어야 되기 때문이다.

## :black_nib: **Review**

- 일부 테스트케이스와 효율성을 통과하지 못했었는데, 문제를 풀고 난 후 알고력/코딩력이 Max(목표값)을 넘으면 안된다는 조건을 고려해주니까 통과할 수 있었다.
