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
package pi.uebung01.gui;

import static pi.uebung01.Direction.EAST;
import static pi.uebung01.Direction.NORTH;
import static pi.uebung01.Direction.SOUTH;
import static pi.uebung01.Direction.WEST;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import pi.uebung01.PI2Laby;
import pi.uebung01.Phase;
import pi.uebung01.Tile;
import pi.uebung01.spec.BoardInterface;
import pi.uebung01.spec.LabyInterface;
import pi.uebung01.spec.PieceInterface;

/**
 * Realisiert das Panel, auf dem das Spielfeld gezeichnet wird.
 *
 *
 * @author @author K. Hölscher
 * @version 2017-04-06
 */
public final class BoardPanel extends JPanel {

   /**
    * Halbe Größe (Breite und Höhe) eines Pfeils in Pixeln.
    */
   private static final int ARROW_CENTER = 20;

   /**
    * Verschiebung (in beide Richtungen) der Spielfiguren aus dem Zentrum eines
    * Plättchens in Pixeln als ein Viertel der Größe eines Pfeils.
    */
   private static final int PIECE_OFFSET = ARROW_CENTER / 2;

   /**
    * Größe (Breite und Höhe) der Pfeile in Pixeln.
    */
   private static final int ARROW_SIZE = 2 * ARROW_CENTER;

   /**
    * Größe (Breite und Höhe) der Plättchen in Pixeln als das Doppelte der Pfeilgröße.
    */
   public static final int TILE_SIZE = 2 * ARROW_SIZE;

   /**
    * Größe (Breite und Höhe) beider Pfeilbegrenzungen um das eigentliche Spielfeld.
    */
   private static final int MARGIN_SIZE = 2 * ARROW_SIZE;

   /**
    * Bevorzugte Spielfeldgröße in Pixeln als Produkt der Plättchengröße und der
    * Zellenanzahl plus 1 für 2 Pfeile.
    */
   private static final int SIZE = (PI2Laby.NUMBER_OF_CELLS + 1) * TILE_SIZE;

   /**
    * Eineindeutige ID für Serialisierung.
    */
   private static final long serialVersionUID = 8138893420691949465L;

   /**
    * Das Modell des gesamten Spiels - enthält die aktuelle Situation des Spiels. Ist
    * nicht serialisierbar, daher transient.
    */
   private final transient LabyInterface laby;

   /**
    * Das Hauptfenster, in dem dieses Spielfeld-Panel platziert ist. Ist transient, weil
    * es nicht serialisierbar ist.
    */
   private final transient LabyGUI gui;

   /**
    * Das Modell des Spielfeldes - enthält die aktuell zu zeichnende Situation. Ist
    * nicht serialisierbar, daher transient.
    */
   private final transient BoardInterface field;

   /**
    * Erzeugt ein neues SpielfeldPanel für das gegebene SpielModell und das gegebene
    * Hauptfenster.
    *
    * @param pLaby
    *           Das Modell des Spiels.
    * @param pGui
    *           Die GUI mit dem Hauptfenster.
    */
   public BoardPanel(final LabyInterface pLaby, final LabyGUI pGui) {
      super();
      setBorder(new EtchedBorder(EtchedBorder.RAISED));
      setPreferredSize(new Dimension(SIZE, SIZE));
      laby = pLaby;
      gui = pGui;
      field = laby.getBoard();
      addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(final MouseEvent e) {
            int xcoor = e.getX();
            int ycoor = e.getY();
            if (laby.getPhase() == Phase.INSERTION) {
               if (insert(xcoor, ycoor)) {
                  laby.nextPhase();
                  gui.displayNextPhase();
               }
            } else {
               if (setPiece(xcoor, ycoor)) {
                  laby.nextPhase();
                  gui.displayNextPhase();
               }
            }
         }

      });
   }

   /**
    * Berechnet aus den gegebenen Koordinaten die Zeile und Spalte des entsprechenden
    * Plättchens auf dem Spielfeld und informiert das Modell des Spiels darüber, dass
    * die aktuell am Zug befindliche Spielfigur auf dieses Plättchens gesetzt werden
    * soll. Wenn das Modell das Setzen erlaubt, wird {@code true} zurückgegeben,
    * anderenfalls {@code false}.
    *
    * @param pXcoor
    *           Die X-Koordinate des ZielPlättchens innerhalb dieses Panels.
    * @param pYcoor
    *           Die Y-Koordinate des ZielPlättchens innerhalb dieses Panels.
    * @return {@code true} falls die aktuelle Spielfigur auf das errechnete Plättchen
    *         ziehen darf, anderenfalls {@code false}.
    */
   private boolean setPiece(final int pXcoor, final int pYcoor) {
      if (pXcoor > 0 && pXcoor < ARROW_SIZE
            || pXcoor < SIZE && pXcoor >= SIZE - ARROW_SIZE) {
         return false;
      }
      if (pYcoor > 0 && pYcoor < ARROW_SIZE
            || pYcoor < SIZE && pYcoor >= SIZE - ARROW_SIZE) {
         return false;
      }
      final int row = calculateCell(pYcoor);
      final int column = calculateCell(pXcoor);
      return laby.setPiece(row, column);
   }

   /**
    * Berechnet aus den gegebenen Koordinaten innerhalb dieses Spielfeldes die Zeile und
    * Spalte, an der das SchiebePlättchen eingeschoben werden soll. Falls keine gültige
    * Einschubposition vorliegt, wird {@code false} zurückgegeben und nichts weiter
    * verändert. Falls die Koordinaten eine gültige Einschubposition angeben, wird diese
    * Einschubposition an das Modell des Spielfeldes übermittelt, welches dann den neuen
    * Zustand des Spielfeldes durch den Einschub errechnet. In diesem Fall wird
    * {@code true} zurückgegeben.
    *
    * @param pXcoor
    *           Die X-Koordinate des Einschubbereiches innerhalb dieses Panels.
    * @param pYcoor
    *           Die Y-Koordinate des Einschubbereiches innerhalb dieses Panels.
    * @return {@code true} falls es sich bei den Koordinaten um eine gültige
    *         Einschubposition handelt, anderenfalls {@code false}.
    */
   private boolean insert(final int pXcoor, final int pYcoor) {
      int row = calculateInsertionIndex(pYcoor);
      int column = calculateInsertionIndex(pXcoor);

      if (row == -1 && column == -1) {
         return false;
      }

      if (row == 0 || row == PI2Laby.LAST_CELL_INDEX) {
         column = calculateCell(pXcoor);
         if (column % 2 == 0) {
            return false;
         }
      }
      if (column == 0 || column == PI2Laby.LAST_CELL_INDEX) {
         row = calculateCell(pYcoor);
         if (row % 2 == 0) {
            return false;
         }
      }

      field.insert(row, column);
      return true;
   }

   /**
    * Errechnet aus der gegebenen Koordinate innerhalb dieses Spielfeldes die konkrete
    * Zeile oder Spalte der Koordinate, falls es sich um die erste oder letzte Zeile
    * bzw. Spalte handelt. Gibt dementsprechend entweder 0 oder 6 als gültiges Ergebnis
    * zurück. Falls es sich bei der Koordinate nicht um die erste oder letzte Zeile bzw.
    * Spalte handelt, wird -1 zurückgegeben.
    *
    * @param pCoor
    *           Die Koordinate innerhalb dieses Spielfeldes.
    * @return Die Zeile bzw. Spalte, die der gegebenen Koordinate entspricht oder -1
    *         wenn es nicht die erste oder letzte Zeile bzw. Spalte ist.
    */
   private int calculateInsertionIndex(final int pCoor) {
      if (pCoor > 0 && pCoor < ARROW_SIZE) {
         return 0;
      } else if (pCoor < SIZE && pCoor >= SIZE - ARROW_SIZE) {
         return PI2Laby.LAST_CELL_INDEX;
      } else {
         return -1;
      }
   }

   /**
    * Berechnet die Zeile bzw. Spalte zu der gegebenen Koordinate innerhalb dieses
    * Spielfeldes. Kann eine Zahl zwischen 0 und 6 sein (einschl.). Falls die Koordinate
    * keine gültige Zeile bzw. Spalte auf dem Spielfeld ergibt, wird -1 zurückgegeben.
    *
    * @param pCoor
    *           Die Koordinate innerhalb dieses Spielfeldes.
    * @return Die Zeile bzw. Spalte zu der gegebenen Koordinate innerhalb des
    *         Spielfeldgitters oder -1, falls die Koordinate einem Bereich außerhalb des
    *         Spielfeldgitters entspricht.
    */
   private int calculateCell(final int pCoor) {
      if (pCoor > 0 && pCoor < ARROW_SIZE
            || pCoor < SIZE && pCoor >= SIZE - ARROW_SIZE) {
         return -1;
      }
      return (pCoor - ARROW_SIZE) / MARGIN_SIZE;
   }

   /**
    * {@inheritDoc} Zeichnet die EinschubPfeile für das SchiebePlättchen, die aktuellen
    * Plättchen auf dem Spielfeld in ihrer aktuellen Orientierung und die Spielfiguren
    * auf ihren entsprechenden Plättchen.
    */
   @Override
   protected void paintComponent(final Graphics g) {
      super.paintComponent(g);

      // Pfeile
      int offset = ARROW_SIZE + TILE_SIZE + ARROW_CENTER;
      for (int coor = offset; coor <= SIZE - offset; coor += 2 * TILE_SIZE) {
         g.drawImage(gui.getArrowImage(EAST.getOrdinal()), 0, coor, this);
         g.drawImage(gui.getArrowImage(WEST.getOrdinal()), SIZE - ARROW_SIZE, coor,
               this);
         g.drawImage(gui.getArrowImage(SOUTH.getOrdinal()), coor, 0, this);
         g.drawImage(gui.getArrowImage(NORTH.getOrdinal()), coor, SIZE - ARROW_SIZE,
               this);
      }

      // plättchen
      for (int row = 0; row < PI2Laby.NUMBER_OF_CELLS; row++) {
         for (int column = 0; column < PI2Laby.NUMBER_OF_CELLS; column++) {
            Tile tile = field.getTile(row, column);
            g.drawImage(gui.getTileImage(tile), column * MARGIN_SIZE + ARROW_SIZE,
                  row * MARGIN_SIZE + ARROW_SIZE, this);
         }
      }

      // spielfiguren
      PieceInterface piece1 = laby.getPiece(0);
      PieceInterface piece2 = laby.getPiece(1);
      Tile tile1 = piece1.getTile();
      Tile tile2 = piece2.getTile();
      if (tile1 == tile2) {
         g.drawImage(gui.getPieceImage(0),
               ARROW_SIZE + tile1.getColumn() * MARGIN_SIZE + PIECE_OFFSET,
               ARROW_SIZE + tile1.getRow() * MARGIN_SIZE + PIECE_OFFSET, this);
         g.drawImage(gui.getPieceImage(1),
               ARROW_SIZE + tile2.getColumn() * MARGIN_SIZE + ARROW_SIZE - PIECE_OFFSET,
               ARROW_SIZE + tile2.getRow() * MARGIN_SIZE + ARROW_SIZE - PIECE_OFFSET,
               this);
      } else {
         g.drawImage(gui.getPieceImage(0),
               ARROW_SIZE + tile1.getColumn() * MARGIN_SIZE + ARROW_CENTER,
               ARROW_SIZE + tile1.getRow() * MARGIN_SIZE + ARROW_CENTER, this);
         g.drawImage(gui.getPieceImage(1),
               ARROW_SIZE + tile2.getColumn() * MARGIN_SIZE + ARROW_CENTER,
               ARROW_SIZE + tile2.getRow() * MARGIN_SIZE + ARROW_CENTER, this);
      }
   }

}
