const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let n = Number(input[0]);
let indegrees = new Array(n + 1).fill(0);
let graph = new Array(n + 1);
let time = new Array(n + 1);

for (let i = 0; i < graph.length; i++) {
  graph[i] = [];
}
for (let i = 0; i < n; i++) {
  let arr = input[i + 1].split(' ').map(Number);
  for (let j = 0; j < arr[1]; j++) {
    graph[arr[j + 2]].push(i + 1);
    indegrees[i + 1]++;
  }
  time[i + 1] = arr[0];
}

let queue = [];
let finish_time = new Array(n + 1).fill(0);
for (let i = 1; i <= n; i++) {
  if (indegrees[i] === 0) {
    queue.push(i);
    finish_time[i] = time[i];
  }
}

while (queue.length) {
  let x = queue.shift();
  for (let next of graph[x]) {
    indegrees[next]--;
    finish_time[next] = Math.max(
      finish_time[next],
      finish_time[x] + time[next]
    );
    if (indegrees[next] === 0) queue.push(next);
  }
}
console.log(Math.max(...finish_time));
