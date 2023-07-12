# [3584] 가장 가까운 공통 조상

## :pushpin: **Algorithm**

스택, 트리

## :round_pushpin: **Logic**
- parent라는 int배열에 해당 인덱스 숫자의 부모를 저장한다.
- 공통 조상을 찾아야하는 두 숫자 각 stack을 만들어서 부모를 root까지 쭉 push한다.

```java
static int getAncestor(int n1, int n2){
    int answer = 0;

    // 두 스택에서 하나씩 빼면서 같은 수가 나오면 공통 조상
    // --> 마지막에 나오는 공통 조상이 제일 가까운 공통 조상

    while(!p1.isEmpty() && !p2.isEmpty()){ // 둘 중 하나가 빌 때까지
        int num1 = p1.pop();
        int num2 = p2.pop();

        if(num1 != num2){ // 부모가 다르면 멈춤
            break;
        }

        answer = num1;
    }

    return answer;
}
```
- 두 스택에서 하나씩 빼면서 비교 후 두 숫자가 같으면 공통 조상이므로 answer에 저장하고, 두 수가 같지 않을 때까지 반복한다.

## :black_nib: **Review**
- 처음에는 트리를 리스트로 구현하려고 했는데 어떤 숫자에 자식이 있는지 알 수 없기 때문에 리스트를 미리 만들어 두는 것은 비효율적이라 생각해서 다른 방법을 생각하다가 조상을 찾는 것이기 때문에 부모만 저장해두고 타고타고 올라가면 된다는 생각이 들어 parent 배열을 만들었다.
- 부모를 저장하는 parent배열은 만들었는데 막상 끝에서부터 부모를 비교하면서 올라가려니 부모의 depth가 다를 수 있다는 생각이 들었다. 어떻게 해결할 지 고민하다가 결국 다른 사람의 답을 참고했다.
- stack에 넣어두면 가장 위에 있는 root가 top에 오기 때문에 같은 depth끼리 비교할 수 있게 된다. 사람들은 정말 똑똑하다....