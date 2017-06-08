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
 * Die Klasse Junction repraesentiert ein Plaetchen mit drei Endungen auf dem Spielfeld
 * Das Plaetchen kann in dieser Klasse erstellt und auf einem Ausgang überprueft werden
 */
public final class Junction extends Tile {

   /**
    * Erzeugt ein neues TPlättchen mit der gegebenen Orientierung.
    *
    * @param pOrientation
    *           Die Orientierung des neuen TPlättchens.
    */
   public Junction(final Direction pOrientation) {
      super(pOrientation);
   }

   /**
    * Die Methode ueberprueft, ob es einen Ausgang fuer das Plaetchen in der gegebenen Richtung und Orinetierung gibt.
    * @param pDirection, Die Richtung, für die man den Ausgang ueberpuefen soll
    * @param true wenn es einen Ausgang gibt, ansonsten false
    * @ throws unsupportedOperationException, wenn die Orientierung oder die Richtung null ist
    */
   @Override
   public boolean hasExit(final Direction pDirection) {
      //throw new UnsupportedOperationException();

     if(getOrientation().getOrdinal()==pDirection.getOrdinal()){	
   	   return false;
   	 }
   	 else {
   		return true;
   }
   }

}
