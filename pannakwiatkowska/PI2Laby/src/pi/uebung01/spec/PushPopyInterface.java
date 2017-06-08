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

/**
 * Spezifiziert eine beschränkte Sammlung von beliebigen Objekten (außer {@code null}).
 * Die Anzahl der Elemente, die in dieser Sammlung enthalten sein können wird dem
 * Konstruktor der konkreten Sammlung übergeben. Ein Element kann mit der Methode push
 * hinzugefügt und mit der Methode pop entfernt werden. Darüber hinaus kann abgefragt
 * werden, ob die Sammlung bereits maximal gefüllt oder leer ist.
 *
 * @param <E>
 *           Ein beliebiger Datentyp, der den Datentyp der Elemente dieser Sammlung
 *           festlegt.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public interface PushPopyInterface<E> {

   /**
    * Fügt das übergebene Element zu dieser Sammlung hinzu, falls noch Platz ist.
    *
    * @param pElement
    *           Das einzufügende Element.
    * @throws PushPopyException
    *            Falls kein Platz mehr in dieser Sammlung vorhanden ist.
    * @throws IllegalArgumentException
    *            Falls das gegebene Element den Wert {@code null} hat.
    */
   void push(E pElement);

   /**
    * Entfernt ein Element dieser Sammlung, welches zuvor über
    * {@link PushPopyInterface#push} eingefügt wurde (und seit dem nicht entfernt wurde)
    * und gibt dieses Element zurück.
    *
    * @return Ein Element dieser Sammlung, das zuvor eingefügt und seit dem nicht
    *         entfernt wurde.
    * @throws PushPopyException
    *            Falls es keine Elemente in dieser Sammlung gibt.
    */
   E pop();

   /**
    * Gibt an, ob diese Sammlung leer ist.
    *
    * @return {@code true} falls diese Sammlung leer ist, ansonsten {@code false}.
    */
   boolean isEmpty();

   /**
    * Gibt an, ob sich in dieser Sammlung bereits maximal viele Elemente befinden.
    *
    * @return {@code true} falls diese Sammlung maximal viele Elemente enthält,
    *         ansonsten {@code false}.
    */
   boolean isFull();

}
