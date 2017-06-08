package pi.uebung01.spec;

/**
 * Diese klasse implementiert die Klasse FiFoInterface
 * @author Mahmoud F.
 */
public class FiFo<E> implements FiFoInterface<E> {

	private E[] sammlung;
	private int gepuscht;

	/**
	 * erzeugt eine neue Sammlung
	 * 
	 * @param pAnzahl die Anzahl der Elemente der Sammlung
	 */
	@SuppressWarnings("unchecked")
	public FiFo(int pAnzahl) {
		if (pAnzahl > 0) {
			this.sammlung = (E[]) new Object[pAnzahl];
		}

	}

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
	@Override
	public void push(E pElement){
		if (pElement == null) {
			throw new IllegalArgumentException();
		}

		if (isFull()) {
			throw new PushPopyException("die Sammlung ist full");
		}

		else if (!isFull() && pElement != null) {
					sammlung[gepuscht] = (E) pElement;
					gepuscht ++;
		   }
		}

	/**
	    * Gibt an, ob diese Sammlung leer ist.
	    *
	    * @return {@code true} falls diese Sammlung leer ist, ansonsten {@code false}.
	    */
	@Override
	public boolean isEmpty() {
		boolean empty = true;
		for (int i = 0; i < sammlung.length; i++) {
			if (sammlung[i] != null) {
				empty = false;
			}
		}
		return empty;
	}

	/**
	    * Gibt an, ob sich in dieser Sammlung bereits maximal viele Elemente befinden.
	    *
	    * @return {@code true} falls diese Sammlung maximal viele Elemente enthält,
	    *         ansonsten {@code false}.
	    */
	@Override
	public boolean isFull() {
		boolean full = true;
		for (int i = 0; i < sammlung.length; i++) {
			if (sammlung[i] == null) {
				full = false;
			}
		}
		return full;
	}

	/**
	    * {@inheritDoc} Es wird das älteste Element dieser Sammlung entfernt und
	    * zurückgegeben (falls die Sammlung nicht leer ist).
	    *
	    * @return Das älteste Element dieser Sammlung.
	    * @throws PushPopyException
        *         Falls es keine Elemente in dieser Sammlung gibt.
	    */
	@Override
	public E pop() {
		E deleted = null;

		if (isEmpty()) {
			throw new PushPopyException("die Sammlung ist leer!");
		}

		else {
			deleted = sammlung[0];
			sammlung[0] = null;
			for (int i = 0; i < sammlung.length; i++) {
				if(i < sammlung.length -1){
					sammlung[i] = sammlung[i+1];
					sammlung[i+1] = null;
					gepuscht--;
				}
				else{
					sammlung[i] = null;
					gepuscht--;
				}
			}
		}


		return deleted;
	}

}
