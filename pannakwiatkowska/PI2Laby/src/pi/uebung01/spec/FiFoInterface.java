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
 * Erweitert die Spezifikation von {@linkplain PushPopyInterface}.
 *
 * Diese Sammlung arbeitet nach dem FiFo-Prinzip, d.h. das älteste in der Sammlung
 * befindliche Element wird entfernt und zurückgegeben. Ein Element {@code e1} ist
 * hierbei älter als ein Element {@code e2}, falls {@code e1} zeitlich vor {@code e2} in
 * die Sammlung eingefügt wurde.
 *
 * @param <E>
 *           ein beliebiger Datentyp, der den Datentyp der Elemente dieser Sammlung
 *           festlegt
 *
 * @author K. Hölscher
 * @version 2017-04-06
 */
public interface FiFoInterface<E> extends PushPopyInterface<E> {

   /**
    * {@inheritDoc} Es wird das älteste Element dieser Sammlung entfernt und
    * zurückgegeben (falls die Sammlung nicht leer ist).
    *
    * @return Das älteste Element dieser Sammlung.
    */
   @Override
   E pop();

}
