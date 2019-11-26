package array;
/**
 * 泛型数组：不能应用基本数据类型啊，要用基本类型的包装类
 * @author slf
 *
 * @param <T>
 */
public class GenericArray<T> {

	//数组参数 方便测试，改用public//
	private T[] data;	//数组类型、定义数据data保存数据
	private int size;	//数组实际个数
	//默认容量为10
	public GenericArray() {
		this(10);
		
	}
	//根据传入容量，构造Array
	public GenericArray(int capacity) {//容量capacity
		
		data = (T[]) new Object[capacity];//泛型--》用对象表示
		size = 0;//一开始一个数都没有存所以为0【满数组有8个元素，size=8=data.length】
	}	
	
	//获取数组容量
	public int getCapacity() {
		return data.length;
	}
	
	//获取当前元素个数
	public int getCount() {
		return size;
	}
	

	//扩充方法，时间复杂度 O(n),这里的修师符为private
	private void resize(int capacity) {
		T[] newData = (T[]) new Object[capacity];
		
		//将原数据放扩充数组中
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		//把扩充数组赋值给data
		data = newData;
	}
	
	//修改 index 位置的元素
	public void set(int index, T e) {
		checkIndex(index);
		data[index] = e;//噢噢，要先检测下标越界
	}
	
	//获取对应 index 位置的元素
	public T get(int index) {
		checkIndex(index);
		return data[index];
	}
	
	//查看数组是否包含元素e
	public boolean contains(T e) {
		//遍历数组
		int eCount = 0;
		for(int i = size-1; i >=0; --i) {
			if(data[i].equals(e)) {
				//还可以显示具体的下标位置
				System.out.println("具体在这个位置：i="+i);
				//要是有多个相同元素e呢？先不要这么快返回
				eCount++;
				//
				//return true;//把这个放到for循环外面
			}
		}
		if(eCount > 0) {
			System.out.println("有eCount个相同字符："+eCount);
			return true;
		}
		return false;
	}
	
	//查找元素e的下标,未找到就返回-1
	public int find(T e) {
		for(int i = 0; i < size; ++i) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	
	//在index 位置插入元素e,时间复杂度 O（m+n）
	public boolean insert(int index,T e) {
		checkIndex(index);
		
		//所以，这里要提前判断数组是否已满（满了的话就扩充），所以还是不用判断（）
				if(size == data.length) {
					resize(2*data.length);	//resize(2*size);
				}
		
		//将index（包括index）后面的元素往后移动一位
		for(int i= size-1; i >= index; --i) {//倒过来：int i= size-1; i >= index; --i===>data[i + 1] = data[i];
			//int i = index; i < size; ++i;这样好像不行诶，不知道，晚点再看
			data[i+1] = data[i];
		}
		data[index] = e;
		++size;
		return true;
	}
	
	//向数组头插入元素
	public void insertFirst(T e) {//这里就不写新的函数了，直接调用insert即可
		insert(0,e);
	}
	
	//向数组尾插入元素  ?有个问题？数据个数为8个(10个)，大小10，a[0]~a[9];,这里不用判断数组已满吗？
	public void insertLast(T e) {
		//所以，这里要提前判断数组是否已满（满了的话就扩充），所以还是不用判断（）
		if(size == data.length) {
			resize(2*size);
		}
			
		insert(size,e);//1 2 3  4 5{size=5,e=6}==>123456;
	}
	
	//删除index 位置的元素，并返回该下标的值
	public T remove(int index) {
		checkIndexForRemove(index);//检查index 不大于数组已经存储的值的大小（size）
		
		T ret = data[index];
		//将index前面的元素往前移动一位
		for(int i = size-1; i > index; --i) {
			data[i-1] = data[i];
		}
		--size;
		data[size]=null;
		
		 // 缩容		这里有点不懂？【容量是size的4倍，容量/2 不等于0】【避免size=0,容量也是0】
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
		
		return ret;
	}
	
	//删除第一个元素
	public T removeFirst() {
		return remove(0);
	}
	//删除末尾元素
	public T removeLast() {
		return remove(size-1);
	}
	
	//删除指定元素
	public void removeElement(T e) {
		int index = find(e);
		if(index != -1) {
			remove(index);
		}
	}
	
	
	 	@Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
	        builder.append('[');
	        for (int i = 0; i < size; i++) {
	            builder.append(data[i]);
	            if (i != size - 1) {
	                builder.append(", ");
	            }
	        }
	        builder.append(']');
	        return builder.toString();
	    }
	 
	 public void printAll() {
	        for (int i = 0; i < size; ++i) {
	            System.out.print(data[i] + " ");
	        }
	        System.out.println();
	    }
	 
	 
	
		//判断数组是否为空
		public boolean isEmpty() {
			return size == 0;//这是个判断：size == 0；是就true，否就是false
		}
		
		//判断数组下标是否越界；
		private void checkIndex(int index) {
			if(index < 0 || index > size) {
				//index >= size为什么这里是大于；【可能是数组扩充时index可以等于size吧】
				//12345，下标是01234，size是5，index=5时，不报错
				throw new IllegalAccessError("Add failed! Require index >=0 and index <= size");
			}		
		}
		 
		//
		private void checkIndexForRemove(int index) {
			if(index < 0 || index >= size) {
	            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
	        }
		}
		
	
		public static <Sting> void main(String[] args) {
			GenericArray<String> array = new GenericArray<>(8);
			//array= {"a","b"};数组常量只能在初始化程序中使用
			int count = array.getCount();
			array.insert(0, "ab");
			array.insert(0, "abc");
			array.insert(2, "size");
			//扩充
			array.insert(3, "add");
			
			//String remove = array.remove(2);
			//System.out.println("移除的数值："+remove);
			
			array.removeElement("ab");
			array.removeElement("www");
			array.remove(2);
			
			System.out.println(array.toString());
			
			
			System.out.println("数组的长度="+array.getCapacity());
			System.out.println("数组的实际大小="+array.getCount());
		}
	
}
