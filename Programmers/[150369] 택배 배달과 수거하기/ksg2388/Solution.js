function solution(cap, n, deliveries, pickups) {
    let answer = 0;
    let dStack = [];
    let pStack = [];
    
    // 배달해야할 택배들 저장
    deliveries.map((count, index) => {
        for (let i = 0; i < count; i++) {
            dStack.push(index + 1);
        }
    })
    
    // 수거해야할 택배들 저장
    pickups.map((count, index) => {
        for (let i = 0; i < count; i++) {
            pStack.push(index + 1);
        }
    })
    
    while (dStack.length || pStack.length) {
        const dLength = dStack.length ? dStack[dStack.length - 1] : 0;
        const pLength = pStack.length ? pStack[pStack.length - 1] : 0;
        const length = Math.max(dLength, pLength);
        // 트럭의 용량만큼 택배 배달 및 수거
        for (let i = 0; i < cap; i++) {
            dStack.pop();
            pStack.pop();
        }
        answer += length * 2;
    }
    
    return answer;
}