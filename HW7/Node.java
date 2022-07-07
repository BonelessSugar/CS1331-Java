public class Node<T> {
	//<T> represents generic
	//generic type holding data stored in node
	private T data;
	//type Node with generic type attached
	//acts as "next" pointer, represent node that is next in LinkedList
	private Node<T> next;
	//constructor that takes in two arguments, data and next
	public Node(T data, Node<T> next) {
		//assigns instance variables accordingly
		this.data = data;
		this.next = next;
	}
	//constructor takes in one argument
	public Node(T data) {
		//assign next to null
		//constructor chaining
		this(data, null);
	}
	//getters and setters
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
}
