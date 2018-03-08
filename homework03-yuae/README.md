Homework CS 2610 - Midterm Prep

A few sample midterm questions. Please note this is only for aid in studying for your midterm. The actual midterm may look different than this, may have a different format and will possibly have different questions. Use at your own risk.

Keeping in mind that the midterm is not open book, open internet nor are phone a friends allowed, you may wish to employ those constraints when looking at these sample questions.

To get credit for this homework, answer question 1 (a, b and c) below and check your correct solution back into the repository before Oct 29.
Note the midterm is October 26th - just in case this conflicts with your typical studying schedule - the 1pt homework is due 29th.

1. Given the following code:

```java
public static int[] myFunction(int[] input){         
    int temp;
    for (int i = 1; i < input.length; i++) {
        int j = i;
        temp = input[j];
        while(j > 0 && temp < input[j-1]){
            input[j] = input[j-1];
            j--;
        }
        input[j] = temp;
    }
    return input;
}
```

1 a) What is the net result of calling this function?

 _an sorted array that from listed from minimun to maximun_______

1 b) What algorithm is this (name of algorithm)?

__insersion sorting______

1 c) Expressed in BigOh notation what is the growth rate of this function:

__O(n*n)______

2. Convert the following code from recursive to iterative:
```java
boolean binSearch(int[] arr, int val, int l, int r) {
        if (l > r || r < l)
            return false;
        int mid = (l + r) / 2;
        if (arr[mid] == val)
            return true;
        else if (arr[mid] < val) {
            return binSearch(arr,val, mid+1, r);
        }
        else {
            return binSearch(arr,val, l, mid-1);
        }
    }
```
boolean binSearchIterative(int[] arr, int val)
{
	int l = 0;
	int r = arr.length;
	int mid;
	while(l < r || r > l)
	{
		mid = (l+r)/2;
		if(arr[mid]==val)
		{
			return true;
		}
		else if(arr[mid]<value)
		{
			l = mid;
		}
		else
		{
			r = mid;
		}
	}
	return false;
}

3. In a singly linked list with the only reference into the list being a `Node head`, but with other class instance variables such as `int size` giving the number of elements in the list. Write the method `addFirst(E e)` to insert an element into the first position in the list. You may assume that the Node class has a next reference (with setters and getters) and an element that is taken in the constructor.

Heres the method header to get you started:
```java
public void addFirst(E e) {
	if(size<=0)
	{
		head = new Node(e,null);
	}
	else
	{
		Node newest = new Node(e,head.getNext());
		head.setNext(newest);
	}

}
```
4. Given the following code:

```java

interface MyPredicate {
        public boolean test(Integer i);
    }


static void printOnlySome(ArrayList<Integer> arr, MyPredicate pi ) {

    for(int val : arr) {
        if(pi.test(val))
            System.out.println(val);
    }
}

```

Call the given method printOnlySome in a way such that only numbers greater than 100 will be printed.
	printOnlySome(arrar, x->x>100);

5. What is ironic about the following method:

```java
void fastPrintEm(List<Integer> myList) {
    for(int i = 0; i < myList.size(); i++) {
        System.out.println(myList.get(i));
    }
}
```it's called fast print but not print fast

6. Write a method `countEm` to count all of the nodes in a Binary Tree. Design your own method header taking in whatever parameters you like. You can assume a TreeNode class exists with methods `E getElement()`, `TreeNode getLeft()` and `TreeNode getRight()` and that a `root()` method exists in your owning class that returns a TreeNode representing the root of the tree.
public int countEm(TreeNode root)
{
	int count;
	if(root == null)
	{
		return 0;
	}
	else
	{
		count = 1+countEm(root.getLeft())+countEm(root.getRight());
	}
	return count;
}