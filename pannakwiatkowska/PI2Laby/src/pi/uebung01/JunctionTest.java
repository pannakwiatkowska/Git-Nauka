package pi.uebung01;

import static org.junit.Assert.*;

import org.junit.Test;

public class JunctionTest {

	@Test
	public void JunctionNorthNorth() {
		final Junction test= new Junction(Direction.NORTH);
		 assertFalse(test.hasExit(Direction.NORTH));
	}
	@Test
	public void JunctionNorthEast() {
		final Junction test= new Junction(Direction.NORTH);
		 assertTrue(test.hasExit(Direction.EAST));
	}
	@Test
	public void JunctionNorthSouth() {
		final Junction test= new Junction(Direction.NORTH);
		 assertTrue(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void JunctionNorthWest() {
		final Junction test= new Junction(Direction.NORTH);
		 assertTrue(test.hasExit(Direction.WEST));
	}
	@Test
	public void JunctionEastNorth() {
		final Junction test= new Junction(Direction.EAST);
		 assertTrue(test.hasExit(Direction.NORTH));
	}
	@Test
	public void JunctionEastEast() {
		final Junction test= new Junction(Direction.EAST);
		 assertFalse(test.hasExit(Direction.EAST));
	}
	@Test
	public void JunctionEastSouth() {
		final Junction test= new Junction(Direction.EAST);
		 assertTrue(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void JunctionEastWest() {
		final Junction test= new Junction(Direction.EAST);
		 assertTrue(test.hasExit(Direction.WEST));
	}
	@Test
	public void JunctionSouthNorth() {
		final Junction test= new Junction(Direction.SOUTH);
		 assertTrue(test.hasExit(Direction.NORTH));
	}
	@Test
	public void JunctionSouthEast() {
		final Junction test= new Junction(Direction.SOUTH);
		 assertTrue(test.hasExit(Direction.EAST));
	}
	@Test
	public void JunctionSouthSouth() {
		final Junction test= new Junction(Direction.SOUTH);
		 assertFalse(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void JunctionSouthWest() {
		final Junction test= new Junction(Direction.SOUTH);
		 assertTrue(test.hasExit(Direction.WEST));
	}
	@Test
	public void JunctionWestNorth() {
		final Junction test= new Junction(Direction.WEST);
		 assertTrue(test.hasExit(Direction.NORTH));
	}
	@Test
	public void JunctionWestEast() {
		final Junction test= new Junction(Direction.WEST);
		 assertTrue(test.hasExit(Direction.EAST));
	}
	@Test
	public void JunctionWestSouth() {
		final Junction test= new Junction(Direction.WEST);
		 assertTrue(test.hasExit(Direction.SOUTH));
	}
	@Test
	public void JunctionWestWest() {
		final Junction test= new Junction(Direction.WEST);
		 assertFalse(test.hasExit(Direction.WEST));
	}
}
