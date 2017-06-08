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
 * Schnittstelle für das Modell des Spielfeldes. Ein Spielfeld enthält ein Gitter von
 * 7x7 Plättchen, angeordnet in Zeilen und Spalten. Zusätzlich enthält das Spielfeld ein
 * SchiebePlättchen, das nicht Teil der GitterPlättchen ist, sondern zum Einschub
 * verwendet wird. Dieses Modell berechnet, ob es einen gültigen Weg vom Plättchen einer
 * gegebenen Spielfigur zu einem angegebenen ZielPlättchen gibt.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */

public interface BoardInterface {

   /**
    * Initialisiert das Spielfeld, d.h. legt ein Gitter von Plättchen an und befüllt
    * diese mit zufälligen Plättchen. Erzeugt ebenfalls das SchiebePlättchen, das für
    * den ersten Einschub benötigt wird.
    */
   void configure();

   /**
    * Gibt das Plättchen in der gegebenen Zeile und Spalte zurück. 
    *
    * @param pRow
    *           Die Zeile des gesuchten Plättchens.
    * @param pColumn
    *           Die Spalte des gesuchten Plättchens.
    * @return Das gesuchte Plättchen.
    * @throws IllegalArgumentException
    *            Falls für Zeile und/oder Spalte ungültige Werte übergeben wurden.
    */
   Tile getTile(int pRow, int pColumn);

   /**
    * Realisiert das Einschieben des SchiebePlättchens an der gegebenen Zeile und
    * Spalte. Alle Plättchen "davor" werden um einen Platz verschoben und das
    * gegenüberliegende Plättchen wird zum neuen SchiebePlättchen.
    *
    * @param pRow
    *           Die Zeile, in der das Plättchen eingeschoben werden soll.
    * @param pColumn
    *           Die Spalte, in der das Plättchen eingeschoben werden soll.
    * @throws IllegalArgumentException
    *            Falls für Zeile und/oder Spalte ungültige Werte übergeben wurden.
    */
   void insert(int pRow, int pColumn);

   /**
    * Gibt das aktuelle SchiebePlättchen zurück.
    *
    * @return Das aktuelle SchiebePlättchen.
    */
   Tile getInsertionTile();

   /**
    * Dreht das aktuelle SchiebePlättchen um 90 Grad nach rechts.
    */
   void turnInsertionTileRight();

   /**
    * Dreht das aktuelle SchiebePlättchen um 90 Grad nach links.
    */
   void turnInsertionTileLeft();

   /**
    * Ermittelt, ob es einen Weg gibt vom Plättchen auf dem sich die gegebene Spielfigur
    * befindet zu dem Plättchen in der gegebenen Zeile und Spalte. Falls es einen
    * solchen Weg gibt, wird {@code true} zurückgegeben, ansonsten {@code false}.
    *
    * @param pPiece
    *           Die Spielfigur, von deren Plättchen aus der Weg gesucht wird.
    * @param pRow
    *           Die Zeile in der sich das ZielPlättchen befindet.
    * @param pColumn
    *           Die Spalte in der sich das ZielPlättchen befindet.
    * @return {@code true} falls es einen Weg gibt, ansonsten {@code false}.
    * @throws IllegalArgumentException
    *            Falls für Zeile und/oder Spalte ungültige Werte übergeben wurden oder
    *            der Wert der Spielfigur {@code null} ist.
    *
    */
   boolean existsPath(PieceInterface pPiece, int pRow, int pColumn);

}
