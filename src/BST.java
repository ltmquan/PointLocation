/* Name: Quan Luu
 * Student ID: 31529099
 * NetID: qluu2
 * Lab section: MW 6h15 - 7h30
 * Project: 2
 * Description: from lab 10
 */
public interface BST<T extends Comparable<T>> {
	public void insert(T x);

	public void delete(T x);

	public boolean lookup(T x);

	public void printPreOrder();

	public void printInOrder();

	public void printPostOrder();
}