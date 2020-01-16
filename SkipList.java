
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<Item extends Comparable<Item>> implements SList<Item> {

	
	private Node<Item> head;	//The start of the skip list. This locates the top-leftmost of the list
	private Node<Item> tail;	//The end of the skip list. This locates the right-most of the skip list.
	private int size;
	private int highestLevel; 			//Keep track of the highest height in the skip list
	
	
	private class Node<Item extends Comparable<Item>>{	//Private node class. Each item inthe skip list are stored as a node
		
		private Item item;
		private Node<Item> right;
		private Node<Item> left;
		private Node<Item> up;
		private Node<Item> down;

		
		
		public Node(Item item, Node<Item> right, Node<Item> left, Node<Item> up, Node<Item> down){
			
			this.item = item;
			this.right = right;
			this.left = left;
			this.up = up;
			this.down = down;
		}
		
		public Node(Item item) {
			this.item = item;
		}
		
	}

	
	
	public SkipList(Comparator<Item> c) {
		
		Node p1, p2;
		
		p1 = new Node("-infinity");
		p2 = new Node("infinity");
		
		p1.right = p2;
		p2.left = p1;
		
		this.head = p1;
		this.tail = p2;
		//this.end = p2;
		this.size = 0;
		this.highestLevel = 1;
	}

	
	
	public Node findEntryNode(Item item) {
		
		Node curr = head;
		
		boolean endIsReached = false;
		
		while (!endIsReached)
		{
			while(curr.right.item != "infinity" && item.compareTo((Item) curr.right.item) > 0)
			{
				curr = curr.right;
				
			}
			
			if (curr.down != null)
			{
				curr = curr.down;			
			}
			else
			{
				endIsReached = true;
			}
		}

		return curr;
	}
	
	private void buildAnotherLevel() {
		
		Node p1, p2;
		
		p1 = new Node("-infinity");
		p2 = new Node("infinity");
		
		p1.right = p2;
		p2.left = p1;
		
		p1.down = head;
		head.up = p1;
		p2.down = tail;
		tail.up = p2;
		
		this.head = p1;
		this.tail = p2;
	}
	
	@Override
	public void insert(Item item) {
		
		Node curr = findEntryNode(item);
		
		//If the item is already in the skip list, don't insert the item
		if (curr.right.item.equals(item)) {
			return;
		}
		
		Node newNode = new Node(item, null, null, null, null);
		
		newNode.right = curr.right;
		newNode.left = curr;
		curr.right.left = newNode;
		curr.right = newNode;
		
		int level = getLevel();
		System.out.println(level);
		
		
		int currLevel = 1;
		
		while (level != 0)
		{
			if (currLevel >= highestLevel)
			{
				buildAnotherLevel();
				highestLevel++;
			}
			
			while (curr.up == null)
			{
				curr = curr.left;
			}
			curr = curr.up;
			
			Node node = new Node(item, null, null, null, null);
			node.right = curr.right;
			node.left = curr;
			curr.right.left = node;
			curr.right = node;
			newNode.up = node;
			node.down = newNode;
			
			
			newNode = newNode.up;
			currLevel++;
			
			level--;
		}
		
		size++;
	}
	
	
	

	private int getLevel() {
		Random rand = new Random();
		int i = rand.nextInt(2);	//Since the level has to be at least 1
		int level = 0;
		
		while (i == 1)
		{			
			level++;
			i = rand.nextInt(2);
		}
		
		return level;
	}
	
	@Override
	public Item delete(Item item) {
		
		Node curr = findEntryNode(item);	
		curr = curr.right;
		
		if (!curr.item.equals(item))
		{
			throw new NoSuchElementException();
		}
		else 
		{
			while (curr != null)
			{
				curr.left.right = curr.right;
				curr.right.left = curr.left;
				curr = curr.up;
			}
			return item;
		}
		
	}
	
	
	
	@Override
	public boolean find(Item item) {
		
		if (findEntryNode(item).right.item == item)
		{
			return true;
		}
		return false;
	}
	
	
	

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (size == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		int currentLevel = highestLevel;
		while (currentLevel > 1)
		{
			head.right = tail;
			tail.left = head;
			head = head.down;
			tail = tail.down;
			head.up = null;
			tail.up = null;
			currentLevel--;
		}
	}
	
	@Override
	public void printList() {
		
		Node curr;
		int level = highestLevel;
		int i = 0;
		
		while (level > 0)
		{
			curr = head;
			int j = i;
			while (j > 0)
			{
				curr = curr.down;
				j--;
			}
			System.out.print("Level "+level+" : ");
			while (curr.right.item != "infinity")
			{
				curr = curr.right;
				System.out.print(curr.item+", ");
			}
			System.out.println();
			level--;
			i++;
		}
	
		
	}
}
