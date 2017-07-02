package tech.loyd.ds.list;

public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    
    private AnyType [ ] theItems;
    private int theSize;
	
	/**
     * 构造函数，构造一个空的ArrayList
     */
    public MyArrayList( )
    {
        doClear( );
    }
    
    /**
     * 返回集合中元素的个数
     * @return 返回集合中元素的个数
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * 若集合为空返回true，否则返回false
     * @return 若集合为空返回true，否则返回false
     */ 
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * 返回指定位置的元素
     * @param 元素的位置
     * @throws 若位置超出范围，抛出ArrayIndexOutOfBoundsException
     */
    public AnyType get( int idx )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        return theItems[ idx ];    
    }
        
    /**
     * 将新元素替换到指定位置上
     * @param 指定的位置
     * @param 新的元素
     * @return 旧的元素
     * @throws 若位置超出范围，抛出ArrayIndexOutOfBoundsException
     */
    public AnyType set( int idx, AnyType newVal )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        AnyType old = theItems[ idx ];    
        theItems[ idx ] = newVal;
        
        return old;    
    }

    /**
     * 重新定义容量
     * @param 新的容量大小
     */
    @SuppressWarnings("unchecked")
    public void ensureCapacity( int newCapacity )
    {
        if( newCapacity < theSize )
            return;

        AnyType [ ] old = theItems;
        theItems = (AnyType []) new Object[ newCapacity ];
        for( int i = 0; i < size( ); i++ )
            theItems[ i ] = old[ i ];
    }
    
    /**
     * 在集合的尾部添加元素
     * @param 想要添加的元素
     * @return true
     */
    public boolean add( AnyType x )
    {
    add( size( ), x );
        return true;            
    }
    
    /**
     * 在指定位置添加元素
     * @param 位置 
     * @param 元素
     */
    public void add( int idx, AnyType x )
    {
        if( theItems.length == size( ) )
            ensureCapacity( size( ) * 2 + 1 );

        for( int i = theSize; i > idx; i-- )
            theItems[ i ] = theItems[ i - 1 ];

        theItems[ idx ] = x;
        theSize++;  
    }
      
    /**
     * 移除指定位置的元素
     * @param 指定的位置
     * @return 返回被移除的元素
     * 
     */
    public AnyType remove( int idx )
    {
        AnyType removedItem = theItems[ idx ];
        
        for( int i = idx; i < size( ) - 1; i++ )
            theItems[ i ] = theItems[ i + 1 ];
        theSize--;    
        
        return removedItem;
    }
    
    /**
     * 清空集合中的所有元素
     */
    public void clear( )
    {
        doClear( );
    }
    
    /**
     * 清空集合中的所有元素
     */
    private void doClear( )
    {
        theSize = 0;
        ensureCapacity( DEFAULT_CAPACITY );
    }
    
    /**
     * 获得一个迭代器对象来遍历集合
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new ArrayListIterator( );
    }

    /**
     * 返回一个可以描述这个集合的字符串
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
     * 内部类ArrayListIterator，实现next,hasNext和remove方法
     */
    private class ArrayListIterator implements java.util.Iterator<AnyType>
    {
        private int current = 0;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current < size( );
        }
        
        
        public AnyType next( )
        {
            if( !hasNext( ) ) 
                throw new java.util.NoSuchElementException( ); 
                  
            okToRemove = true;    
            return theItems[ current++ ];
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyArrayList.this.remove( --current );
            okToRemove = false;
        }
    }
    

	
}
