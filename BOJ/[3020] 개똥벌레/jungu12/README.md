# [3020] 개똥벌레

## :pushpin: **Algorithm**

누적 합

## :round_pushpin: **Logic**

```java
private static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    evenObstacles = new int[H];
    oddObstacles = new int[H];
    even = new int[H];
    odd = new int[H];
    count = new int[H];

    for (int i = 0; i < N; i++) {
        if (i % 2 == 0) {
            evenObstacles[Integer.parseInt(br.readLine())]++;
        }
        if (i % 2 != 0) {
            oddObstacles[Integer.parseInt(br.readLine())]++;
        }
    }

    //누적합 배열 만들기
    for (int i = 1; i < H; i++) {
        even[i] = even[i - 1] + evenObstacles[i];
        odd[i] = odd[i - 1] + oddObstacles[i];
    }
}
```

- 종유석과 석순에 대한 입력값을 따로 받았다.
- 누적합 배열도 당연히 각각 만들어 주었다.

```java
//각 구간들에서 부숴야 하는 장애물의 최소값을 반환
private static int countMinObstaclesToDestroy() {
    for (int i = 0 ; i < H; i++) {
        count[i] += even[H - 1] - even[i];
        count[i] += odd[H - 1] - odd[H - i - 1];
    }
    return Arrays.stream(count).min().getAsInt();
}

//장애물을 최소로 부수고 지나갈 수 있는 구간의 개수를 반환
private static int countMinSection(int min) {
    int cnt = 0;
    for (int i = 0; i < H; i++) {
        if (count[i] == min) {
            cnt++;
        }
    }
    return cnt;
}
```

- even 배열 (석순에 대한 누적합 배열)을 예시로 설명하면, even[H - 1]에는 총 석순의 개수가 저장되고, even[2]에는 높이가 1, 2인 석순의 개수가 저장 된다.
- 높이가 3인 구간으로 지나갈 때, 부숴야하는 석순의 개수는 even[H - 1] - even[2] 가 된다!
- 이 로직을 적용하여 부숴야하는 장애물의 개수를 빠르게 구할 수 있다.

## :black_nib: **Review**

- N과 H의 값이 큰 것을 보고 일반적인 방법으로는 풀 수 없다는 걸 알았지만 다른 방법이 생각나지 않아 그냥 풀고 시간초과를 보았다..
- DP로는 풀 수 없는 것 같아서 누적 합인가..? 하고 고민을 해보니 방법이 떠올라서 풀었다.. 정말 어렵게 풀었다 ...
