# [1225] 암호생성기

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**
- 0이 도출될때까지, 각 원소에 num만큼 빼줌
  - 데이터 타입을 배열을 그대로 사용하고, 직접 순회하면서 값을 변경해줌
  - 실제로 데이터 값을 삭제하고 다시 추가하면 메모리 및 시간이 많이 소모되기 때문
```java
for(int i=0; i<8; i++) {
				
				arr[i] -= num;
				
				if(arr[i] <= 0) {
					arr[i] = 0;
					idx = i+1;
					break;
				}
				
				num++;
				
				if(num == 6)
					num = 1;
				
				if(i == 7)
					i = -1;
			}
```
## :black_nib: **Review**
