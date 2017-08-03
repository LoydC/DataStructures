package tech.loyd.ds.tree;

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
			t.right = insert(x, t.right);
		else if (compareResult < 0)
			t.left = insert(x, t.left);
		return t;
	}

	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return t;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
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
			return findMax(t.right);
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
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	private int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	public static void main(String[] args) throws Exception {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		final int NUMS = 4000;
		final int GAP = 33;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
			t.insert(i);
		}

		//t.printTree();

		for (int i = 1; i < NUMS; i += 2)
			t.remove(i);
		
		t.printTree();
		
		if (NUMS < 40)
			t.printTree();
		
		if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
			System.out.println( "FindMin or FindMax error!"  + t.findMin( ) + " " + t.findMax( ));

		for (int i = 2; i < NUMS; i += 2)
			if (!t.contains(i))
				System.out.println("Find error1!");

		for (int i = 1; i < NUMS; i += 2) {
			if (t.contains(i))
				System.out.println("Find error2!");
		}
	}
}
