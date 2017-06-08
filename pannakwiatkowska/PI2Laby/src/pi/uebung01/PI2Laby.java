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

import javax.swing.SwingUtilities;

import pi.uebung01.gui.LabyGUI;
import pi.uebung01.spec.LabyInterface;
import pi.uebung01.spec.BoardInterface;
import pi.uebung01.spec.PieceInterface;

/**
 * Realisiert das Modell eines Spiels "PI2-verrücktes Labyrinth". Verwaltet das
 * Spielfeld und stellt diverse Methoden bereit, mit denen der aktuelle Zustand eines
 * solchen Spiels abgefragt werden kann.
 *
 * @author K. Hölscher
 * @version 2017-04-07
 *
 */
public final class PI2Laby implements LabyInterface {

   /**
    * Die Anzahl verschiedener Plättchenarten.
    */
   public static final int NO_OF_TILETYPES = 3;

   /**
    * Die Anzahl der Zellen/Plättchen pro Zeile und Spalte auf dem Spielfeld.
    */
   public static final int NUMBER_OF_CELLS = 7;

   /**
    * Der Index der letzten Zelle in einer Zeile bzw. Spalte.
    */
   public static final int LAST_CELL_INDEX = NUMBER_OF_CELLS - 1;

   /**
    * Das Spielfeld für dieses Spiel.
    */
   private final BoardInterface board;

   /**
    * Die Spielfiguren dieses Spiels.
    */
   private final PieceInterface[] pieces;

   /**
    * Nummer der SpielerIn, die aktuell am Zug ist.
    */
   private int activePlayer;

   /**
    * Nummer der SpielerIn, die das Spiel gewonnen hat. Hat den Wert -1 solange es noch
    * keine GewinnerIn gibt.
    */
   private int winner;

   /**
    * Die Phase des Spiels.
    */
   private Phase phase;

   /**
    * Erzeugt ein neues Modell für das Spiel. Dabei wird das Spielfeld initialisiert,
    * d.h. mit zufälligen Plättchen gefüllt und die Spielfiguren erzeugt. Die aktuelle
    * Phase wird auf Einschub gesetzt und die SpielerIn mit der Nummer 0 wird als
    * SpielerIn am Zug gesetzt.
    */
   public PI2Laby() {
      board = new Board(this);
      pieces = new PieceInterface[2];
      board.configure();
      pieces[0] = new Piece(board.getTile(0, 0));
      pieces[1] = new Piece(board.getTile(0, PI2Laby.NUMBER_OF_CELLS - 1));
      activePlayer = 0;
      winner = -1;
      phase = Phase.INSERTION;
   }

   @Override
   public BoardInterface getBoard() {
      return board;
   }

   @Override
   public PieceInterface getPiece(final int pPlayerNo) {
      if (pPlayerNo < 0 || pPlayerNo > 1) {
         throw new IllegalArgumentException(
               "SpielerInnen-Nummer " + pPlayerNo + " is ungültig!");
      }
      return pieces[pPlayerNo];
   }

   @Override
   public Phase getPhase() {
      return phase;
   }

   @Override
   public int getActivePlayerNo() {
      return activePlayer;
   }

   @Override
   public void nextPhase() {
      if (phase == Phase.INSERTION) {
         phase = Phase.MOVE;
      } else {
         phase = Phase.INSERTION;
         activePlayer = (activePlayer + 1) % 2;
      }
   }

   @Override
   public boolean setPiece(final int pRow, final int pColumn) {
      if (pRow < 0 || pRow >= PI2Laby.NUMBER_OF_CELLS || pColumn < 0
            || pColumn >= PI2Laby.NUMBER_OF_CELLS) {
         throw new IllegalArgumentException(
               "Einer oder beide Parameterwerte sind ungültig. Zeile: " + pRow
                     + ", Spalte: " + pColumn);
      }
      final PieceInterface piece = pieces[activePlayer];
      if (board.existsPath(piece, pRow, pColumn)) {
         final Tile tile = board.getTile(pRow, pColumn);
         piece.setTile(tile);
         if (hasWon(activePlayer)) {
            winner = activePlayer;
         }
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getWinner() {
      return winner;
   }

   /**
    * Gibt an, ob die Spielfigur der SpielerIn mit der gegebenen SpielerInNummer ihr
    * Zielfeld unten rechts bzw. links erreicht hat.
    *
    * Der Parameterwert wird nicht auf Plausibilität geprüft, da die Methode nicht
    * öffentlich ist.
    *
    * @param pPlayerNo
    *           die Nummer der SpielerIn
    * @return {@code true} falls die Spielfigur der SpielerIn mit der gegebenen
    *         SpielerInNummer ihr Zielfeld erreicht hat, sonst {@code false}
    */
   private boolean hasWon(final int pPlayerNo) {
      final PieceInterface piece = pieces[pPlayerNo];
      final Tile tile = piece.getTile();
      if (tile.getRow() == PI2Laby.LAST_CELL_INDEX) {
         return tile.getColumn()
               + (pPlayerNo * PI2Laby.LAST_CELL_INDEX) == PI2Laby.LAST_CELL_INDEX;
      }
      return false;
   }

   /**
    * Erzeugt ein neues Spiel und erzeugt dann die Benutzungsschnittstelle und zeigt
    * diese an.
    *
    * @param args
    *           Werden ignoriert.
    */
   public static void main(final String... args) {
      final PI2Laby laby = new PI2Laby();
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new LabyGUI(laby);
         }
      });
   }
}
