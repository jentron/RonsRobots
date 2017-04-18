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
		Vector3D testV = new Vector3D(1,0,1);
		double theAngle = testV.pitch();
		assert(Math.abs(theAngle - Math.PI/4) < 0.001);
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
		Vector3D testV2 = new Vector3D(1,0,0);
		Vector3D testV1 = new Vector3D(Math.sqrt(3)/2,0.5,0);
		double theDot = testV1.dot(testV2);
		assertTrue("Dot product is root 3 over 2",Math.abs(theDot - Math.sqrt(3)/2) < 0.001);
	}
	/**
	 * Test method for {@link utilities.Vector3D#dot()}.
	 */

	@Test
	public void testDotB() {
		Vector3D testV1 = new Vector3D(2,-3,7);
		Vector3D testV2 = new Vector3D(-4,2,-4);
		double theDot = testV1.dot(testV2);
		assertTrue("Dot product is -42",Math.abs(theDot + 42) < 0.001);
	}

	/**
	 * Test method for {@link utilities.Vector3D#cross(utilities.Vector3D)}.
	 */
	@Test
	public void testCrossZ() {
		Vector3D testV1 = new Vector3D(1,0,0);
		Vector3D testV2 = new Vector3D(0,1,0);
		Vector3D theCross = testV1.cross(testV2);
		assertTrue("Cross product is 0,0,1", 
				Math.abs(theCross.x ) < 0.001 &
				Math.abs(theCross.y ) < 0.001 &
				Math.abs(theCross.z -1 ) < 0.001
				);
	}

	/**
	 * Test method for {@link utilities.Vector3D#cross(utilities.Vector3D)}.
	 */
	@Test
	public void testCrossY() {
		Vector3D testV1 = new Vector3D(0,0,1);
		Vector3D testV2 = new Vector3D(1,0,0);
		Vector3D theCross = testV1.cross(testV2);
		assertTrue("Cross product is 0,1,0", 
				Math.abs(theCross.x ) < 0.001 &
				Math.abs(theCross.y -1) < 0.001 &
				Math.abs(theCross.z ) < 0.001
				);
	}

	/**
	 * Test method for {@link utilities.Vector3D#cross(utilities.Vector3D)}.
	 */
	@Test
	public void testCrossX() {
		Vector3D testV1 = new Vector3D(0,0,1);
		Vector3D testV2 = new Vector3D(0,1,0);
		Vector3D theCross = testV2.cross(testV1);
		assertTrue("Cross product is 1,0,0", 
				Math.abs(theCross.x -1) < 0.001 &
				Math.abs(theCross.y ) < 0.001 &
				Math.abs(theCross.z ) < 0.001
				);
	}


	/**
	 * Test method for {@link utilities.Vector3D#add(utilities.Vector3D)}.
	 */
	@Test
	public void testAdd() {
		Vector3D testV1 = new Vector3D(1,2,3);
		Vector3D testV2 = new Vector3D(4,5,6);
		testV1.add(testV2);
		assertTrue("Result is is 5,7,9", 
				Math.abs(testV1.x - 5.0 ) < 0.001 &
				Math.abs(testV1.y - 7.0 ) < 0.001 &
				Math.abs(testV1.z - 9.0 ) < 0.001
				);
	}

	/**
	 * Test method for {@link utilities.Vector3D#subtract(utilities.Vector3D)}.
	 */
	@Test
	public void testSubract() {
		Vector3D testV1 = new Vector3D(1,2,3);
		Vector3D testV2 = new Vector3D(6,5,4);
		testV1.subtract(testV2);
		assertTrue("Result is is -5, -3, -1", 
				Math.abs(testV1.x + 5.0 ) < 0.001 &
				Math.abs(testV1.y + 3.0 ) < 0.001 &
				Math.abs(testV1.z + 1.0 ) < 0.001
				);
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
