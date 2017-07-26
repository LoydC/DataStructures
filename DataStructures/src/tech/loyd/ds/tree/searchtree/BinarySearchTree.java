package tech.loyd.ds.tree.searchtree;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private BinaryNode<AnyType> root;

	private static class BinaryNode<AnyType> {
		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;

		BinaryNode(AnyType element) {
			this(element, null, null);
		}

		BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}

	public BinarySearchTree() {
		root = null;
	}

	public void insert(AnyType x) {
		root = insert(x, root);
	}

	public void remove(AnyType x) {
		root = remove(x, root);
	}

	public AnyType findMin() throws Exception {
		if (isEmpty())
			throw new Exception("Empty tree");
		return findMin(root).element;
	}

	public AnyType findMax() throws Exception {
		if (isEmpty())
			throw new Exception("Empty tree");
		return findMax(root).element;
	}

	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
	if (t == null)
		return new BinaryNode<>(x);

	int compareResult = x.compareTo(t.element);

	if (compareResult > 0)
		return insert(x,t.right);
	else if (compareResult < 0)
		return insert(x, t.left);
	else 
		return t;
}

	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if(t==null)
			return t;
		
		int compareResult = x.compareTo(t.element);
	
		if (compareResult > 0)
			t.left = remove(x,t.left);
		else if (compareResult < 0)
			t.right = remove(x,t.right);
		else if(t.left != null && t.right != null){
			t.element = findMin(t.right).element;
			t.right = remove(t.element,t.right);
		}else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		else
			return findMin(t.left);
	}

	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		else if (t.right == null)
			return t;
		else
			return findMin(t.right);
	}

	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult > 0)
			return contains(x, t.right);
		else if (compareResult < 0)
			return contains(x, t.left);
		else
			return true;
	}

	private void printTree(BinaryNode<AnyType> t) {
		if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
	}

	private int height(BinaryNode<AnyType> t) {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
	}
}
