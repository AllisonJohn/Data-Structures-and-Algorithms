import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTreeTester
{
	public void test()
	{
	    System.out.println("hello");
	    TreeDisplay display = new TreeDisplay();
		TreeNode t = new TreeNode("k");
		display.displayTree(t);
		display.setTester(this);
		System.out.println("k in tree?" + BSTUtilities.contains(t, "k", display));
		System.out.println("i in tree?" + BSTUtilities.contains(t, "i", display));
		System.out.println("Adding i to tree...");
		BSTUtilities.insert(t, "i", display);
		System.out.println("i in tree?" + BSTUtilities.contains(t, "i", display));
		System.out.println("Adding i to tree...");
		BSTUtilities.insert(t, "i", display);
		System.out.println("j in tree?" + BSTUtilities.contains(t, "j", display));
		System.out.println("Adding j to tree...");
		BSTUtilities.insert(t, "j", display);
		System.out.println("s in tree?" + BSTUtilities.contains(t, "s", display));
		System.out.println("Adding s to tree...");
		BSTUtilities.insert(t, "s", display);
		System.out.println("a in tree?" + BSTUtilities.contains(t, "a", display));
		System.out.println("Adding a to tree...");
		BSTUtilities.insert(t, "a", display);
		System.out.println("t in tree?" + BSTUtilities.contains(t, "t", display));
		System.out.println("Adding t to tree...");
		BSTUtilities.insert(t, "t", display);
		System.out.println("s in tree?" + BSTUtilities.contains(t, "s", display));
		System.out.println("Adding s to tree...");
		BSTUtilities.insert(t, "s", display);
	}
	 /**
    * called by the display object to send back the node value
    * when a node is visited
    */
    public void sendValue(Object value)
    {
        System.out.println(value);
    }
    public static void main(String[] args)
    {
        BinaryTreeTester allison  = new BinaryTreeTester();
        allison.test();
    }
}