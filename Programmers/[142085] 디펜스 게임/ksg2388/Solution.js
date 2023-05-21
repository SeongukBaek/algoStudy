class priorityQueue {
  constructor() {
    this.heap = [];
  }

  push(value) {
    const heap = this.heap;
    heap.push(value);
    let index = heap.length - 1;
    let parent = Math.floor((index - 1) / 2);

    while (index > 0 && heap[index] < heap[parent]) {
      [heap[index], heap[parent]] = [heap[parent], heap[index]];
      index = parent;
      parent = Math.floor((index - 1) / 2);
    }
  }

  pop() {
    const heap = this.heap;
    if (heap.length <= 1) {
      return heap.pop();
    }

    const output = heap[0];
    heap[0] = heap.pop();
    let index = 0;

    while (index * 2 + 1 < heap.length) {
      let left = index * 2 + 1,
        right = index * 2 + 2,
        next = index;

      if (heap[next] > heap[left]) {
        next = left;
      }

      if (right < heap.length && heap[next] > heap[right]) {
        next = right;
      }

      if (next === index) {
        break;
      }

      [heap[index], heap[next]] = [heap[next], heap[index]];
      index = next;
    }

    return output;
  }
}

function solution(n, k, enemy) {
  let round = 0;
  let leftSoldier = n; // 현재 남은 병사의 수
  const pq = new priorityQueue();
  let capacity = 0; // 현재 사용한 병사의 수

  enemy.slice(0, k).forEach((e) => pq.push(e));

  for (let i = k; i < enemy.length; i++) {
    pq.push(enemy[i]);
    let temp = pq.pop();
    if (temp + capacity > n) {
      return i;
    }
    capacity += temp;
  }
  return enemy.length;
}
