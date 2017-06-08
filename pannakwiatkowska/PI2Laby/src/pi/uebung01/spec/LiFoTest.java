 package pi.uebung01.spec;
  
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse testet die Klasse LiFo
 * 
 * @author Mahmoud
 *
 */
public class LiFoTest<E> {

	private Object a;

	/**
	 * erzeugt eine neue Sammlung vom Typ LiFo
	 */
	@SuppressWarnings("rawtypes")
	@Before
	public void createSammlung() {

		a = new LiFo(3);

	}

	/**
	 * testet die Methode push Es wird Elemente hinzugefügt mehr als die Große
	 * der Sammlung, und wird dementsprechend eine PushPopyException entworfen
	 */
	@Test(expected = PushPopyException.class)
	public void pushSammlungIsFullTest() {
		((LiFo) a).push(a);
		((LiFo) a).push(a);
		((LiFo) a).push(a);
		((LiFo) a).push(a);

	}

	/**
	 * testet die Methode push Es wird null hintzugefügt, und wird
	 * dementsprechend eine illegalArgumentException entworfen
	 */
	@Test(expected = IllegalArgumentException.class)
	public void pushNullEingeben() {
		((LiFo) a).push(null);
	}

	/**
	 * testet die Methode isEmpty In diesem Fall ist die Sammlung leer. Es wird
	 * true zurückgegeben
	 */
	@Test
	public void testIsEmptyTrue() {
		assertTrue(((LiFo) a).isEmpty());
	}

	/**
	 * testet die Methode isEmpty In diesem Fall ist die Sammmlung nicht leer.
	 * Es wird false zurückgegeben
	 */
	@Test
	public void testIsEmptyFalse() {
		((LiFo) a).push(a);
		assertFalse(((LiFo) a).isEmpty());
	}

	/**
	 * testet die Methode isFull die Sammlung ist in diesem Fall leer Es wird
	 * false zurückgegeben
	 */
	@Test
	public void testIsFullLeerFalse() {
		assertFalse(((LiFo) a).isFull());
	}

	/**
	 * tested die Methode isFull die Sammlung enthält in diesem Fall nur ein
	 * Element und hat noch freie Plaetze Es wird false zurückgegeben
	 */
	@Test // problem
	public void testIsFullFalse() {
		((LiFo) a).push(a);
		assertFalse(((LiFo) a).isFull());
	}

	/**
	 * tested die Methode isFull die Sammlung ist in diesem Fall full Es wird
	 * true zurückgegeben
	 */
	@SuppressWarnings("unchecked")
	@Test // Problem
	public void testIsFullTrue() {
		((LiFo) a).push(1);
		((LiFo) a).push(1);
		((LiFo) a).push(2);
		assertTrue(((LiFo) a).isFull());
	}

	/**
	 * testet die Methode pop die Sammlung ist in diesem Fall leer Es wird eine
	 * PushPopyException entworfen
	 */
	@Test(expected = PushPopyException.class)
	public void testPopEmpty() {
		((LiFo) a).pop();
	}

	/**
	 * testet die Methode pop die Sammlung ist in diesem Fall full das jungste
	 * Element wird entfernt und zurückgegeben
	 */
	@Test // problem
	public void testPopFull() {
		((LiFo) a).push("a");
		((LiFo) a).push("b");
		((LiFo) a).push("c");
		assertEquals("c", ((LiFo) a).pop());
	}

	/**
	 * testet die Methode pop die Sammlung enthält in diesem Fall 2 Elemente und
	 * hat nocht freie Plaetze das jungste Element wird entfernt und
	 * zurückgegeben
	 */
	@SuppressWarnings("rawtypes")
	@Test // problem
	public void testPop() {
		((LiFo) a).push(1);
		((LiFo) a).push(2);
		assertEquals(2, ((LiFo) a).pop());
	}

	/**
	 * testet die Methode pop die Sammlung enthält in diesem Fall 3 Elemente die
	 * Methode pop wird hier 2 Mal benutzt und beim zweiten Mal getestet das
	 * jungste Element, das sich nach dem ersten pop in der Sammlung befindet
	 * wird zurückgegeben
	 */
	@Test
	public void testPopZweiMal() {
		((LiFo) a).push(1);
		((LiFo) a).push(2);
		((LiFo) a).push(3);
		((LiFo) a).pop();
		assertEquals(2, ((LiFo) a).pop());
	}
}
