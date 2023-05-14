function solution(cards) {
  let firstBox = 0;
  let secondBox = 0;
  let count = 0;
  let cardList = Array.from({ length: cards.length }, () => 0);

  cards.forEach((card, idx) => {
    if (cardList[idx] === 0) {
      count = 0;
      openBox(idx);

      // 첫번째 박스가 빈 경우 값 저장
      if (firstBox === 0) {
        firstBox = count;
        return;
      }
      // 두번째 박스가 빈 경우 값 저장
      if (secondBox === 0) {
        secondBox = count;
        if (secondBox > firstBox) {
          [firstBox, secondBox] = [secondBox, firstBox];
        }
        return;
      }
      // 새로운 값이 두번 째 박스의 값보다 큰 경우
      if (count > secondBox) {
        secondBox = count;
        if (secondBox > firstBox) {
          [firstBox, secondBox] = [secondBox, firstBox];
        }
      }
    }
  });

  function openBox(idx) {
    cardList[idx] = 1;
    count++;

    if (cardList[cards[idx] - 1] !== 0) {
      return;
    }

    cardList[cards[idx] - 1] = 1;
    openBox(cards[idx] - 1);
  }

  return firstBox * secondBox;
}
