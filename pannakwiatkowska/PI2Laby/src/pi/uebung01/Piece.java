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

import pi.uebung01.spec.PieceInterface;

/**
 * Realisiert das Modell einer Spielfigur. Eine Spielfigur befindet sich auf einem
 * konkreten Plättchen auf dem Spielfeld.
 *
 * @author K. Hölscher
 * @version 2017-04-07
 */
public final class Piece implements PieceInterface {

   /**
    * Das Plättchen, auf dem sich diese Spielfigur aktuell befindet.
    */
   private Tile tile;

   /**
    * Erzeugt eine neue Spielfigur und setzt das gegebene Plättchen als aktuelles
    * Plättchen. Falls der Parameterwert {@code null} übergeben wurde, wird eine
    * {@link IllegalArgumentException} ausgelöst.
    *
    * @param pTile
    *           Das Plättchen, auf dem sich die neue Spielfigur befindet.
    */
   public Piece(final Tile pTile) {
      if (pTile == null) {
         throw new IllegalArgumentException(
               "Plättchen für die neue Spielfigur ist null!");
      }
      tile = pTile;
   }

   @Override
   public Tile getTile() {
      return tile;
   }	

   @Override
   public void setTile(final Tile pTile) {
      if (pTile == null) {
         throw new IllegalArgumentException(
               "Neues Plättchen für die Spielfigur ist null!");
      }
      tile = pTile;
   }

}
