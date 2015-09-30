package datastructure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * this is a min heap, i.e, top element is the min element
 */
public class heap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		heap test = new heap();
		ArrayList<Integer> array =  new ArrayList<Integer>( Arrays.asList(3,2,6,4,1,5,8,7,0,8));
		test.heapfi(array);
		System.out.println(array);
		/*
		while(!array.isEmpty()){
			System.out.println(test.min(array));
			test.deleteMin(array);
		}*/
		
		int[] unsorted = {3,2,6,4,1,5,8,7,0,8};
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		for(int i=0;i<unsorted.length;i++){
			test.insert(array2, unsorted[i]);
		}
		System.out.println(array2);
		while(!array2.isEmpty()){
			System.out.println(test.min(array2));
			test.deleteMin(array2);
		}
		
		
	}
	
	/*
	 * bottom up method
	 * should take o(n) time complexity
	 */
	public void heapfi(ArrayList<Integer> array){
		int length = array.size();
		//walk backward from last internal node to root, when we visit an internal nodes, bubble it down as in removeMin
		for(int i=length-(length+1)/2-1;i>=0;i--){
			bubbleDown(array,i,length);
		}
	}
	
	private void bubbleDown(ArrayList<Integer> array, int i, int length){
		int left = (i+1)*2-1, right = (i+1)*2+1-1;
		if(right<length){
			int min = array.get(left)<array.get(right)?array.get(left):array.get(right);
			if(array.get(i)>min){
				int next = array.get(left)<array.get(right)?left:right;
				swap(array,i,next);
				bubbleDown(array,next,length);
			}
		}else if(left<length){
			//left child is leave node, no need to bubbleDown
			if(array.get(i)>array.get(left)){
				//swap
				swap(array,i,left);
			}
		}else{
			//this is leave node, not internal node
			return;
		}
	}
	
	private void swap(ArrayList<Integer> array, int x, int y){
		int tmp = array.get(x);
		array.set(x, array.get(y));
		array.set(y, tmp);
	}
	
	/*
	 * return top element, does not remove it
	 * log(1) time complexity
	 */
	public int min(ArrayList<Integer> array){
		return array.get(0);
	}
	
	/*
	 * insert one element to the heap
	 * log(n) time complexity
	 */
	public void insert(ArrayList<Integer> array, int ele){
		array.add(ele);
		bubbleUp(array,array.size()-1);
		//then need to bubble up this element
	}
	
	private void bubbleUp(ArrayList<Integer> array, int i){
		if(i>0){
			int parent = (i+1)/2-1;
			if(array.get(parent)>array.get(i)){
				swap(array,i,parent);
				bubbleUp(array,parent);
			}
		}
	}
	
	/*
	 * return top element, and remove it
	 * log(n) time complexity
	 * return the length of the heap, -1 means that try to delete empty heap
	 */
	public int deleteMin(ArrayList<Integer> array){
		int length = array.size();
		if(length!=0){
			int min = array.get(0);
			array.set(0, array.get(length-1));
			array.remove(length-1);
			//then try to bubble down
			bubbleDown(array,0,length-1);
			return length-1;
		}else
			return -1;
	}

}

