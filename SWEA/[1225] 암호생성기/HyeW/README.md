# [1225] 암호생성기

## :pushpin: **Algorithm**

큐

## :round_pushpin: **Logic**

사이클을 다섯번 돌면 다시 원래 리스트 순서로 돌아가게 된다. 그리고 5번 사이클을 돌면 하나의 수는 **15 (1+2+3+4+5)** 만큼 줄어든다. <br/>주어진 수에서 가장 작은 수를 찾아 이 수를 기준으로 사이클이 몇 번 돌 수 있는지 체크하고 이 값은 돌아야 하는 최소 사이클이기 때문에 모든 리스트 값이 해당 사이클만큼 돌고 난 후의 수를 구한다.
<br/>
```java
for (int i = 0; i < N; i++) {
    int tmp = Integer.parseInt(st.nextToken());

    if (min > tmp) {
        min = tmp;
        min_idx = i;
    }

    pw.add(tmp);
}

//최소 사이클 수 계산
int times = (pw.get(min_idx)-1) / 15;
if (times > 0)
    for (int i = 0; i < N; i++)
        pw.set(i, pw.get(i) - (times * 15));
```
`int times = (pw.get(min_idx)-1) / 15;` 여기서 -1을 한 이유는 times가 0값이 나오지 않아야 하기때문에 빼주었다. (0값이 나온다는 것은 종료조건을 중간에 만족했다는 의미로 따로 뒤에서 처리해줘야 한다.)

```java
int d = 0;
int cur = pw.get(0);
while (true) {
    pw.remove(0);
    cur -= ++d;
    
    if(d == 5)
        d = 0;
    if (cur <= 0)
        break;
    
    pw.add(cur);
    cur = pw.get(0);
}
pw.add(0);
```
이후에 1부터 5까지 한사이클씩 빼주며 0이하의 수가 나오면 종료한다.

## :black_nib: **Review**
- 주어진 수가 크면 사이클을 많이 돌게 되어 이를 줄이는데 신경을 쓰며 로직을 세웠다.
    - 사이클이 5번 돌게 되면 처음 0번 index가 다시 0번으로 돌아와 5번 사이클을 돌고 오면 리스트 순서가 처음과 같아진다.
- 들어온 순서대로 수를 빼서 다시 집어넣는 행위를 반복하여 ArrayList로 Queue를 구현했다.
    - add, remove연산은 LinkedList가 더 빠르니 문제를 다 풀고 나서 LinkedList로 변경하여 실행시간을 측정했는데 데이터양이 적어서인지 속도의 차이는 없었다.