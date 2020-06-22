/**
 * MyTreeSet is used to more easily test various BSTUtilities methods
 * It holds on to a tree, and the methods it includes are:
 * size, contains, add, remove, toString
 * @param <E> the type of tree
 * @version 1-7-18
 * @author Allison John
 */
public class MyTreeSet<E>
{
	private TreeNode root;
	private int size;
	private TreeDisplay display;

	/**
	 * Constructor for MyTreeSet creates a new tree set with 
	 * a null binary search tree root, a size of zero and a display.
	 */
	public MyTreeSet()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();

		//wait 1 millisecond when visiting a node
		display.setDelay(1);
	}

	/**
	 * Gets the size of the tree
	 * @return the size
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Sees if an object is in the tree using BSTUtilities
	 * @param obj the value to look for
	 * @return boolean whether it is in the tree or not
	 */
	public boolean contains(Object obj)
	{
		return BSTUtilities.contains(root, (Comparable) obj, display);
	}

	/**
	 * Adds the new object to binary search tree
	 * @param obj the object to add
	 * @return boolean if the object was added
	 */
	public boolean add(E obj)
	{
		if (contains(obj))
		{
		    display.displayTree(root);
		    return false;
		}
		display.displayTree(root);
		root=BSTUtilities.insert(root, (Comparable) obj, display);
		size++;
		return true;
	}

	/**
	 * if obj is present in this set, removes obj and 
	 * returns true; otherwise returns false
	 * @param obj the object to remove
	 * @return if it was removed
	 */
	public boolean remove(Object obj)
	{
		if (!contains(obj))
		{
		    display.displayTree(root);
		    return false;
		}
		display.displayTree(root);
		root=BSTUtilities.delete(root, (Comparable) obj, display);
		size--;
		return true;
	}

	/**
	 * Prints the tree as a string
	 * @return the tree string
	 */
	public String toString()
	{
		return toString(root);
	}

	/**
	 * Prints the tree as a string
	 * @param t the tree
	 * @return the tree string
	 */
	private String toString(TreeNode t)
	{
	    return "[" + helpToString(t) + "]";
	}
	
	/**
	 * helper method for toString for better formatting
	 * @param t the tree to print
	 * @return the string
	 */
	private String helpToString(TreeNode t)
	{
		if (t == null)
			return "";
		return helpToString(t.getLeft()) + t.getValue() + ", " + helpToString(t.getRight());
	}
}