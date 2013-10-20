import junit.framework.TestCase;

public class TestUnitaire extends TestCase {

	public TestUnitaire(String name) { super(name); }

	public static void testYinch() {

		Yinsh _yinsh = new Yinsh();
		Yinsh.color c = _yinsh.current_color();
		
		assertTrue(c == Yinsh.color.BLACK || c == Yinsh.color.WHITE);
		System.out.println("testYinch() : " + (c == Yinsh.color.BLACK || c == Yinsh.color.WHITE));
	}

	public static void testPut_Ring1() {

		Yinsh _yinsh = new Yinsh();

		try {
			
			_yinsh.put_ring('A', 3, Yinsh.color.BLACK);
		}
		catch(Exception e) {}

		assertTrue(_yinsh.isAnneau('A', 3));
		System.out.println("testPut_Ring1() : " + _yinsh.isAnneau('A', 3));
	}

	public static void testPut_Ring2() {

		Yinsh _yinsh = new Yinsh();

		assertTrue(_yinsh.getNbAnneau() == 0);

		try {
			
			_yinsh.put_ring('A', 3, Yinsh.color.BLACK);
		}
		catch(Exception e) {}

		assertTrue(_yinsh.getNbAnneau() != 0);
		System.out.println("testPut_Ring2() : " + (_yinsh.getNbAnneau() != 0));
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
			
			System.out.println("testPut_Ring3() : false");
			assertTrue(false);
		}
		catch(Exception e) {

			System.out.println("testPut_Ring3() : true");
			assertTrue(true);
		}
	}

	public static void testPut_Ring4() {

		Yinsh _yinsh = new Yinsh();

		try {

			_yinsh.put_ring('A', 2, Yinsh.color.WHITE);
			_yinsh.put_ring('A', 3, Yinsh.color.WHITE);

			System.out.println("testPut_Ring4() : false");
			assertTrue(false);
		}
		catch(Exception e) {

			System.out.println("testPut_Ring4() : true");
			assertTrue(true);
		}
	}

	public static void testIntersectionAnneau() {

		Yinsh _yinsh = new Yinsh();

		try {

			_yinsh.put_ring('A', 2, Yinsh.color.WHITE);
			_yinsh.put_ring('A', 2, Yinsh.color.WHITE);

			System.out.println("testIntersectionAnneau() : false");
			assertTrue(false);
		}
		catch(Exception e) {

			System.out.println("testIntersectionAnneau() : true");
			assertTrue(true);
		}
	}

	public static void testIs_Initialized() {

		Yinsh _yinsh = new Yinsh();

		try {
			
			initPlateau(_yinsh);
			
			assertTrue(_yinsh.getNbAnneau() == 10);
			assertTrue(_yinsh.getNbAnneauBlanc() == 5);
			assertTrue(_yinsh.getNbAnneauNoir() == 5);
			System.out.println("testIs_Initialized() : true");
		}
		catch(Exception e) { System.out.println("testIs_Initialized() : false"); }
	}
	
	public static void testPut_Marker1() {
		
		Yinsh _yinsh = new Yinsh();
		
		try {
			
			initPlateau(_yinsh);
			
			_yinsh.put_marker('D', 2, Yinsh.color.BLACK);
			
			assertTrue(true);
			System.out.println("testPut_Marker1() : true");
		}
		catch(Exception e) {
			
			System.out.println("testPut_Marker1() : false");
			assertTrue(false);
		}
	}

	public static void testMove_Ring1() {
		
		Yinsh _yinsh = new Yinsh();
		
		try {
			
			initPlateau(_yinsh);
			
			_yinsh.put_marker('D', 2, Yinsh.color.BLACK);
			_yinsh.move_ring('D', 2, 'D', 5);

			assertTrue(true);
			System.out.println("testMove_Ring1() : true");
		}
		catch(Exception e) {

			System.out.println("testMove_Ring1() : false");
			assertTrue(false);
		}
	}

	public static void testPut_Marker2() {
		
		Yinsh _yinsh = new Yinsh();
		
		try {
			
			initPlateau(_yinsh);
			
			_yinsh.put_marker('D', 2, Yinsh.color.WHITE);
			
			assertTrue(false);
			System.out.println("testPut_Marker2() : false");
		}
		catch(Exception e) {

			System.out.println("testPut_Marker2() : true");
			assertTrue(true);
		}
	}

    public static void testMove_Ring2() {

        Yinsh _yinsh = new Yinsh();

        try {

            initPlateau(_yinsh);

            _yinsh.put_marker('D', 2, Yinsh.color.BLACK);
            _yinsh.move_ring('D', 2, 'D', 6);

            assertTrue(false);
            System.out.println("testMove_Ring2() : false");
        }
        catch(Exception e) {

            System.out.println("testMove_Ring2() : true");
            assertTrue(true);
        }
    }

    public static void testMove_Ring3() {

        Yinsh _yinsh = new Yinsh();

        try {

            initPlateau(_yinsh);

            _yinsh.put_marker('D', 2, Yinsh.color.BLACK);
            _yinsh.move_ring('D', 2, 'I', 7);

            assertTrue(false);
            System.out.println("testMove_Ring3() : false");
        }
        catch(Exception e) {

            System.out.println("testMove_Ring3() : true");
            assertTrue(true);
        }
    }

	public static void testMove_Ring4() {

		Yinsh _yinsh = new Yinsh();

		try {

			// Placement des anneaux

			_yinsh.put_ring('A', 5, Yinsh.color.BLACK);
			_yinsh.put_ring('B', 4, Yinsh.color.WHITE);

			_yinsh.put_ring('D', 3, Yinsh.color.BLACK);
			_yinsh.put_ring('B', 6, Yinsh.color.WHITE);

			_yinsh.put_ring('E', 4, Yinsh.color.BLACK);
			_yinsh.put_ring('D', 2, Yinsh.color.WHITE);

			_yinsh.put_ring('G', 8, Yinsh.color.BLACK);
			_yinsh.put_ring('F', 8, Yinsh.color.WHITE);

			_yinsh.put_ring('H', 8, Yinsh.color.BLACK);
			_yinsh.put_ring('G', 7, Yinsh.color.WHITE);

			// Placement des marqueurs noirs

			_yinsh._plateau[1][4] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[2][3] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[3][6] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[4][4] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[4][7] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[8][7] = Yinsh.color.BLACK_MARKER;

			// Placement des marqueurs blancs

			_yinsh._plateau[2][4] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[2][5] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[3][5] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][2] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][5] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][6] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][8] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[6][8] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[7][6] = Yinsh.color.WHITE_MARKER;

			// Déplacements anneaux (E, 4) en (E, 10)

			_yinsh.put_marker('E', 4, Yinsh.color.BLACK);
			_yinsh.move_ring('E', 4, 'E', 10);

			assertTrue(_yinsh.get('E', 4) == Yinsh.color.BLACK_MARKER);
			assertTrue(_yinsh.get('E', 6) == Yinsh.color.BLACK_MARKER);
			assertTrue(_yinsh.get('E', 7) == Yinsh.color.BLACK_MARKER);
			assertTrue(_yinsh.get('E', 9) == Yinsh.color.BLACK_MARKER);

			assertTrue(_yinsh.get('E', 5) == Yinsh.color.WHITE_MARKER);
			assertTrue(_yinsh.get('E', 8) == Yinsh.color.WHITE_MARKER);

			System.out.println("testMove_Ring4() : true");
		}
		catch(Exception e) {

			System.out.println("testMove_Ring4() : false");
			assertTrue(false);
		}
	}

	public static void testRemove_Row() {

		Yinsh _yinsh = new Yinsh();

		try {

			// Placement des anneaux

			_yinsh.put_ring('D', 6, Yinsh.color.BLACK);
			_yinsh.put_ring('C', 4, Yinsh.color.WHITE);

			_yinsh.put_ring('E', 3, Yinsh.color.BLACK);
			_yinsh.put_ring('D', 4, Yinsh.color.WHITE);

			_yinsh.put_ring('E', 4, Yinsh.color.BLACK);
			_yinsh.put_ring('F', 3, Yinsh.color.WHITE);

			_yinsh.put_ring('G', 6, Yinsh.color.BLACK);
			_yinsh.put_ring('F', 6, Yinsh.color.WHITE);

			_yinsh.put_ring('H', 10, Yinsh.color.BLACK);
			_yinsh.put_ring('I', 8, Yinsh.color.WHITE);

			// Placement des marqueurs noirs

			_yinsh._plateau[3][1] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[4][5] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[5][3] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[5][6] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[6][7] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[7][7] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[7][8] = Yinsh.color.BLACK_MARKER;
			_yinsh._plateau[8][9] = Yinsh.color.BLACK_MARKER;

			// Placement des marqueurs blancs

			_yinsh._plateau[3][4] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][4] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][6] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[4][7] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[6][3] = Yinsh.color.WHITE_MARKER;
			_yinsh._plateau[6][6] = Yinsh.color.WHITE_MARKER;

			// Le joueur "noir" retire les marqueurs de l'alignement

			_yinsh.remove_row('E', 6, 'I', 10);
			assertTrue(_yinsh.get('E', 6) == Yinsh.color.EMPTY);
			assertTrue(!_yinsh.isAnneau('F', 7));
			assertTrue(!_yinsh.isAnneau('G', 8));
			assertTrue(!_yinsh.isAnneau('H', 9));
			assertTrue(!_yinsh.isAnneau('I', 10));

			_yinsh.remove_ring('H', 10);
			assertTrue(!_yinsh.isAnneau('H', 10));

			int score = _yinsh.getScore(Yinsh.color.BLACK);
			assertTrue(score == 1);

			System.out.println("testRemove_Row() : true");
		}
		catch(Exception e) {

			System.out.println("testRemove_Row() : false");
			assertTrue(false);
		}
	}

	public static Yinsh initPlateau(Yinsh y) throws Exception {
		
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
