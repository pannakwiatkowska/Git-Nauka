package pi.uebung01;

import static org.junit.Assert.*;

import org.junit.Test;

public class StraightTest {

	@Test
	public void NorthNorth() {
	 final Straight test= new Straight(Direction.NORTH);
	 assertTrue(test.hasExit(Direction.NORTH));
	}

	@Test
	public void NorthEast() {
		 final Straight test= new Straight(Direction.NORTH);
		 assertFalse(test.hasExit(Direction.EAST));
		}
	@Test
	public void NorthSouth() {
		 final Straight test= new Straight(Direction.NORTH);
		 assertTrue(test.hasExit(Direction.SOUTH));
		}
	@Test
	public void NorthWest() {
		 final Straight test= new Straight(Direction.NORTH);
		 assertFalse(test.hasExit(Direction.WEST));
		}
	@Test
	
	public void EastNorth() {
		 final Straight test= new Straight(Direction.EAST);
		 assertFalse(test.hasExit(Direction.NORTH));
		}
	@Test

		public void EastEast() {
			 final Straight test= new Straight(Direction.EAST);
			 assertTrue(test.hasExit(Direction.EAST));
			}
	@Test
		public void EastSouth() {
			 final Straight test= new Straight(Direction.EAST);
			 assertFalse(test.hasExit(Direction.SOUTH));
			}
	@Test
		public void EastWest() {
			 final Straight test= new Straight(Direction.EAST);
			 assertTrue(test.hasExit(Direction.WEST));
			}
	@Test
		public void SouthNorth() {
			 final Straight test= new Straight(Direction.SOUTH);
			 assertTrue(test.hasExit(Direction.NORTH));
			}
	@Test
			public void SouthEast() {
				 final Straight test= new Straight(Direction.SOUTH);
				 assertFalse(test.hasExit(Direction.EAST));
				}
	@Test
			public void SouthSouth() {
				 final Straight test= new Straight(Direction.SOUTH);
				 assertTrue(test.hasExit(Direction.SOUTH));
				}
	@Test
			public void SouthWest() {
				 final Straight test= new Straight(Direction.SOUTH);
				 assertFalse(test.hasExit(Direction.WEST));
				}
	@Test		
			public void WestNorth() {
				 final Straight test= new Straight(Direction.WEST);
				 assertFalse(test.hasExit(Direction.NORTH));
				}
	@Test
				public void WestEast() {
					 final Straight test= new Straight(Direction.WEST);
					 assertTrue(test.hasExit(Direction.EAST));
				}
	@Test
				public void WestSouth() {
					 final Straight test= new Straight(Direction.WEST);
					 assertFalse(test.hasExit(Direction.SOUTH));
					}
	@Test
				public void WestWest() {
					 final Straight test= new Straight(Direction.WEST);
					 assertTrue(test.hasExit(Direction.WEST));
					}
	
}
