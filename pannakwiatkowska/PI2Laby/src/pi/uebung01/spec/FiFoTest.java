package pi.uebung01.spec;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse testet die Klasse FiFo
 * 
 * @author Mahmoud
 *
 */
public class FiFoTest {
	
	private Object a;

	/**
	 * erzeugt eine neue Sammlung vom Typ FiFo
	 */
	@SuppressWarnings("rawtypes")
	@Before
	public void createSammlung() {
		
		a = new FiFo(3);

	}


	/**
	 * testet die Methode push
	 * Es wird Elemente hinzugefügt mehr als die Große der Sammlung, und wird
	 * dementsprechend eine PushPopyException entworfen
	 */
	@Test(expected = PushPopyException.class)
	public void pushSammlungIsFullTest() {
		((FiFo) a).push(1);
		((FiFo) a).push(2);
		((FiFo) a).push(3);
		((FiFo) a).push(4);
	}
	
	/**
	 * testet die Methode push
	 * Es wird null hintzugefügt, und wird dementsprechend eine 
	 * illegalArgumentException entworfen
	 */
	@Test(expected=IllegalArgumentException.class)
	public void pushNullEingeben(){
		((FiFo) a).push(null);
	}
	
	/**
	 * testet die Methode isEmpty
	 * In diesem Fall ist die Sammlung leer.
	 * Es wird true zurückgegeben
	 */
	@Test
	public void testIsEmptyTrue(){
		assertTrue(((FiFo) a).isEmpty());
	}
	
	/**
	 * testet die Methode isEmpty
	 * In diesem Fall ist die Sammmlung nicht leer.
	 * Es wird false zurückgegeben
	 */
	@Test
	public void testIsEmptyFalse(){
		((FiFo) a).push(1);
		assertFalse(((FiFo)a).isEmpty());
	}

	/**
	 * testet die Methode isFull
	 * die Sammlung ist in diesem Fall leer
	 * Es wird false zurückgegeben
	 */
	@Test
	public void testIsFullLeerFalse(){
		assertFalse(((FiFo)a).isFull());
	}
	
	/**
	 * tested die Methode isFull
	 * die Sammlung enthält in diesem Fall nur ein Element und hat noch freie Plaetze
	 * Es wird false zurückgegeben
	 */
	@Test //problem
	public void testIsFullFalse(){
		((FiFo) a).push(1); 
		assertFalse(((FiFo)a).isFull());
	}
	
	/**
	 * tested die Methode isFull
	 * die Sammlung ist in diesem Fall full
	 * Es wird true zurückgegeben
	 */
	@Test //Problem
	public void testIsFullTrue(){
		((FiFo) a).push(1);
		((FiFo) a).push(2);
		((FiFo) a).push(3);
		assertTrue(((FiFo)a).isFull());
	}

	/**
	 * testet die Methode pop
	 * die Sammlung ist in diesem Fall leer
	 * Es wird eine PushPopyException entworfen
	 */
	@Test(expected = PushPopyException.class)
	public void testPopEmpty(){
		((FiFo)a).pop();
	}
	
	/**
	 * testet die Methode pop
	 * die Sammlung ist in diesem Fall full
	 * das aelteste Element wird entfernt und zurückgegeben
	 */
	@Test //problem
	public void testPopFull(){
		((FiFo) a).push(1);
		((FiFo) a).push(2);
		((FiFo) a).push(3);
		assertEquals(1 , ((FiFo) a).pop());
	}
	
	/**
	 * testet die Methode pop
	 * die Sammlung enthält in diesem Fall 2 Elemente und hat nocht freie Plaetze
	 * das aelteste Element wird entfernt und zurückgegeben
	 */
	@Test //problem
	public void testPop(){
		((FiFo) a).push(1);
		((FiFo) a).push(2);
		assertEquals(1, ((FiFo)a).pop());
	}
	
	/**
	 * testet die Methode pop
	 * die Sammlung enthält in diesem Fall 3 Elemente
	 * die Methode pop wird hier 2 Mal benutzt und beim zweiten Mal getestet
	 * das aelteste Element, das sich nach dem ersten pop in der Sammlung befindet
	 * wird zurückgegeben
	 */
	@Test
	public void testPopZweiMal(){
		((FiFo) a).push(1);
		((FiFo) a).push(2);
		((FiFo) a).push(3);
		((FiFo) a).pop();
		assertEquals(2, ((FiFo)a).pop());
		
		
	}
}
