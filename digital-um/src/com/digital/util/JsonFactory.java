package com.digital.util;

import java.util.ArrayList;
import java.util.List;

import com.digital.pojo.*;

public class JsonFactory {
	public static List<TreeNode> buildtree(List<TreeNode> nodes, int id) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (TreeNode treeNode : nodes) {
			TreeNode node = new TreeNode();
			node.setId(treeNode.getId());
			node.setText(treeNode.getText());
			if (id == treeNode.getFid()) {
				// 递给调用buildtree方法给TreeNode中的children属性赋值
				node.setChildren(buildtree(nodes, node.getId()));
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}
}
