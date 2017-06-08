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

import static pi.uebung01.gui.BoardPanel.TILE_SIZE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pi.uebung01.Curve;
import pi.uebung01.Direction;
import pi.uebung01.Junction;
import pi.uebung01.PI2Laby;
import pi.uebung01.Phase;
import pi.uebung01.Tile;
import pi.uebung01.spec.BoardInterface;
import pi.uebung01.spec.LabyInterface;

/**
 * Realisiert das Hauptfenster der Benutzungsschnittstelle für das Spiel "PI2 verrücktes
 * Labyrinth".
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public final class LabyGUI {

   /**
    * Pfad zu den Bilddateien (relativ zum Arbeitsverzeichnis).
    */
   private static final String IMAGE_PATH = "images";

   /**
    * Enthält die Bezeichnungen der konkreten Plättchen-Klassen.
    */
   private static final String[] TILE_TYPES = {"Junction", "Curve", "Straight"};

   /**
    * Fester Abstand zwischen dem Rotationspanel und dem oberen Ende des Panels.
    */
   private static final int RIGID_AREA_ONE = 160;

   /**
    * Fester Abstand zwischen dem Rotationspanel und der Zuganzeige.
    */
   private static final int RIGID_AREA_TWO = 110;

   /**
    * Enthält die Bilder mit den EinschubPfeilen (je eins pro Richtung).
    */
   private final BufferedImage[] arrowImages = new BufferedImage[Direction.COUNT];

   /**
    * Das Bild für den Rotationspfeil im Uhrzeigersinn.
    */
   private BufferedImage turnClockImage;

   /**
    * Das Bild für den Rotationspfeil im Gegenuhrzeigersinn.
    */
   private BufferedImage turnCounterClockImage;

   /**
    * Enthält die Bilder der SpielfeldPlättchen. Es gibt drei verschiedene Plättchen (1.
    * Dimension) und je eins pro 90 Grad Drehung (2. Dimension).
    */
   private final BufferedImage[][] tileImages =
         new BufferedImage[PI2Laby.NO_OF_TILETYPES][Direction.COUNT];

   /**
    * Enthält die Bilder für die Spielfiguren.
    */
   private final BufferedImage[] pieceImages = new BufferedImage[2];

   /**
    * Das Hauptfenster dieser GUI.
    */
   private final JFrame mainFrame;

   /**
    * Das Modell des Spielfeldes - enthält die aktuell zu zeichnende Situation.
    */
   private final BoardInterface board;

   /**
    * Die Schaltfläche zum Drehen des SchiebePlättchens um 90 Grad im Uhrzeigersinn.
    */
   private JButton clockButton;

   /**
    * Die Schaltfläche zum Drehen des SchiebePlättchens um 90 Grad gegen den
    * Uhrzeigersinn.
    */
   private JButton counterButton;

   /**
    * Das Label, in dem die Spielfigur der SpielerIn angezeigt wird, die gerade am Zug
    * ist.
    */
   private JLabel active;

   /**
    * Das Label, in dem angezeigt wird, in welcher Phase sich die aktuelle SpielerIn
    * gerade befindet.
    */
   private JLabel phase;

   /**
    * Enthält das Bild der ersten Spielfigur.
    */
   private ImageIcon imagePlayer1;

   /**
    * Enthält das Bild der zweiten Spielfigur.
    */
   private ImageIcon imagePlayer2;

   /**
    * Das Modell des gesamten Spiels - enthält die aktuelle Situation des Spiels.
    */
   private final LabyInterface laby;

   /**
    * Erzeugt ein neues Fenster, in dem die aktuell durch den Parameter gegebene
    * Spielsituation dargestellt wird. Erlaubt durch Schaltflächen und anklickbare
    * Spielfelder eine Interaktion mit dem Modell des Spiels.
    *
    * @param pLaby
    *           Das Modell des darzustellenden Spiels.
    * @throws IllegalStateException
    *            Falls eine erwartete Bilddatei nicht geladen werden kann (siehe
    *            {@link LabyImages}).
    */
   public LabyGUI(final LabyInterface pLaby) {
      mainFrame = new JFrame("PI2 verrücktes Labyrinth");
      laby = pLaby;
      board = laby.getBoard();
      loadImages();
      configureMainFrame();

      configurePanels();

      mainFrame.pack();
      mainFrame.setVisible(true);
   }

   /**
    * Konfiguriert die Anzeige-Panels.
    */
   private void configurePanels() {
      final JPanel displayPanel = new JPanel();
      displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
      mainFrame.getContentPane().add(displayPanel);

      final JPanel tileDisplay = createTileDisplay();
      final JPanel turnDisplay = createTurnDisplay();

      displayPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_ONE)));
      displayPanel.add(tileDisplay);

      displayPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_TWO)));

      displayPanel.add(turnDisplay);
   }

   /**
    * Erzeugt das Panel für die Anzeige des Einschiebeplättchens mit den
    * Rotationsschaltflächen und gibt das neu erzeugte Panel zurück.
    *
    * @return Das neu erzeugte Panel für die Anzeige des Einschiebeplättchens.
    */
   private JPanel createTileDisplay() {
      final JPanel tileDisplay = new JPanel();
      tileDisplay.setLayout(new FlowLayout());
      @SuppressWarnings("serial") // wird nicht serialisiert
      final JPanel tilePanel = new JPanel() {
         /**
          * {@inheritDoc} Ermittelt anhand des SpielModells das aktuelle
          * SchiebePlättchen und dessen aktuelle Orientierung und zeichnet das
          * entsprechende Bild.
          */
         @Override
         protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            Tile tile = board.getInsertionTile();
            g.drawImage(getTileImage(tile), 0, 0, this);
         }

      };
      tilePanel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));

      final ImageIcon clockIcon = new ImageIcon(turnClockImage);
      clockButton = new JButton(clockIcon);
      clockButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(final ActionEvent e) {
            board.turnInsertionTileRight();
            mainFrame.repaint();
         }
      });

      final ImageIcon counterIcon = new ImageIcon(turnCounterClockImage);
      counterButton = new JButton(counterIcon);
      counterButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(final ActionEvent e) {
            board.turnInsertionTileLeft();
            mainFrame.repaint();
         }
      });

      tileDisplay.add(clockButton);
      tileDisplay.add(tilePanel);
      tileDisplay.add(counterButton);
      return tileDisplay;
   }

   /**
    * Erzeugt das Panel für die Zuganzeige und gibt es zurück.
    *
    * @return Das Panel für die Zuganzeige.
    */
   private JPanel createTurnDisplay() {
      final JPanel turnDisplay = new JPanel();
      turnDisplay.setBorder(new LineBorder(Color.BLACK));
      turnDisplay.setLayout(new FlowLayout());
      active = new JLabel(imagePlayer1);
      phase = new JLabel("EINSCHIEBEN");
      turnDisplay.add(active);
      turnDisplay.add(phase);
      return turnDisplay;
   }

   /**
    * Gibt das Bild für den Pfeil am gegebenen Index zurück.
    *
    * @param pIndex
    *           Der Index des gewünschten Pfeilbildes.
    * @return Das Pfeilbild am gegebenen Index.
    * @throws ArrayIndexOutOfBoundsException
    *            Falls der gegebene Index kleiner als 0 oder größer oder gleich
    *            {@link Direction#COUNT} ist.
    */
    BufferedImage getArrowImage(final int pIndex) {
      return arrowImages[pIndex];
   }

   /**
    * Gibt das Plättchenbild des gegebenen Typs mit der gegebenen Orientierung zurück.
    *
    * @param pType
    *           Der Typ des Plättchens.
    * @param pOrientation
    *           Die Orientierung des Plättchens.
    * @return Das Plättchenbild des gegebenen Typs mit der gegebenen Orientierung.
    * @throws ArrayIndexOutOfBoundsException
    *            Falls der Typ des Plättchens kleiner als 0 oder größer oder gleich
    *            {@link PI2Laby#NO_OF_TILETYPES} ist oder die Orientierung
    *            kleiner als 0 oder größer oder gleich {@link Direction#COUNT} ist.
    */
    BufferedImage getTileImage(final int pType, final int pOrientation) {
      return tileImages[pType][pOrientation];
   }

   /**
    * Gibt das Plättchenbild zum gegebenen Plättchen zurück.
    *
    * @param pTile
    *           Das Plättchen, dessen Bild gesucht ist.
    * @return Das Plättchenbild zum gegebenen Plättchen.
    */
    BufferedImage getTileImage(final Tile pTile) {
      int type = 2;
      if (pTile instanceof Junction) {
         type = 0;
      } else if (pTile instanceof Curve) {
         type = 1;
      }
      return tileImages[type][pTile.getOrientation().ordinal()];
   }

   /**
    * Gibt das Bild für die SpielerIn am gegebenen Index zurück.
    *
    * @param pIndex
    *           Der Index des gewünschten SpielerInnenbildes.
    * @return Das SpielerInnen-Bild am gegebenen Index.
    * @throws ArrayIndexOutOfBoundsException
    *            Falls der gegebene Index kleiner als 0 oder größer als 1 ist.
    */
    BufferedImage getPieceImage(final int pIndex) {
      return pieceImages[pIndex];
   }

   /**
    * Gibt das Bild für den Rotationspfeil im Uhrzeigersinn zurück.
    *
    * @return Das Bild für den Rotationspfeil im Uhrzeigersinn.
    */
    BufferedImage getTurnClockImage() {
      return turnClockImage;
   }

   /**
    * Gibt das Bild für den Rotationspfeil im Gegenuhrzeigersinn zurück.
    *
    * @return Das Bild für den Rotationspfeil im Gegenuhrzeigersinn.
    */
    BufferedImage getTurnCounterClockImage() {
      return turnCounterClockImage;
   }

   /**
    * Aktualisiert die Anzeigen für die jeweils nächste Phase.
    */
    void displayNextPhase() {
      updatePhaseLabel();

      if (laby.getPhase() == Phase.MOVE) {
         disableRotation();
         mainFrame.repaint();
      } else {
         updateActivePlayerDisplay();
         enableRotation();
         mainFrame.repaint();
         final int gewinnerNo = laby.getWinner();
         if (gewinnerNo > -1) {
            displayWinner(gewinnerNo);
         }
      }
   }

   /**
    * Deaktiviert die beiden Schaltflächen für die Rotation des SchiebePlättchens.
    */
   private void disableRotation() {
      clockButton.setEnabled(false);
      counterButton.setEnabled(false);
   }

   /**
    * Aktiviert die beiden Schaltflächen für die Rotation des SchiebePlättchens.
    */
   private void enableRotation() {
      clockButton.setEnabled(true);
      counterButton.setEnabled(true);
   }

   /**
    * Aktualisiert den Text des Labels für die aktuelle Phase, in dem beim Modell des
    * Spiels die aktuelle Phase abgefragt wird.
    */
   private void updatePhaseLabel() {
      if (laby.getPhase() == Phase.INSERTION) {
         phase.setText("EINSCHIEBEN");
      } else {
         phase.setText("ZIEHEN");
      }
   }

   /**
    * Aktualisiert die Anzeige der aktuell am Zug befindlichen Spielfigur, in dem beim
    * Modell des Spiels die aktuelle SpielerIn abgefragt wird.
    */
   private void updateActivePlayerDisplay() {
      if (laby.getActivePlayerNo() == 0) {
         active.setIcon(imagePlayer1);
      } else if (laby.getActivePlayerNo() == 1) {
         active.setIcon(imagePlayer2);
      } else {
         active.setIcon(null);
      }
   }

   /**
    * Schließt das Fenster und damit die gesamte Anwendung.
    */
   private void close() {
      mainFrame.setVisible(false);
      mainFrame.dispose();
   }

   /**
    * Zeigt ein modales Benachrichtigungsfenster an, in dem die Spielfigur der
    * GewinnerIn des Spiels angezeigt wird. Das Fenster kann durch Anklicken der
    * Schaltfläche geschlossen werden woraufhin sich die Anwendung beendet.
    *
    * @param pPlayerNo
    *           Die Nummer der siegreichen SpielerIn (0 oder 1).
    * @throws ArrayIndexOutOfBoundsException
    *            Falls die gegebene SpielerInnennummer einen Wert kleiner als 0 oder
    *            größer als 1 hat.
    */
   private void displayWinner(final int pPlayerNo) {
      JOptionPane.showMessageDialog(mainFrame, new ImageIcon(pieceImages[pPlayerNo]),
            "GewinnerIn", JOptionPane.PLAIN_MESSAGE);
      close();
   }

   /**
    * Lädt die Bilder aus dem Unterverzeichnis "images" des aktuellen
    * Arbeitsverzeichnisses. Erwartet die Existenz der folgenden Bilddateien:
    *
    * <pre>
    * "pfeil1.png" bis "pfeil4.png"
    * "TPlaettchen1.png" bis "TPlaettchen4.png"
    * "Kurve1.png" bis "Kurve4.png"
    * "Gerade1.png" bis "Gerade4.png"
    * "shark.png" und "tux.png"
    * "turn_clock.png"
    * "turn_counterclock.png"
    * </pre>
    *
    * @throws IllegalStateException
    *            Falls die erwarteten Dateien nicht existieren oder keine Bilddateien
    *            sind.
    */
   private void loadImages() {
      try {
         for (int arrow = 0; arrow < Direction.COUNT; arrow++) {
            arrowImages[arrow] = createImage("arrow" + arrow + ".png");
         }
         for (int type = 0; type < PI2Laby.NO_OF_TILETYPES; type++) {
            for (int direction = 0; direction < Direction.COUNT; direction++) {
               tileImages[type][direction] =
                     createImage(TILE_TYPES[type] + direction + ".png");
            }
         }
         pieceImages[0] = createImage("shark.png");
         pieceImages[1] = createImage("tux.png");

         turnClockImage = createImage("turn_clock.png");
         turnCounterClockImage = createImage("turn_counterclock.png");

         imagePlayer1 = new ImageIcon(pieceImages[0]);
         imagePlayer2 = new ImageIcon(pieceImages[1]);

      } catch (final IOException e) {
         JOptionPane
               .showMessageDialog(mainFrame,
                     "Es ist ein Problem beim Laden einer Bilddatei aufgetreten: "
                           + e.getMessage(),
                     "Kritischer Fehler", JOptionPane.ERROR_MESSAGE);
         close();
         throw new IllegalStateException("Kritischer Fehler beim Laden der Bilddateien",
               e);
      }
   }

   /**
    * Erzeugt aus der Bilddatei deren Pfad durch den Parameter gegeben ist ein
    * darstellbares Bild-Objekt und gibt dieses zurück.
    *
    * @param pFileName
    *           Der Name der Bilddatei.
    * @return Ein darstellbares Bild-Objekt.
    * @throws IOException
    *            Falls es unter dem gegebenen Dateinamen keine Datei gibt oder die Datei
    *            keine Bilddatei ist.
    */
   private BufferedImage createImage(final String pFileName) throws IOException {
      final Path path = Paths.get(IMAGE_PATH, pFileName);
      return ImageIO.read(new File(path.toString()));
   }

   /**
    * Konfiguriert das Hauptfenster.
    */
   private void configureMainFrame() {
      mainFrame.setResizable(false);
      mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(final WindowEvent e) {
            final int option = JOptionPane.showConfirmDialog(mainFrame,
                  "Wirklich beenden?",
                  "Spiel beenden", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
               close();
            }

         }

      });
      mainFrame.getContentPane().setLayout(new FlowLayout());
      mainFrame.getContentPane().add(new BoardPanel(laby, this));

   }

}
