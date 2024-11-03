# [134. Gas Station](https://leetcode.com/problems/gas-station/)

## 代码关键逻辑说明

1. **第一步**：计算 `delta` 数组和总和 `sum`。
   
   ```java
   for (int i = 0; i < delta.length; i++) {
       delta[i] = gas[i] - cost[i];
       sum += delta[i];
   }
   ```
   `delta[i]` 表示在第 `i` 个加油站，加油量 `gas[i]` 减去到下一个加油站的耗油量 `cost[i]` 的结果。通过计算 `sum`，可以判断是否能完成一圈。如果 `sum < 0`，总油量不足，肯定无法完成一圈，因此返回 `-1`。
   
2. **第二步**：找到一个可行的起点。
   ```java
   int currentGas = 0;
   int startingIndex = 0;
   
   for (int i = 0; i < delta.length; i++) {
       currentGas += delta[i];
       if (currentGas < 0) {
           currentGas = 0;
           startingIndex = i + 1;
       }
   }
   ```
   这里的循环目的是寻找合适的起始加油站。

   - `currentGas` 记录从 `startingIndex` 开始累计的油量。如果在遍历过程中 `currentGas < 0`，说明从 `startingIndex` 到 `i` 的路径无法继续下去。
   - 当 `currentGas < 0` 时，表示从当前的 `startingIndex` 开始出发无效，因为无法走完环路。
   - 此时，更新 `startingIndex` 为 `i + 1`，并将 `currentGas` 重置为 `0`，重新尝试从下一个加油站开始出发。

## 为什么这样可以找到答案？

在遍历完成时，如果 `sum >= 0`，说明环路中总油量充足，环路中剩下的油量 `currentGas` 不足时可以重新选择起点。

在遍历完成后，如果 `sum >= 0`，那么 `startingIndex` 是一个可行的起点，可以绕完整个环路。具体原因如下：

### 1. 为什么遍历完成后找到的 `startingIndex` 是可行的起点？

代码逻辑确保了当 `currentGas < 0` 时，会将 `startingIndex` 移动到下一个位置（即 `i + 1`），并从那里开始重新累计油量。这意味着，从 `startingIndex` 开始到数组末尾的部分总和（即 `delta[startingIndex]` 到 `delta[delta.length - 1]`）在累加时不会低于零，否则我们早已更新起点。

同时，由于 `sum >= 0`，即整个 `delta` 数组的总和不小于零，因此从 `startingIndex` 开始出发可以涵盖剩余的路径并回到起点。

### 2. 如果 `startingIndex == length - 1`，也可以作为结果吗？

是的，`startingIndex` 等于 `length - 1` 时同样是一个有效结果。因为如果在数组的最后一个元素时满足 `currentGas >= 0`，则意味着从最后一个加油站出发，绕行一周依然可以回到起点。  

### 总结

遍历结束时的 `startingIndex` 必然是满足绕行一周的起点，因为：

- 从 `startingIndex` 开始，`currentGas` 不会中途变负，因此可以走到数组末尾。
- 整体油量总和 `sum >= 0`，保证了可以绕行一周