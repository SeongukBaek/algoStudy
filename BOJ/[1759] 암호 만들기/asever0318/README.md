# [1759] 암호 만들기

## **Algorithm**

 - 브루트포스, 백트래킹


## **Logic**

``` java
Arrays.sort(clist);
```
- 문제에 암호 생성 조건이 알파벳 순서이기 때문에 먼저 입력받은 char 배열을 정렬해줬다.


``` java
public static void recur(int num, int depth) {
		
		if(depth == L) { //L개 다 뽑으면 
			//모음과 자음 갯수 확인 
			if(check(code) == true) {
				System.out.println(code);
				//return; //리턴 위치 조심! ArrayindexOutOfBounds 에러 - 범위를 벗어났을 때 발생
			}
			return; //리턴해줘야 빠져나가서 배열 처음부터 채울 수 있음
		}
		
		for(int i = num; i < C; i++) {

			code[depth] = clist[i];
			recur(i + 1, depth + 1);
		}
	}

```
- 암호 자릿수가 L개로 매번 달라질 수 있기때문에 재귀를 통해 L개를 뽑고, L개를 다 뽑으면 만들어진 암호의 모음과 자음 갯수를 검사해서 통과하면 print하도록 했다.

``` java
public static boolean check(char[] code) {
		int c = 0; //자음
		int v = 0; //모음
		
		for(int a = 0; a < code.length; a++){
			switch(code[a]){
				case 'a': 
				case 'e': 
				case 'i': 
				case 'o':
				case 'u': v++; break;
				default : c++; break;
			}
		}
		
		//모음이 1개 이상히고 자음이 2개 이상이면 암호 출력
		if(v >= 1 && c >= 2)
			return true;
		else
			return false;
	}
```
- 자음과 모음의 개수는 switch문으로 검사하고 조건에 만족하면 true를 반환하도록 했다.

# **Review**
- 재귀함수에서 처음에 return 위치를 check if문안에 작성했는데 이 때문에 조건을 만족하지 못해서 if문안으로 진입하지 못하는 경우 재귀함수에서 빠져나오지 못하고 뒤로 계속 접근해서 ArrayindexOutOfBounds 에러가 발생했다. return을 해당 if문 바깥쪽으로 뺴주어서 해결 할 수 있었다. 이 문제를 해결하면서 재귀함수에 대해 좀 더 이해할 수 있게 된 거 같다.
- L개의 암호를 다 뽑고 조건을 검사했는데 암호를 뽑을 때 검사를 하면 불필요한 암호를 생성하지 않고 시간을 줄일 수 있을 것 같다.
- 문제를 풀 때 입력을 Sacnner로 받았는데 편리하긴하지만 Scanner보다는 BufferedReader를 사용하는 것이 좀 더 시간을 줄일 수 있다고 한다.