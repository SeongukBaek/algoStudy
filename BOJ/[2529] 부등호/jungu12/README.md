# [2529] 부등호

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

```java
static void rec_func(int idx) {
		if (idx == N + 1) {
			StringBuilder sb = new StringBuilder();
			for(int n : nums) {
				sb.append(n);
			}
			Long num = Long.parseLong(sb.toString());
			if(num > max) {
				max = num;
				maxStr = sb.toString();
			}
			if(num < min) {
				min = num;
				minStr = sb.toString();
			}
			return;
		} else if (idx == 0) {
			for (int i = 0; i <= 9; i++) {
				nums[0] = i;
				Used[i] = true;
				rec_func(1);
				Used[i] = false;
			}
		} else {
			for (int i = 0; i <= 9; i++) {
				if (!Used[i]) {
					if (symbols[idx - 1].equals(">")) {
						if(nums[idx-1] > i) {
							nums[idx] = i;
							Used[i] = true;
							rec_func(idx+1);
							Used[i] = false;
						}
					}
					else {
						if(nums[idx-1] < i) {
							nums[idx] = i;
							Used[i] = true;
							rec_func(idx+1);
							Used[i] = false;
						}
					}
				}
			}
		}
	}
  ```
  
   - 숫자가 이미 사용 된 숫자인지 확인하기 위해 boolean 배열을 만들어 사용하였다.
   - Long으로 변환하여 값을 비교하고 stringbuilder를 활용하여 string으로 정답을 출력하였다.
  
  
## :black_nib: **Review**
 - 재귀안에서 반복되는 코드가 다소 많다.
 - Long으로 비교하고 string으로 바꿔주어 변수의 개수도 많아졌다.
 - 자바의 네이밍 규칙을 지키지 않았다.
 - 로직은 어렵지 않았으나 값들을 어떤 타입으로 관리하고 출력할지 고민해 보았다.
  
  	

  
