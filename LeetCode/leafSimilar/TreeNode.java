package leafSimilar;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val ;
    TreeNode left ;
    TreeNode right ;
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(){}
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

