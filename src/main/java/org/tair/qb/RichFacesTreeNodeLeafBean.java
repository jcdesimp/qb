package org.tair.qb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;
import org.richfaces.event.TreeToggleEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

//import org.richfaces.book.examples.model.SimpleTreeNodeTypes;
import org.tair.qb.model.RichFacesTreeNode;
import org.tair.qb.model.SimpleTreeNodeTypes;
import org.tair.qb.model.TypifiedRichFacesTreeNode;

import java.io.Serializable;

@ManagedBean
@ViewScoped
public class RichFacesTreeNodeLeafBean implements Serializable {

	private TreeNode rootNode = null;
	private List<String> selection = new ArrayList<String>();
	private String toggledNodeData = null;
	int i = 0 ;
	private String list = null;

	public void nodeToggled(TreeToggleEvent event) {
		toggledNodeData = ((RichFacesTreeNode)((UITree)event.getComponent()).getRowData()).getData();
	}

	public void selectionChanged(TreeSelectionChangeEvent event) {
		
		//selection.clear();
		UITree tree = (UITree) event.getComponent();
		Object storedRowKey = tree.getRowKey();
		//i = i + 1;
		for (Object rowKey : event.getNewSelection()) {
			tree.setRowKey(rowKey);
			
			//selection.add(((RichFacesTreeNode) tree.getRowData()).getData());
			selection.add(((TypifiedRichFacesTreeNode) tree.getRowData()).getData());
		}
		tree.setRowKey(storedRowKey);
	}

	public void initNodes() {
		// fake node. all the direct children nodes will be root nodes.
		rootNode = new TreeNodeImpl();

		//RichFacesTreeNode node = new RichFacesTreeNode("Desktop Type");
		TypifiedRichFacesTreeNode node = new TypifiedRichFacesTreeNode("SELECT", SimpleTreeNodeTypes.NODE);
		node.addChild("1_1", new TypifiedRichFacesTreeNode("r.name", SimpleTreeNodeTypes.LEAF));
		node.addChild("1_2", new TypifiedRichFacesTreeNode("t.primarySymbol", SimpleTreeNodeTypes.LEAF));
		node.addChild("1_3", new TypifiedRichFacesTreeNode("t.sequence", SimpleTreeNodeTypes.LEAF));
		rootNode.addChild("1", node);

		//node = new RichFacesTreeNode("Customer Reviews");
		
		node = new TypifiedRichFacesTreeNode("FROM", SimpleTreeNodeTypes.NODE);
		//node.addChild("2_1", new TypifiedRichFacesTreeNode("TopRated", SimpleTreeNodeTypes.LEAF));
		rootNode.addChild("2", node);
		
		
		TypifiedRichFacesTreeNode node2 = new TypifiedRichFacesTreeNode("JOIN", SimpleTreeNodeTypes.NODE);
		node2.addChild("2_1_1", new TypifiedRichFacesTreeNode("Region r", SimpleTreeNodeTypes.LEAF));
		node2.addChild("2_1_2", new TypifiedRichFacesTreeNode("Transcript t on r.tairObjectID = t.tairObjectId", SimpleTreeNodeTypes.LEAF));
		node.addChild("2_1", node2);
		
		/*
		node = new RichFacesTreeNode("Current Offers");
		node.addChild("3_1", new RichFacesTreeNode("On Sale"));
		node.addChild("3_2", new RichFacesTreeNode("Special Offers"));
		node.addChild("3_3", new RichFacesTreeNode("Package Deals"));
		node.addChild("3_4", new RichFacesTreeNode("Financing offers"));
		node.addChild("3_5", new RichFacesTreeNode("Outlet Center"));
		rootNode.addChild("3", node);

		node = new RichFacesTreeNode("Availability");
		node.addChild("4_1", new RichFacesTreeNode("In store & Online"));
		node.addChild("4_2", new RichFacesTreeNode("Online only"));
		rootNode.addChild("4", node);
		*/
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public TreeNode getRootNode() {
		if (rootNode == null) {
			initNodes();
		}
		return rootNode;
	}

	public List<String> getSelection() {
		return selection;
	}

	public void setSelection(List<String> selection) {
		this.selection = selection;
	}

	public void setToggledNodeData(String toggledNodeData) {
		this.toggledNodeData = toggledNodeData;
	}

	public String getToggledNodeData() {
		return toggledNodeData;
	}
	
	public String getList() {
		//selection.add("Test ");
		//selection.add(" Testtwo ");
		StringBuilder str = new StringBuilder("");
		for (String item: this.selection) {
			str.append(item);
			str.append(" ");
		}
		String str2 = str.toString();	
		this.list = str2;
		return this.list;
		
		
	}
	
}
