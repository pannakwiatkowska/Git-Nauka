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
 * TODO: javadoc
 */
public final class Straight extends Tile {

   /**
    * Erzeugt ein neues GeradenPlättchen mit der gegebenen Orientierung.
    *
    * @param pOrientation
    *           Die Orientierung des neuen GeradenPlättchens.
    */
	
	
	
   public Straight(final Direction pOrientation) {
      super(pOrientation);
   }

   @Override
   public boolean hasExit(final Direction pDirection) {
     // throw new UnsupportedOperationException(); // TODO: implementieren
	//   if(getOrientation()==NORTH || getOrientation()==SOUTH)
	//   if(pDirection==NORTH || pDirection==SOUTH){
	//	   return true;
	//   }
	//   else {
		//   return false;
	// if(getOrientation()==pDirection || ) {
	if((pDirection.getOrdinal()-getOrientation().getOrdinal())%2 ==0){	
	   return true;
	 }
	 else {
		 return false;
	 
	//  getOrdinal()   
	   }
   }

}
