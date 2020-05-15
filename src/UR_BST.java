/* Name: Quan Luu
 * Student ID: 31529099
 * NetID: qluu2
 * Lab section: MW 6h15 - 7h30
 * Project: 2
 * Description: from lab 10
 */
public class UR_BST{
	
	MyTreeNode root;
	
	public UR_BST() {
		root = new MyTreeNode();
	}
	
	public void insert(Line x) {
		
		//if not already in BST -> insert
		if (root.data != null) {
			if (root.lookup(x)) {
				return;
			}
		}
		root.insert(x);
	}

	public boolean lookup(Line x) {
		if (root.data == null) {
			return false;
		}
		return root.lookup(x);
	}

	public void printPreOrder() {
		if (root.data == null) {
			return;
		}
		root.printPreOrder();
		System.out.println();
	}

	public void printInOrder() {
		if (root.data == null) {
			return;
		}
		root.printInOrder();	
		System.out.println();
	}

	public void printPostOrder() {
		if (root.data == null) {
			return;
		}
		root.printPostOrder();
		System.out.println();
	}
	public int countNode() {
		if (root.data == null) {
			return 0;
		}
		return root.countNode();
	}
	
	public int countExNode() {
		if (root.data == null) {
			return 0;
		}
		return root.countExNode();
	}
	
	public int getInPathLen() {
		if (root.data == null) {
			return 0;
		}
		return root.getInPathLen();
	}
	
	public int getExPathLen() {
		if (root.data == null) {
			return 0;
		}
		return root.getExPathLen();
	}
	
	public Line test(Line x) {
		if (root.data == null) {
			return null;
		}
		return root.test(x);
	}
}
