package pi.uebung01;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurveTest {

	@Test
	public void NorthNorth() {
		final Curve test= new Curve(Direction.NORTH);
		 assertFalse(test.hasExit(Direction.NORTH));
	}
	@Test
	public void NorthEast() {
		final Curve test= new Curve(Direction.NORTH);
		 assertTrue(test.hasExit(Direction.EAST));
	}
	@Test
	public void NorthSouth() {
		final Curve test= new Curve(Direction.NORTH);
		 assertTrue(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void NorthWest() {
		final Curve test= new Curve(Direction.NORTH);
		 assertFalse(test.hasExit(Direction.WEST));
	}
	@Test
	public void EastNorth() {
		final Curve test= new Curve(Direction.EAST);
		 assertFalse(test.hasExit(Direction.NORTH));
	}
	@Test
	public void EastEast() {
		final Curve test= new Curve(Direction.EAST);
		 assertFalse(test.hasExit(Direction.EAST));
	}
	@Test
	public void EastSouth() {
		final Curve test= new Curve(Direction.EAST);
		 assertTrue(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void EastWest() {
		final Curve test= new Curve(Direction.EAST);
		 assertTrue(test.hasExit(Direction.WEST));
	}
	@Test
	public void SouthNorth() {
		final Curve test= new Curve(Direction.SOUTH);
		 assertTrue(test.hasExit(Direction.NORTH));
	}
	@Test
	public void SouthEast() {
		final Curve test= new Curve(Direction.SOUTH);
		 assertFalse(test.hasExit(Direction.EAST));
	}
	@Test
	public void SouthSouth() {
		final Curve test= new Curve(Direction.SOUTH);
		 assertFalse(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void SouthWest() {
		final Curve test= new Curve(Direction.SOUTH);
		 assertTrue(test.hasExit(Direction.WEST));
	}
	@Test
	public void WestNorth() {
		final Curve test= new Curve(Direction.WEST);
		 assertTrue(test.hasExit(Direction.NORTH));
	}
	@Test
	public void WestEast() {
		final Curve test= new Curve(Direction.WEST);
		 assertTrue(test.hasExit(Direction.EAST));
	}
	@Test
	public void WestSouth() {
		final Curve test= new Curve(Direction.WEST);
		 assertFalse(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void WestWest() {
		final Curve test= new Curve(Direction.WEST);
		 assertFalse(test.hasExit(Direction.WEST));
	}
}
