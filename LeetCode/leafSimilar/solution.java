package leafSimilar;

import java.util.ArrayList;
import java.util.List;

public class solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        getLeaves(root1, list1);
        getLeaves(root2, list2);
        return list1.equals(list2);
    }

    public void getLeaves(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if(root.left==null && root.right == null){
            list.add(root.val);
            return ;
        }
        getLeaves(root.left, list);
        getLeaves(root.right, list);
    }
}
