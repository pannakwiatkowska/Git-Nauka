/*
 * Copyright 2017 AG Softwaretechnik, University of Bremen, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package pi.uebung01;

/**
 * TODO: javadoc.
 */
public final class Curve extends Tile {

	/**
	 * Erzeugt ein neues KurvenPlättchen mit der gegebenen Orientierung.
	 *
	 * @param pOrientation
	 *            Die Orientierung des neuen KurvenPlättchens.
	 */
	public Curve(final Direction pOrientation) {
		super(pOrientation);
	}

	@Override
	public boolean hasExit(final Direction pDirection) {
		// throw new UnsupportedOperationException();
		if (getOrientation() == Direction.NORTH) {
			if ((pDirection.getOrdinal() + getOrientation().getOrdinal()) == 1
					|| (pDirection.getOrdinal() + getOrientation().getOrdinal()) == 2) {
				return true;
			}
			
		}

		if (getOrientation() == Direction.SOUTH) {
			if ((pDirection.getOrdinal() + getOrientation().getOrdinal()) == 2
					|| (pDirection.getOrdinal() + getOrientation().getOrdinal()) == 5) {
				return true;
			}
		}

		if (getOrientation() == Direction.EAST) {
			if ((pDirection.getOrdinal() + getOrientation().getOrdinal()) == 3
					|| (pDirection.getOrdinal() + getOrientation().getOrdinal()) == 4) {
				return true;
			}
		}
		if (getOrientation() == Direction.WEST) {
			if ((pDirection.getOrdinal() + getOrientation().getOrdinal()) == 3
					|| (pDirection.getOrdinal() + getOrientation().getOrdinal()) == 4) {
				return true;
			}
		}

		return false;
	}

}
