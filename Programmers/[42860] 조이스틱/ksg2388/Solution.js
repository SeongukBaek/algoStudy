function solution(name) {
    let sum = 0;
    let j = 0;
    for (let i = 0; i < name.length; i++) {
        let diff = name[i].charCodeAt() - 'A'.charCodeAt();
        sum += diff > 13 ? 26 - diff : diff;
    }

    let minMove = name.length - 1;
    for (let i = 1; i < name.length; i++) {
        if (name[i] === 'A') {
            for (j = i + 1; j < name.length; j++) {
                if (name[j] !== 'A') {
                    break;
                }
            }
            const left = i - 1; // A가 나오지 직전까지
            const right = name.length - j;
            minMove = Math.min(minMove, left > right ? left + right * 2 : left * 2 + right);

            i = j;
        }
    }
    return sum + minMove;
}
