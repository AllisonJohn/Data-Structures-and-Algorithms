/**
 * a collection of static methods for operating on binary search trees:
 * contains, insert, delete
 * @author Allison John
 * @version 1/7/18
 */
public abstract class BSTUtilities
{
    /**
     * Determines if the tree contains a value
     * @param t the binary search tree
     * @param x the item to look for
     * @param display the tree display
     * @return whether or not the tree contains the value
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns true if t contains the value x;
                       otherwise, returns false
     */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        display.visit(t);
        if (t==null)
        { 
            return false;
        }
        Object v = t.getValue();
        if (v==null)
        {
            return false;
        }
        else if (x.compareTo(v)<0)
        {
            return contains(t.getLeft(), x, display);
        }
        else if (x.compareTo(v)>0)
        {
            return contains(t.getRight(), x, display);
        }
        else if (x.compareTo(v)==0)
        {
            return true;
        }
        System.out.println("Cleanup on aisle 39!");
        return false;
    }

    /**
     * Inserts the value into the binary search tree at the correct location
     * and returns its node
     * @param t the binary search tree
     * @param x the value
     * @param display the display
     * @return the tree with the inserted value
     * @precondition  t is a binary search tree in ascending order
     * postcondition: if t is empty, returns a new tree containing x;
                      otherwise, returns t, with x having been inserted
                      at the appropriate position to maintain the binary
                      search tree property; x is ignored if it is a
                      duplicate of an element already in t; only one new
                      TreeNode is created in the course of the traversal
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        display.visit(t);
        if (t==null)
        {
            return new TreeNode(x);
        }
        else if (x.compareTo(t.getValue())>0)
        {
            t.setRight(insert(t.getRight(), x, display));
        }
        else if (x.compareTo(t.getValue())<0)
        {
            t.setLeft(insert(t.getLeft(), x, display));
        }
        return t;
    }
    
    /*
     * Expands the given node by creating two more of it on the right and left
     * @param t the tree node
     */
    /*
    public TreeNode expandNode(TreeNode t)
    {
        if (t!=null)
        {
         TreeNode r = new TreeNode(t.getValue(), null, t.getRight());
         TreeNode l = new TreeNode(t.getValue(), null, t.getLeft());
         t.setRight(r);
         t.setLeft(l);
        }
        return t;
    }
    
    public void growTree(TreeNode t, Object val)
    {
        helpGrow(t, val);
    }
    
    private TreeNode helpGrow(TreeNode t, Object val)
    {
        if (t==null)
        {
            return t;
        }
        TreeNode r = helpGrow(t.getRight(), val);
        TreeNode l = helpGrow(t.getLeft(), val);
        t.setRight(r);
        t.setLeft(l);
        if (t.getValue()==val)
        {
            expandNode(t);
        }
        return t;
    }
    */

    /**
     * Deletes the node and aranges the remaining nodes to maintain the
     * binary search tree.
     * @param t the node to delete
     * @param display the tree display
     * @return the new tree
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns a pointer to a binary search tree,
                      in which the value at node t has been deleted
                      (and no new TreeNodes have been created)
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        if (t.getLeft()==null && t.getRight()==null)
        {
            return null;
        }
        if (t.getLeft()==null)
        {
            return t.getRight();
        }
        if (t.getRight()==null)
        {
            return t.getLeft();
        }
        else
        {
            TreeNode focusNode = (TreeNode) t;
            Comparable x = (Comparable) focusNode.getValue();
            TreeNode replacement = (TreeNode) TreeUtil.leftmost(focusNode.getRight());
            Object value = replacement.getValue();
            focusNode.setValue(value);
            Comparable d = (Comparable) value;
            focusNode.setRight(delete(focusNode.getRight(), d, display));
            return focusNode;
        }
    }

    /**
     * Deletes the node with the value from the binary search tree
     * @param t the tree
     * @param x the value
     * @param display the display
     * @return the tree without the node
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns a pointer to a binary search tree,
                      in which the value x has been deleted (if present)
                      (and no new TreeNodes have been created)
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        if (contains(t, x, display))
        {
            if(x.compareTo(t.getValue())>0)
            {
                t.setRight(delete(t.getRight(), x, display));
            }
            else if(x.compareTo(t.getValue())<0)
            {
                t.setLeft(delete(t.getLeft(), x, display));
            }
            else
            {
                return deleteNode(t, display);
            }
        }
        return t;
    }
}