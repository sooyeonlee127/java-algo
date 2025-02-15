def find_num(n):
    global N, nums
    start = 0
    end = N-1
    while end >= start:
        mid = (start + end) // 2
        if nums[mid] == n:
            return True
        elif nums[mid] < n:
            start = mid + 1
        else:
            end = mid - 1
    return False


N = int(input())
nums = list(map(int, input().split()))
nums.sort()
M = int(input())
x_nums = list(map(int, input().split()))

for x in x_nums:
    answer = find_num(x)
    if answer:
        print(1)
    else:
        print(0)