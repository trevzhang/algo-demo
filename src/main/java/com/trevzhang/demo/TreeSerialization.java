package com.trevzhang.demo;

/**
 * @author trevor
 * @since 2023/11/29 12:01
 **/

/**
 * 序列化可以将对象存储在文件或内存缓冲区中，以便通过RPC调用等传输，并稍后在同一个或另一个服务中重建。
 * 设计一个算法来序列化和反序列化二叉搜索树，使二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 * <p>
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 *
 * @author trevor
 * @since 2023/11/29 12:01
 */
public class TreeSerialization {

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        // 可视化展示二叉树的结构
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            toStringHelper(this, "", sb);
            return sb.toString();
        }

        private void toStringHelper(TreeNode node, String prefix, StringBuilder sb) {
            if (node == null) {
                return;
            }

            sb.append(prefix)
                .append("├── ")
                .append(node.val)
                .append("\n");

            if (node.left != null && node.right != null) {
                toStringHelper(node.left, prefix + "│   ", sb);
                toStringHelper(node.right, prefix + "│   ", sb);
            } else if (node.left != null) {
                toStringHelper(node.left, prefix + "    ", sb);
            } else if (node.right != null) {
                toStringHelper(node.right, prefix + "    ", sb);
            }
        }
    }


    private static final String SEP = ","; // 分隔符
    private static final String NULL = "#"; // 空节点标识

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    // 前序遍历序列化二叉搜索树
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP); // 将空节点值加入字符串并使用分隔符分隔
            return;
        }
        sb.append(root.val).append(SEP); // 将节点值加入字符串并使用分隔符分隔
        serializeHelper(root.left, sb); // 递归序列化左子树
        serializeHelper(root.right, sb); // 递归序列化右子树
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] values = data.split(SEP); // 将字符串按照分隔符分割为节点值数组
        int[] index = new int[1]; // 使用数组来保存当前遍历到的节点值的索引
        return deserializeHelper(values, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 前序遍历反序列化二叉搜索树
    private TreeNode deserializeHelper(String[] values, int[] index, int min, int max) {
        if (index[0] >= values.length || values[index[0]].equals(NULL)) {
            return null; // 遍历到末尾或者遇到空节点时返回null
        }
        int val = Integer.parseInt(values[index[0]]); // 将字符串转换为节点值
        if (val < min || val > max) {
            return null; // 节点值超出范围时返回null
        }
        index[0]++; // 更新索引值，指向下一个节点值
        TreeNode root = new TreeNode(val); // 创建当前节点
        root.left = deserializeHelper(values, index, min, val); // 递归反序列化左子树
        root.right = deserializeHelper(values, index, val, max); // 递归反序列化右子树
        return root; // 返回当前节点
    }
}
