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
package pi.uebung01.spec;

import pi.uebung01.Tile;

/**
 * Schnittstelle für das Modell einer Spielfigur. Eine Spielfigur befindet sich auf
 * einem konkreten Plättchen auf dem Spielfeld.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public interface PieceInterface {

   /**
    * Gibt das Plättchen zurück, auf dem sich diese Spielfigur gerade befindet.
    *
    * @return Das Plättchen, auf dem sich diese Spielfigur gerade befindet.
    */
   Tile getTile();

   /**
    * Setzt die Spielfigur auf das gegebene Plättchen.
    *
    * @param pTile
    *           Das Plättchen, auf das diese Spielfigur gesetzt wird.
    * @throws IllegalArgumentException
    *            Falls der Parameterwert {@code null} ist.
    */
   void setTile(Tile pTile);
}
