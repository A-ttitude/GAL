import junit.framework.TestCase;

public class TestUnitaire extends TestCase {

	public TestUnitaire(String name) {

		super(name);
	}

	public void testYinch() {

		Yinsh _yinsh = new Yinsh();

		assertTrue(_yinsh.current_color() == Yinsh.color.BLACK || _yinsh.current_color() == Yinsh.color.WHITE);
	}

	public void testPut_Ring1() {

		Yinsh _yinsh = new Yinsh();
		
		_yinsh.put_ring('A', 3, Yinsh.color.BLACK);
		
		assertTrue(_yinsh.isAnneau('A', 3));
	}

	public void testPut_Ring2() {

		Yinsh _yinsh = new Yinsh();
		
		assertTrue(_yinsh.getNbAnneau() == 0);
		
		_yinsh.put_ring('A', 3, Yinsh.color.BLACK);
		
		assertTrue(_yinsh.getNbAnneau() != 0);
	}

	public void testPut_Ring3() {

		Yinsh _yinsh = new Yinsh();
		
		try {
			
			_yinsh.put_ring('A', 1, Yinsh.color.WHITE);
			assertTrue(false);
		}
		catch(Exception e) {
			
			assertTrue(true);
		}
	}
}
