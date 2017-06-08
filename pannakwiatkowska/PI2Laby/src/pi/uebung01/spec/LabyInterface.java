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

import pi.uebung01.Phase;

/**
 * Schnittstelle für das Modell eines Spiels "PI2-verrücktes Labyrinth". Definiert
 * Konstanten für die verschiedenen Phasen einer am Zug befindlichen SpielerIn und
 * stellt diverse Methoden bereit, mit denen der aktuelle Zustand eines solchen Spiels
 * abgefragt werden kann.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public interface LabyInterface {

   /**
    * Gibt das Spielfeld zurück.
    *
    * @return Das Spielfeld.
    */
   BoardInterface getBoard();

   /**
    * Gibt die Spielfigur der SpielerIn mit der angegebenen Nummer (0 oder 1) zurück.
    *
    * @param pPlayerNo
    *           Die Nummer der SpielerIn (0 oder 1).
    * @return Die Spielfigur, die der SpielerIn mit der gegebenen Nummer zugeordnet ist.
    * @throws IllegalArgumentException
    *            Falls eine ungültige Nummer übergeben wird.
    */
   PieceInterface getPiece(int pPlayerNo);

   /**
    * Gibt die aktuelle Phase zurück, d.h. ob ein Plättchen eingeschoben werden muss
    * oder eine Spielfigur gezogen werden muss.
    *
    * @return Die aktuelle Phase.
    */
   Phase getPhase();

   /**
    * Gibt die Nummer der SpielerIn zurück, die gerade an der Reihe ist.
    *
    * @return Die Nummer der SpielerIn, die gerade an der Reihe ist.
    */
   int getActivePlayerNo();

   /**
    * Ändert die aktuelle Phase auf die nächste Phase, d.h. nach dem Einschieben ist ein
    * Zug einer Spielfigur nötig und umgekehrt.
    */
   void nextPhase();

   /**
    * Setzt die Spielfigur der aktuellen SpielerIn auf das Plättchen mit der gegebenen
    * Zeile und der gegebenen Spalte, falls es einen Pfad zu diesem Plättchen vom
    * aktuellen Plättchen der Spielfigur gibt. In diesem Fall wird {@code true}
    * zurückgegeben. Falls es keinen solchen Pfad gibt, wird {@code false}
    * zurückgegeben.
    *
    * @param pRow
    *           Die Zeile in die die Spielfigur bewegt werden soll.
    * @param pColumn
    *           Die Spalte in die die Spielfigur bewegt werden soll.
    * @return {@code true} falls die Spielfigur bewegt wurde, ansonsten {@code false}.
    * @throws IllegalArgumentException
    *            Falls für Zeile und/oder Spalte ungültige Werte übergeben werden.
    *
    */
   boolean setPiece(int pRow, int pColumn);

   /**
    * Gibt die Nummer der SpielerIn zurück, die das Spiel gewonnen hat (0 oder 1). Falls
    * das Spiel noch nicht gewonnen wurde, wird {@code -1} zurückgegeben.
    *
    * @return Die Nummer der SpielerIn, die das Spiel gewonnen hat, oder {@code -1}
    *         falls das Spiel noch läuft.
    */
   int getWinner();

}
