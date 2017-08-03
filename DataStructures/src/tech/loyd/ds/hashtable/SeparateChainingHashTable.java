package tech.loyd.ds.hashtable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE = 101;

	private List<AnyType>[] theLists;
	private int currentSize;

	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++)
			theLists[i] = new LinkedList<>();
	}

	public void insert(AnyType x) {
		List<AnyType> whichList = theLists[myhash(x)];
		if (!whichList.contains(x)) {
			whichList.add(x);

			// Rehash; see Section 5.5
			if (++currentSize > theLists.length)
				rehash();
		}
	}

	public void remove(AnyType x) {
		List<AnyType> whichList = theLists[myhash(x)];
		if (whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}

	public boolean contains(AnyType x) {
		List<AnyType> whichList = theLists[myhash(x)];
		return whichList.contains(x);
	}

	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++)
			theLists[i].clear();
		currentSize = 0;
	}

	public static int hash(String key, int tableSize) {
		int hashVal = 0;

		for (int i = 0; i < key.length(); i++)
			hashVal = 37 * hashVal + key.charAt(i);

		hashVal %= tableSize;
		if (hashVal < 0)
			hashVal += tableSize;

		return hashVal;
	}

	private void rehash() {
		List<AnyType>[] oldLists = theLists;

		theLists = new List[nextPrime(2 * theLists.length)];
		for (int j = 0; j < theLists.length; j++)
			theLists[j] = new LinkedList<>();

		currentSize = 0;
		for (List<AnyType> list : oldLists)
			for (AnyType item : list)
				insert(item);
	}

	private int myhash(AnyType x) {
		int hashVal = x.hashCode();

		hashVal %= theLists.length;
		if (hashVal < 0)
			hashVal += theLists.length;

		return hashVal;
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	public static void main(String[] args) {
		SeparateChainingHashTable<Integer> H = new SeparateChainingHashTable<>();

		long startTime = System.currentTimeMillis();

		final int NUMS = 2000000;
		final int GAP = 37;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			H.insert(i);
		for (int i = 1; i < NUMS; i += 2)
			H.remove(i);

		for (int i = 2; i < NUMS; i += 2)
			if (!H.contains(i))
				System.out.println("Find fails " + i);

		for (int i = 1; i < NUMS; i += 2) {
			if (H.contains(i))
				System.out.println("OOPS!!! " + i);
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Elapsed time: " + (endTime - startTime));
	}

}
