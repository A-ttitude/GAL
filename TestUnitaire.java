import junit.framework.TestCase;

public class TestUnitaire extends TestCase {

	public TestUnitaire(String name) { super(name); }

	public static void testYinch() {

		Yinsh _yinsh = new Yinsh();
		
		assertTrue(_yinsh.current_color() == Yinsh.color.BLACK || _yinsh.current_color() == Yinsh.color.WHITE);
	}

	public static void testPut_Ring1() {

		Yinsh _yinsh = new Yinsh();

		try {
			
			_yinsh.put_ring('A', 3, Yinsh.color.BLACK);
		}
		catch(Exception e) {}

		assertTrue(_yinsh.isAnneau('A', 3));
	}

	public static void testPut_Ring2() {

		Yinsh _yinsh = new Yinsh();

		assertTrue(_yinsh.getNbAnneau() == 0);

		try {
			
			_yinsh.put_ring('A', 3, Yinsh.color.BLACK);
		}
		catch(Exception e) {}

		assertTrue(_yinsh.getNbAnneau() != 0);
	}

	public static void testPut_Ring3() {

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

			System.out.println(e.getMessage() + "\n");
			assertTrue(true);
		}
	}

	public static void testPut_Ring4() {

		Yinsh _yinsh = new Yinsh();

		try {

			_yinsh.put_ring('A', 2, Yinsh.color.WHITE);
			_yinsh.put_ring('A', 3, Yinsh.color.WHITE);

			assertTrue(false);
		}
		catch(Exception e) {

			System.out.println(e.getMessage() + "\n");
			assertTrue(true);
		}
	}

	public static void testIntersectionAnneau() {

		Yinsh _yinsh = new Yinsh();

		try {

			_yinsh.put_ring('A', 2, Yinsh.color.WHITE);
			_yinsh.put_ring('A', 2, Yinsh.color.WHITE);

			assertTrue(false);
		}
		catch(Exception e) {

			System.out.println(e.getMessage() + "\n");
			assertTrue(true);
		}
	}

	public static void testIs_Initialized() {

		Yinsh _yinsh = new Yinsh();

		try {
			
			initYinsh(_yinsh);
			
			assertTrue(_yinsh.getNbAnneau() == 10);
			assertTrue(_yinsh.getNbAnneauBlanc() == 5);
			assertTrue(_yinsh.getNbAnneauNoir() == 5);
		}
		catch(Exception e) { System.out.println(e.getMessage() + "\n"); }
	}
	
	public static void testPut_Marker() {
		
		Yinsh _yinsh = new Yinsh();
		
		try {
			
			initYinsh(_yinsh);
			
			_yinsh.put_marker('D', 2, Yinsh.color.BLACK);
			
			assertTrue(true);
		}
		catch(Exception e) {
			
			System.out.println(e.getMessage() + "\n");
			assertTrue(false);
		}
	}
	
	public static void testMove_Ring() {
		
		Yinsh _yinsh = new Yinsh();
		
		try {
			
			initYinsh(_yinsh);
			
			_yinsh.put_marker('D', 2, Yinsh.color.BLACK);
			_yinsh.move_ring('D', 2, 'D', 5);
			
			assertTrue(true);
		}
		catch(Exception e) {
			
			System.out.println(e.getMessage() + "\n");
			assertTrue(false);
		}
	}
	
	public static Yinsh initYinsh(Yinsh y) throws Exception {
		
		y.put_ring('B', 1, Yinsh.color.BLACK);
		y.put_ring('B', 2, Yinsh.color.WHITE);
		
		y.put_ring('D', 2, Yinsh.color.BLACK);
		y.put_ring('C', 2, Yinsh.color.WHITE);

		y.put_ring('F', 7, Yinsh.color.BLACK);
		y.put_ring('D', 6, Yinsh.color.WHITE);
		
		y.put_ring('G', 9, Yinsh.color.BLACK);
		y.put_ring('G', 8, Yinsh.color.WHITE);
		
		y.put_ring('J', 8, Yinsh.color.BLACK);
		y.put_ring('H', 6, Yinsh.color.WHITE);
		
		return y;
	}
}
