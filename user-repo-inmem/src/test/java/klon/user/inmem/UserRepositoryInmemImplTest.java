package klon.user.inmem;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

public class UserRepositoryInmemImplTest {

	
	@Test
	void hashMapTest() {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		
		String p1 = map.putIfAbsent("A", "B");
		String p2 = map.putIfAbsent("A", "B");
		String p3 = map.putIfAbsent("A", "C");
		String p4 = map.putIfAbsent("A", "D");
	}
	
}
