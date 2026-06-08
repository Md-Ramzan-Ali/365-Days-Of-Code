# Leaf-Similar Trees (LeetCode 872)

## 📝 Problem Description

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a **leaf value sequence**.

Two binary trees are considered *leaf-similar* if their leaf value sequence is the same. This solution determines if two given binary trees with head nodes `root1` and `root2` are leaf-similar.

---

## 💡 Intuition & Approach

The core idea is to traverse both trees, collect their leaf nodes (nodes with no left or right children) from left to right, and then compare the two sequences.

1. **Depth-First Search (DFS):** We use a helper method `getLeaves` to traverse the tree using a pre-order/in-order fashion. By exploring the left subtree before the right subtree, we naturally maintain the **left-to-right order** of the leaves.
2. **Base Cases:** - If both roots are `null`, they are technically identical/similar (`true`).
    - If one of them is `null` and the other is not, they can't be similar (`false`).
3. **Comparison:** We store the leaf values of both trees into two separate `ArrayList` objects and compare them using Java's built-in `.equals()` method, which checks both the size and the sequential elements of the lists.

---

## 🛠️ Java Implementation

```java
package leafSimilar;

import java.util.ArrayList;
import java.util.List;

public class solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // Base edge cases
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // Collect leaves for both trees
        getLeaves(root1, list1);
        getLeaves(root2, list2);
        
        // Compare sequences
        return list1.equals(list2);
    }

    public void getLeaves(TreeNode root, List<Integer> list) {
        if (root == null) return;
        
        // If it's a leaf node, add to the sequence
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        
        // Traversal order: Left -> Right
        getLeaves(root.left, list);
        getLeaves(root.right, list);
    }
}