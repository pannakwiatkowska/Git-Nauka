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
 * Realisiert eine abstrakte Oberklasse für konkrete Plättchen, in dem die gemeinsamen
 * Eigenschaften zusammengefasst werden.
 *
 * Ein Plättchen hat eine Spalte, eine Zeile und eine Orientierung (als
 * {@linkplain Direction}). Für diese Eigenschaften stellt die Klasse getter- und
 * setter-Methoden bereit.
 *
 * Darüber hinaus muss eine konkrete Subklasse eine Methode anbieten, mit der
 * festgestellt werden kann, ob das Plättchen in seiner aktuellen Orientierung einen
 * Ausgang in eine gegebene Himmelsrichtung hat.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public abstract class Tile {

   /**
    * Die Orientierung dieses Plättchens als Himmelsrichtung.
    */
   private Direction orientation;

   /**
    * Die Zeile des Spielfeldes, in der sich dieses Plättchen befindet.
    */
   private int row;

   /**
    * Die Spalte des Spielfeldes, in der sich dieses Plättchen befindet.
    */
   private int column;

   /**
    * Erzeugt ein neues Plättchen mit der gegebenen Orientierung.
    *
    * @param pOrientation
    *           Die Orientierung des neuen Plättchens.
    */
   protected Tile(final Direction pOrientation) {
      orientation = pOrientation;
   }

   /**
    * Gibt die aktuelle Orientierung dieses Plättchens zurück.
    *
    * @return Die aktuelle Orientierung.
    */
   public final Direction getOrientation() {
      return orientation;
   }

   /**
    * Setzt die aktuelle Orientierung dieses Plättchens auf die übergebene Orientierung.
    *
    * @param pOrientation
    *           Die neue Orientierung.
    */
   public final void setOrientation(final Direction pOrientation) {
      orientation = pOrientation;
   }

   /**
    * Setzt die Zeile, in der sich dieses Plättchen auf dem Spielfeld befindet, auf die
    * übergebene Zeile. Falls der Wert für die neue Zeile ungültig ist, wird eine
    * {@link IllegalArgumentException} ausgelöst.
    *
    * @param pRow
    *           Die neue Zeile für dieses Plättchen.
    */
   public final void setRow(final int pRow) {
      if (pRow < 0 || pRow > PI2Laby.LAST_CELL_INDEX) {
         throw new IllegalArgumentException();
      }
      row = pRow;
   }

   /**
    * Setzt die Spalte, in der sich dieses Plättchen auf dem Spielfeld befindet, auf die
    * übergebene Spalte. Falls der Wert für die neue Spalte ungültig ist, wird eine
    * {@link IllegalArgumentException} ausgelöst.
    *
    * @param pColumn
    *           Die neue Spalte für dieses Plättchen.
    */
   public final void setColumn(final int pColumn) {
      if (pColumn < 0 || pColumn > PI2Laby.LAST_CELL_INDEX) {
         throw new IllegalArgumentException();
      }
      column = pColumn;
   }

   /**
    * Gibt die Zeile zurück, in der sich dieses Plättchen auf dem Spielfeld befindet.
    *
    * @return Die Zeile, in der sich dieses Plättchen befindet.
    */
   public final int getRow() {
      return row;
   }

   /**
    * Gibt die Spalte zurück, in der sich dieses Plättchen auf dem Spielfeld befindet.
    *
    * @return Die Spalte, in der sich dieses Plättchen befindet.
    */
   public final int getColumn() {
      return column;
   }

   /**
    * Ermittelt, ob dieses Plättchen in der aktuellen Orientierung einen Ausgang in der
    * gegebenen Himmelsrichtung hat. Falls das so ist, wird {@code true} zurückgegeben,
    * anderenfalls {@code false}.
    *
    * @param pDirection
    *           Die Himmelsrichtung.
    * @return {@code true}, falls es einen Ausgang in der gegebenen Himmelsrichtung
    *         gibt, ansonsten {@code false}.
    */
   public abstract boolean hasExit(Direction pDirection);

}
