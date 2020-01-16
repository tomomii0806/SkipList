import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		SkipList<Integer> sl = new SkipList<>((Integer o1, Integer o2) -> o1 - o2);
		assertTrue(sl.isEmpty());
	}

	@org.junit.jupiter.api.Test
	void testInsert() {
		SkipList<Integer> sl = new SkipList<>((Integer o1, Integer o2) -> o1 - o2);
		
		sl.insert(5);
		sl.insert(3);
		sl.insert(7);
		sl.insert(35);
		sl.insert(1);
		sl.insert(-1);
		sl.printList();
	}
	
	@org.junit.jupiter.api.Test
	void testSize() {
		SkipList<Integer> sl = new SkipList<>((Integer o1, Integer o2) -> o1 - o2);
		
		sl.insert(5);
		sl.insert(3);
		sl.insert(7);
		sl.insert(35);
		sl.insert(3);
		sl.insert(1);
		sl.insert(-1);
		
		assertEquals(6, sl.getSize());
	}
	
	@org.junit.jupiter.api.Test
	void testFind() {
		SkipList<Integer> sl = new SkipList<>((Integer o1, Integer o2) -> o1 - o2);
		
		sl.insert(8);
		sl.insert(9);
		sl.insert(80);
		sl.insert(35);
		sl.insert(82);
		sl.insert(9);
		sl.insert(-10);
		
		sl.printList();
		assertTrue(sl.find(8));
		assertTrue(sl.find(80));
		assertFalse(sl.find(60));
	}
	
	@org.junit.jupiter.api.Test
	void testDelete() {
		SkipList<Integer> sl = new SkipList<>((Integer o1, Integer o2) -> o1 - o2);
		
		sl.insert(1);
		sl.insert(6);
		sl.insert(66);
		sl.insert(23);
		sl.insert(15);
		sl.insert(7);
		sl.insert(-7);
		
		sl.printList();
		sl.delete(6);
		sl.printList();
	}
}
