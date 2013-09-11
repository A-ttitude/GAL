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
		
		try { _yinsh.put_ring('A', 3, Yinsh.color.BLACK); }
		catch(Exception e) {}

		assertTrue(_yinsh.isAnneau('A', 3));
	}

	public void testPut_Ring2() {

		Yinsh _yinsh = new Yinsh();
		
		assertTrue(_yinsh.getNbAnneau() == 0);
		
		try { _yinsh.put_ring('A', 3, Yinsh.color.BLACK); }
		catch(Exception e) {}
		
		assertTrue(_yinsh.getNbAnneau() != 0);
	}

	public void testPut_Ring3() {
		
		// A > 1, 6, 7, 8, 9, 10, 11
		// B > 8, 9, 10, 11
		// C > 9, 10, 11
		// D > 10, 11
		// E > 11
		// F > 1, 11
		// G > 1
		// H > 1, 2
		// I > 1, 2, 3
		// J > 1, 2, 3, 4
		// K > 1, 2, 3, 4, 5, 6, 11

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
