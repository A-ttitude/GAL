import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {

		TestSuite _suite = new TestSuite("Tests Unitaires");
		_suite.addTest(new TestSuite(TestUnitaire.class));
		
		return _suite;
	}

	public static void main(String args[]) {

		System.out.println("Test Unitaires");

		junit.textui.TestRunner.run(AllTests.suite());
	}
}
