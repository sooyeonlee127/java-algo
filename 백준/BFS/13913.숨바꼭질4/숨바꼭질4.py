from collections import deque

N, K = map(int, input().split())
MAP = [0]*100001
visited = [0]*100001
q = deque()
q.appendleft(N)
answer = []
while q:
    now = q.popleft()
    if now == K:
        answer.append(now)
        tmp = now
        for i in range(MAP[now]):
            answer.append(visited[tmp])
            tmp = visited[tmp]
        break
    for i in (now-1, now+1, now*2):
        if i >= 0 and i <= 100000 and MAP[i] == 0:
            MAP[i] = MAP[now] + 1
            visited[i] = now
            q.append(i)
    
print(MAP[K])
print(' '.join(map(str, answer[::-1])))