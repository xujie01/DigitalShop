package com.digital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.pojo.*;
import com.digital.service.*;
import com.digital.util.JsonFactory;

@Controller
@RequestMapping("/functions")
public class FunctionsController {
	@Autowired
	private FunctionsService functionsService;
	
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeNode> getTree(){
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		List<Functions> fs=functionsService.getAllFunctions();
		for (Functions f : fs) {
			TreeNode treeNode=new TreeNode();
		      treeNode.setId(f.getId());
		      treeNode.setFid(f.getParentid());
		      treeNode.setText(f.getName());
		      nodes.add(treeNode);
		}		
	    List<TreeNode> treeNodes=JsonFactory.buildtree(nodes,0);
	    return treeNodes;		
	}
}
