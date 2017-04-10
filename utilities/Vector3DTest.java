/**
 * 
 */
package utilities;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rjensen
 *
 */
public class Vector3DTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}


	/**
	 * Test method for {@link utilities.Vector3D#angle()}.
	 */
	@Test
	public void testAnglePIover4() {
		Vector3D testV = new Vector3D(1,1,0);
		double theAngle = testV.angle();
		assert(Math.abs(theAngle - Math.PI/4) < 0.001);
	}
	/**
	 * Test method for {@link utilities.Vector3D#angle()}.
	 */
	@Test
	public void testAngleNegative3PIover4() {
		Vector3D testV = new Vector3D(-1,-1,0);
		double theAngle = testV.angle();
		assert(Math.abs(theAngle + 3*Math.PI/4) < 0.001);
	}

	/**
	 * Test method for {@link utilities.Vector3D#pitch()}.
	 */
	@Test
	public void testPitch() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link utilities.Vector3D#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector3D testV = new Vector3D(1,2,3);
		testV.normalize();
		double theMagniture = testV.magnitude();
		assert(Math.abs(theMagniture - 1.0) < 0.001);
	}

	/**
	 * Test method for {@link utilities.Vector3D#magnitude()}.
	 */
	@Test
	public void testMagnitude() {
		Vector3D testV = new Vector3D(1,2,3);
		double theMagnitude = testV.magnitude();
		assert(Math.abs(theMagnitude - Math.sqrt(1+4+9)) < 0.001);
	}

	/**
	 * Test method for {@link utilities.Vector3D#dot()}.
	 */
	@Test
	public void testDot() {
		Vector3D testV1 = new Vector3D(1,0,0);
		Vector3D testV2 = new Vector3D(Math.sqrt(3)/2,0.5,0);
		double theDot = testV1.dot(testV2);
		assertTrue("Dot product is pi/3",Math.abs(theDot - Math.PI/3) < 0.001);
	}

	/**
	 * Test method for {@link utilities.Vector3D#cross(utilities.Vector3D)}.
	 */
	@Test
	public void testCross() {
		Vector3D testV1 = new Vector3D(1,0,0);
		Vector3D testV2 = new Vector3D(0,1,0);
		Vector3D theCross = testV1.cross(testV2);
		assertTrue("Cross product is 0,0,1", 
				Math.abs(theCross.x ) < 0.001 &
				Math.abs(theCross.y ) < 0.001 &
				Math.abs(theCross.x -1 ) < 0.001
				);
	}

	/**
	 * Test method for {@link utilities.Vector3D#add(utilities.Vector3D)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link utilities.Vector3D#subtract(utilities.Vector3D)}.
	 */
	@Test
	public void testSubract() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link utilities.Vector3D#multi(double)}.
	 */
	@Test
	public void testMulti() {
		Vector3D testV = new Vector3D(1,2,3);
		testV.multi(10);
		double theMagnitude = testV.magnitude();
		assert(Math.abs(theMagnitude - Math.sqrt(100+400+900)) < 0.001);	}

}
