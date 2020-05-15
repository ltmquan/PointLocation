/* Name: Quan Luu
 * Student ID: 31529099
 * NetID: qluu2
 * Lab section: MW 6h15 - 7h30
 * Project: 2
 * Description: from lab 10, with a few adjustments
 */
public class MyTreeNode {
	
	//I changed from this being generic to being specifically based on Line class
	public Line data;
	public MyTreeNode leftChild;
	public MyTreeNode rightChild;
	public MyTreeNode parent;
	
	public void insert(Line x) {
		
		//test if no data
		if (data == null) {
			data = x;
			
		//this is for when two line intersects, and point 1 of x is on the left
		} else if (x.compareTo(data) == 2) {
			Point intersect = x.intersect(data);
			if (leftChild == null) {
				MyTreeNode left = new MyTreeNode();
				left.parent = this;
				leftChild = left;
			}
			leftChild.insert(new Line(intersect, x.p2, x.name));
			
			if (rightChild == null) {
				MyTreeNode right = new MyTreeNode();
				right.parent = this;
				rightChild = right;
			}
			rightChild.insert(new Line(x.p1, intersect, x.name));
			
		//this is for when two line intersects, and point 1 of x is on the right
		} else if (x.compareTo(data) == -2) {
			Point intersect = x.intersect(data);
			if (leftChild == null) {
				MyTreeNode left = new MyTreeNode();
				left.parent = this;
				leftChild = left;
			}
			leftChild.insert(new Line(x.p1, intersect, x.name));
			
			if (rightChild == null) {
				MyTreeNode right = new MyTreeNode();
				right.parent = this;
				rightChild = right;
			}
			rightChild.insert(new Line(intersect, x.p2, x.name));
			
		//if smaller than parent -> left child
		} else if (x.compareTo(data) < 0) {
			if (leftChild == null) {
				MyTreeNode left = new MyTreeNode();
				left.parent = this;
				leftChild = left;
			}
			leftChild.insert(x);
			
		//if bigger than parent -> right child
		} else {
			if (rightChild == null) {
				MyTreeNode right = new MyTreeNode();
				right.parent = this;
				rightChild = right;
			}
			rightChild.insert(x);
		}
	}
	
	//print data, left, right
	public void printPreOrder() {
		if (data == null) {
			return;
		}
		
		System.out.print(data + " ");
		
		if (leftChild != null) {
			leftChild.printPreOrder();
		}
		
		if (rightChild != null) {
			rightChild.printPreOrder();
		}
	}
	
	//print left, data, right
	public void printInOrder() {
		if (data == null) {
			return;
		}
		
		if (leftChild != null) {
			leftChild.printInOrder();
		}
		
		System.out.print(data + " ");
		
		if (rightChild != null) {
			rightChild.printInOrder();
		}
	}
	
	//print left, right, data
	public void printPostOrder() {
		if (data == null) {
			return;
		}
		
		if (leftChild != null) {
			leftChild.printPostOrder();
		}
		
		if (rightChild != null) {
			rightChild.printPostOrder();
		}
		
		System.out.print(data + " ");
	}
	
	public boolean lookup(Line x) {
		if (x.compareTo(data) == 0) {
			return true;
		}
		
		//if x smaller than data -> look up on the left
		if (x.compareTo(data) < 0) {
			if (leftChild != null) {
				return leftChild.lookup(x);
			}
			return false;
			
		//if x bigger than data -> look up on the right
		} else {
			if (rightChild != null) {
				return rightChild.lookup(x);
			}
			return false;
		}
	}
	
	//a simple function I added to count internal nodes
	public int countNode() {
		if (data == null) {
			return 0;
		}
		int count = 1;
		if (leftChild != null) {
			count += leftChild.countNode();
		}
		if (rightChild != null) {
			count += rightChild.countNode();
		}
		return count;
	}
	
	//a simple function to count external nodes
	public int countExNode() {
		return countNode() + 1;
	}
	
	//a simple function I added to count the internal path length
	public int getInPathLen() {
		if (data == null) {
			return 0;
		}
		
		int length = 0;
		
		MyTreeNode comp = this;
		while (comp.parent != null) {
			comp = comp.parent;
			length++;
		}
		if (leftChild != null) {
			length += leftChild.getInPathLen();
		} 
		if (rightChild != null) {
			length += rightChild.getInPathLen();
		}
		return length;
	}
	
	//a simple function to count the external path length
	public int getExPathLen() {
		return getInPathLen() + 2*countNode();
	}
	
	//a function I added to test two given points
	public Line test(Line x) {
		if (data == null) {
			return null;
		}
		
		if (data.compareTo(x) == 2 || data.compareTo(x) == -2) {
			return data;
		} else if (x.compareTo(data) < 0) {
			if (leftChild != null) {
				return leftChild.test(x);
			}
			return null;
		} else {
			if (rightChild != null) {
				return rightChild.test(x);
			}
			return null;
		}
	}
}