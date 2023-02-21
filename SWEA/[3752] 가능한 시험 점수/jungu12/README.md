# [3752] 가능한 시험 점수

## :pushpin: **Algorithm**



## :round_pushpin: **Logic**

```java
static void calScore() {
	for (int i = 0; i < N; i++) {
		Set<Integer> tmp = new HashSet<>();
		tmp.addAll(possibleScore);
		for (int s : tmp) {
			possibleScore.add(s + score[i]);
		}
	}
}
  ```
   - 점수를 Set에 관리하여 중복 점수를 처리해주었다.
   - 만들어진 점수들에 새로운 문제의 배점을 더하여 새로운 점수를 만들었다.
  
  
## :black_nib: **Review**
 - 백 트래킹으로 가능한 모든 점수들을 계산하려 하였으나, 시간 초과로 풀지 못하였다.


  
  	

  
