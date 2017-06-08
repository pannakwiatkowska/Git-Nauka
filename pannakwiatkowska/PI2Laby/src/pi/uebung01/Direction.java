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
 * Eine Aufzählung der möglichen Himmelsrichtungen für die Orientierung der
 * Spielfeld-Plättchen.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public enum Direction {

   /**
    * Die Himmelsrichtung Norden mit der Ordinalzahl 0.
    */
   NORTH(0),

   /**
    * Die Himmelsrichtung Osten mit der Ordinalzahl 1.
    */
   EAST(1),

   /**
    * Die Himmelsrichtung Süden mit der Ordinalzahl 2.
    */
   SOUTH(2),

   /**
    * Die Himmelsrichtung Westen mit der Ordinalzahl 3.
    */
   WEST(3);

   /**
    * Konstante, die die Anzahl der Richtungen angibt.
    */
   public static final int COUNT = values().length;

   /**
    * Die Ordinalzahl dieser Richtung. Kann nur über den Konstruktor gesetzt werden.
    */
   private final int ordinal;

   /**
    * Erzeugt eine neue Richtung mit der gegebenen Ordinalzahl.
    *
    * @param pOrdinal
    *           Die Ordinalzahl der neuen Richtung.
    */
   Direction(final int pOrdinal) {
      ordinal = pOrdinal;
   }

   /**
    * Gibt die Ordinalzahl dieser Richtung zurück.
    *
    * @return Die Ordinalzahl dieser Richtung.
    */
   public int getOrdinal() {
      return ordinal;
   }

}
