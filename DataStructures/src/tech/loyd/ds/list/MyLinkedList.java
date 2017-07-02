package tech.loyd.ds.list;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
	
    /**
     * 双链表的节点
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    /**
     * 构造一个空的LinkedList
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    /**
     * 清空集合
     */
    public void clear( )
    {
        doClear( );
    }
    
    /**
     * 改变集合的size为0
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * 返回集合中元素个数
     * @return 返回集合中元素个数
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * 判断集合是否为空
     * @return 为空返回true，否则返回false
     */
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * 在集合的末尾加入元素
     * @param 元素
     * @return true
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * 在给定的位置添加元素
     * @param 位置
     * @param 元素
     * @throws 位置超出范围抛出IndexOutOfBoundsException
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * 在指定位置之前插入元素
     * @param 在其前面插入的节点
     * @param 元素x
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * 返回该位置的元素
     * @param 位置
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * 改变对应位置的元素
     * @param 位置
     * @param 新元素
     * @return 旧元素
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * 获取该位置的节点
     * @param 位置
     * @return 返回节点
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * 获取指定位置的节点
     * @param 指定的位置
     * @param 最小的位置
     * @param 最大的位置
     * @return 节点
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * 删除指定位置的元素
     * @param 指定位置
     * @return 元素
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * 删除指定节点
     * @param 节点
     * @return 删除的元素
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * 用字符串表示集合
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * 返回一个迭代器，初始位置为第一个位置
     * @return 返回一个迭代器，初始位置为第一个位置
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * 迭代器内部类
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }

}

