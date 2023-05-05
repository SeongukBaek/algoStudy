# Algorithm Study
코딩테스트의 강자가 되기 위한 스터디입니다.

**정기 회의** : 매주 목요일 저녁

**마감 기한** : 매주 수요일 오후 6시
- 벌금 : 문제당 4000원 (벌금은 제작진들이 회식비로 맛있게 사용할 예정입니다.)

## 💁‍♂️ 스터디 규칙
### 문제 풀이
매주 **5문제**씩 해결하고, 오프라인으로 진행되는 **리뷰 시간**에 의견을 공유합니다.<br/>
해결한 문제의 **풀이와 코드 작성 이유**를 잘 설명할 수 있도록 합니다.

> 자신의 코드는 누구보다도 자신이 가장 잘 이해할 수 있어야 합니다.
설명과 가독성을 위한 깔끔한 주석을 잘 작성할 수 있도록 합시다!

### 리드미 규칙
사용한 알고리즘, 중요 구현 로직 및 설명, 풀이 후기

### 커밋 규칙
1. **Repository clone**
```bash
git clone https://github.com/SeongukBaek/algoStudy.git
```

2. **Repository open**
- vscode or IntelliJ

3. **본인의 Branch 생성**
- branch는 주차별로 생성한다.

```bash
git checkout -b {본인의 깃허브 이름}/{주차명}
```
> ex. git checkout -b SUbbb/1week

4. **문제별 디렉토리 생성 및 코드, README 저장**
```
{플랫폼}/[{문제 번호}] {문제명}/본인의 깃허브 이름
```
> ex. BOJ/[1759] 암호 만들기/SUbbb

5. **Push**
```bash
git add .
git commit -m "{주차명} : {플랫폼}[{문제번호}] {문제명}"
git push origin {생성한 브랜치}
```

> ex. git commit -m "1week : BOJ[1759] 암호 만들기"

6. **Pull request 생성**
- Pull Request Name : {본인의 깃허브 이름} : [{주차명}]
  > ex. SUbbb : [1week]
- Content : 문제명, 시간복잡도, 시간 및 메모리 캡처
- Label : 플랫폼, 언어
- Assignees : 본인

7. **스터디 회의 후, merge**

### 리뷰 규칙
스터디 시작 전, 다른 사람의 코드를 보고 신랄한 평가와 코멘트를 부탁드립니다.

> **코드 리뷰는 창과 방패의 싸움.**

---
## 💻 문제
<details><summary>📎 2월 문제집</summary>

|주차|1|2|3|4|5|
|:---:|:---:|:---:|:---:|:---:|:---:|
|**1주차**<br> (01.25 ~ 01.31)|[부등호](https://www.acmicpc.net/problem/2529)|[암호 만들기](https://www.acmicpc.net/problem/1759)||
|**2주차**<br> (02.01 ~ 02.07)|[테트로미노](https://www.acmicpc.net/problem/14500)|[강의실 배정](https://www.acmicpc.net/problem/11000)|[눈 치우기](https://www.acmicpc.net/problem/26215)|[암호생성기](https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14uWl6AF0CFAYD&categoryId=AV14uWl6AF0CFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2)|[햄버거 다이어트](https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2)||
|**3주차**<br> (02.08 ~ 02.15)|[구간 합 구하기 5](https://www.acmicpc.net/problem/11660)|[토마토](https://www.acmicpc.net/problem/7576)|[벽 부수고 이동하기](https://www.acmicpc.net/problem/2206)|[비밀번호](https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14_DEKAJcCFAYD&categoryId=AV14_DEKAJcCFAYD&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=3)|[창용 마을 무리의 개수](https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWngfZVa9XwDFAQU&categoryId=AWngfZVa9XwDFAQU&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=4&pageSize=10&pageIndex=4&&&&&&&&&&)|
|**4주차**<br> (02.16 ~ 02.22)|[문자열 폭발](https://www.acmicpc.net/problem/9935)|[고냥이](https://www.acmicpc.net/problem/16472)|[가능한 시험 점수](https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AWHPkqBqAEsDFAUn&categoryId=AWHPkqBqAEsDFAUn&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=4&pageSize=10&pageIndex=1&&&&&&&&&&)|[배열 돌리기 4](https://www.acmicpc.net/problem/17406)|[파이프 옮기기 1](https://www.acmicpc.net/problem/17070)|
|**5주차**<br> (02.23 ~ 03.01)|[연구소](https://www.acmicpc.net/problem/14502)|[가스관](https://www.acmicpc.net/problem/2931)|[싸움땅](https://www.codetree.ai/training-field/frequent-problems/battle-ground/description?page=3&pageSize=20&username=bsu1209)|[2048 (Easy)](https://www.acmicpc.net/problem/12100)|[점심 식사시간](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl&)||

</details>

<details><summary>📎 3월 문제집</summary>

|주차|1|2|3|4|5|
|:---:|:---:|:---:|:---:|:---:|:---:|
|**1주차**<br> (03.02 ~ 03.08)|[점프](https://www.acmicpc.net/problem/1890)|[어른 상어](https://www.acmicpc.net/problem/19237)|[색종이 붙이기](https://www.acmicpc.net/problem/17136)|[게리맨더링 2](https://www.acmicpc.net/problem/17779)|[직사각형](https://www.acmicpc.net/problem/2527)|
|**2주차**<br> (03.09 ~ 03.15)|[멀쩡한 사각형](https://school.programmers.co.kr/learn/courses/30/lessons/62048)|[택배상자](https://school.programmers.co.kr/learn/courses/30/lessons/131704)|[코딩테스트 공부](https://school.programmers.co.kr/learn/courses/30/lessons/118668)|[등산코스 정하기](https://school.programmers.co.kr/learn/courses/30/lessons/118669)|[방금그곡](https://school.programmers.co.kr/learn/courses/30/lessons/17683)|
|**3주차**<br> (03.16 ~ 03.22)|[마법의 엘리베이터](https://school.programmers.co.kr/learn/courses/30/lessons/148653)|[미로 탈출](https://school.programmers.co.kr/learn/courses/30/lessons/159993)|[표현 가능한 이진트리](https://school.programmers.co.kr/learn/courses/30/lessons/150367)|[톱니바퀴](https://www.acmicpc.net/problem/14891)|[코드트리 빵](https://www.codetree.ai/training-field/frequent-problems/codetree-mon-bread/description?page=3&pageSize=20&username=)|
|**4주차**<br> (03.23 ~ 03.29)|[스티커](https://www.acmicpc.net/problem/9465)|[트리](https://www.acmicpc.net/problem/4803)|[인구 이동](https://www.acmicpc.net/problem/16234)|[뱀](https://www.acmicpc.net/problem/3190)|[꼬리잡기놀이](https://www.codetree.ai/training-field/frequent-problems/tail-catch-play/description?page=3&pageSize=20&username=)||

</details>

<details><summary>📎 4월 문제집</summary>

|주차|1|2|3|4|5|
|:---:|:---:|:---:|:---:|:---:|:---:|
|**1주차**<br> (03.30 ~ 04.12)|[정수 삼각형](https://www.acmicpc.net/problem/1932)|[뱀과 사다리 게임](https://www.acmicpc.net/problem/16928)|[트리의 지름](https://www.acmicpc.net/problem/1167)|[미세먼지 안녕!](https://www.acmicpc.net/problem/17144)|[팩맨](https://www.codetree.ai/training-field/frequent-problems/pacman/description?page=3&pageSize=20&username=)|
|**2주차**<br> (04.13 ~ 04.19)|[아기 상어](https://www.acmicpc.net/problem/16236)|[DSLR](https://www.acmicpc.net/problem/9019)|[트리의 순회](https://www.acmicpc.net/problem/2263)|[웜홀](https://www.acmicpc.net/problem/1865)|[방문 길이](https://school.programmers.co.kr/learn/courses/30/lessons/49994)|
|**3주차**<br> (04.20 ~ 04.26)|[2×n 타일링](https://www.acmicpc.net/problem/11726)|[최대 힙](https://www.acmicpc.net/problem/11279)|[숨바꼭질 3](https://www.acmicpc.net/problem/13549)|[후위 표기식](https://www.acmicpc.net/problem/1918)|[양궁대회](https://school.programmers.co.kr/learn/courses/30/lessons/92342)|
|**4주차**<br> (04.27 ~ 05.03)|[최고의 집합](https://school.programmers.co.kr/learn/courses/30/lessons/12938)|[땅따먹기](https://school.programmers.co.kr/learn/courses/30/lessons/12913)|[주차 요금 계산](https://school.programmers.co.kr/learn/courses/30/lessons/92341)|[조이스틱](https://school.programmers.co.kr/learn/courses/30/lessons/42860)|[택배 배달과 수거하기](https://school.programmers.co.kr/learn/courses/30/lessons/150369)||

</details>

🧷 5월 문제집

|주차|1|2|3|4|5|
|:---:|:---:|:---:|:---:|:---:|:---:|
|**1주차**<br> (05.04 ~ 05.10)|[혼자서 하는 틱택토](https://school.programmers.co.kr/learn/courses/30/lessons/160585)|[혼자 놀기의 달인](https://school.programmers.co.kr/learn/courses/30/lessons/131130)|[광물 캐기](https://school.programmers.co.kr/learn/courses/30/lessons/172927)|[행렬 테두리 회전하기](https://school.programmers.co.kr/learn/courses/30/lessons/77485)|[연속 펄스 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/161988)||
