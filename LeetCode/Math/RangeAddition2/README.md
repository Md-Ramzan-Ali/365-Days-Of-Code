# Range Addition II (Matrix Maximum Integer Counter)

This repository contains a highly optimized Java solution for the "Range Addition II" problem.

## 📝 Problem Description

You are given an `m x n` matrix `M` initialized with all `0`'s and an array of operations `ops`, where `ops[i] = [ai, bi]` means `M[x][y]` should be incremented by one for all `0 <= x < ai` and `0 <= y < bi`.

You need to count and return the number of maximum integers in the matrix after performing all the operations.

### Example 1:
- **Input:** `m = 3, n = 3, ops = [[2,2],[3,3]]`
- **Output:** `4`
- **Explanation:** The maximum integer in `M` is `2`, and there are four of it in `M` (located in the overlapping $2 \times 2$ sub-matrix). So return `4`.

---

## 💡 Intuition & Approach

Instead of simulating the matrix and performing the operations (which would cause **Time Limit Exceeded** or **Memory Limit Exceeded**), this solution uses a **Geometrical / Logical Approach**.

Since every operation `[ai, bi]` starts from the top-left corner `(0, 0)` and increments a sub-matrix of size `ai * bi`, the maximum integers will always be located in the region where **all operations overlap**.

Therefore, the problem reduces to finding the **minimum row size (`ai`)** and the **minimum column size (`bi`)** among all operations. The total number of maximum integers will simply be the area of this intersecting region:
$$\text{Result} = \text{min\_row} \times \text{min\_col}$$

---

## 🚀 Java Solution

```java
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        // If there are no operations, the entire m x n matrix remains 0,
        // which is the maximum value. So, the count is m * n.
        int min_row = m;
        int min_col = n;
        
        // Find the minimum row and column across all operations
        for (int[] op : ops) {
            min_row = Math.min(min_row, op[0]);
            min_col = Math.min(min_col, op[1]);
        }
        
        // The area of the overlapping region contains the maximum integers
        return min_row * min_col;
    }
}