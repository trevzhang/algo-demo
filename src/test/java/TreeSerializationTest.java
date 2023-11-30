/**
 * @author trevor
 * @since 2023/11/29 12:03
 **/
import com.trevzhang.demo.TreeSerialization;
import com.trevzhang.demo.TreeSerialization.TreeNode;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeSerializationTest {

    @Test
    public void testSerializeAndDeserialize() {
        // 创建一个二叉搜索树
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println(root.toString());

        TreeSerialization ts = new TreeSerialization();
        String serialized = ts.serialize(root); // 序列化二叉搜索树

        System.out.println(serialized);

        TreeNode deserialized = ts.deserialize(serialized); // 反序列化字符串为二叉搜索树

        System.out.println(deserialized.toString());

        // 验证反序列化后的二叉搜索树与原始二叉搜索树相同
        assertTrue(isSameTree(root, deserialized));
    }

    // 判断两个二叉树是否相同
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
