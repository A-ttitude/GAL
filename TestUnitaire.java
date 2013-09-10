import junit.framework.TestCase;

public class TestUnitaire extends TestCase {

	public TestUnitaire(String name) {

		super(name);
	}

	public void testYinch() {

		Yinsh _yinsh = new Yinsh();

		assertTrue(_yinsh.current_color() == Yinsh.color.BLACK || _yinsh.current_color() == Yinsh.color.WHITE);
	}

	public void testPut_Ring() {

		Yinsh _yinsh = new Yinsh();
	
		assertTrue(_yinsh.put_ring('A', 1, Yinsh.color.BLACK));
	}
}
