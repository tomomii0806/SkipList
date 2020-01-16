public interface SList<Item> {

	
	//Inserting a new item to the skip list
	//val - a new Item to insert
	public void insert(Item item);
	
	
	//Deleting the item from the skip list
	//val - the item to delete
	public Item delete(Item item);
	
	//Finding an item in the skip list 
	//Return true if val is in the list
	//Return false if val is not in the list
	public boolean find(Item item);
	
	//Geting the size of the skip list
	//Return the size of the skip list
	public int getSize();
	
	//Determining whether the skip list is empty
	//Return true if the list is empty
	//Return false if the list is not empty
	public boolean isEmpty();
	
	
	//Emptying the skip list
	public void clear();
	
	
	public void printList();
}
