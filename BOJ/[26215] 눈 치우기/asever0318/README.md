# [26215] 눈 치우기

## **Algorithm**

 - Greedy 알고리즘 & 정렬


## **Logic**

``` java
Arrays.sort(snow, Collections.reverseOrder());
```
- 문제에서 한 번에 두 집 앞의 눈을 각각 1만큼 치우거나 한 집의 눈을 1만큼 치울 수 있다고 했으므로, 이 조건을 만족하기 위해서는 두 집을 동시에 치우는 시간이 최대여야 한다. 따라서 먼저 집 앞에 쌓인 눈의 양을 가장 큰 수부터 내림차순으로 정렬했다.

``` java
static int shoveled(int N) {
		int count = 0;
		
		if(N == 1) {
			count = snow[0];
		}
		else {
			while(snow[1] != 0) {
				// 한 번 count 할 때마다 다시 정렬 
				snow[0]--;
				snow[1]--;
				count++;
				Arrays.sort(snow, Collections.reverseOrder());
			}
			
			// 마지막 남은 한 집에 남은 눈까지 카운트 
			count += snow[0];
		}
		
		if(count > 1440)
			count =  -1;
		
		return count;
	}
```
- 총 집의 수가 한 집일 때와 두 집 이상일 때로 나누고 while문을 돌면서 가장 눈이 많이 쌓인 두 집을 동시에 1씩 치우면서 count하고 앞의 두 집이 뒤의 집보다 작아질 때를 고려하여 한 번 치울 때마다 다시 내림차순 정렬을 했다. 마지막 집만 남았을 때 반복문을 종료하며 남은 눈의 양을 모두 count 해주었다.


# **Review**
- 처음에 풀이를 할 때는 앞에서 먼저 정렬을 해주었기 때문에 따로 순서가 바뀔일이 없다고 생각하고 코드를 작성했는데, 두 집씩 치우다보니 정렬이 되지않고 순서가 뒤바뀌는 경우가 생겼다. 이는 매번 다시 정렬해주는 방법으로 문제를 해결할 수 있었다. 