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
 * Zeigt eine Ausnahmesituation beim Umgang mit einer Sammlung vom Typ
 * {@linkplain PushPopyInterface} an.
 *
 * @author K. Hölscher
 * @version 2017-04-06
 *
 */
public class PushPopyException extends RuntimeException {

   /**
    * Eindeutige ID für Serialisierung.
    */
   private static final long serialVersionUID = -8513890050069924187L;

   /**
    * Erzeugt eine neue Exception mit dem gegebenen Nachrichtentext.
    *
    * @param pMessage
    *           Die Nachricht zu dieser Exception.
    */
   public PushPopyException(final String pMessage) {
      super(pMessage);
   }

}
