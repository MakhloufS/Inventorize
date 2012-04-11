package sk.tuke.ursus.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Miestnos� s polo�kami
 * @author Vlastimil Bre�ka
 *
 */
public class Room implements Serializable {

	/**
	 * ID pre serializ�ciu
	 */
	private static final long serialVersionUID = -1925576220775476933L;
	
	/**
	 * N�zov miestnosti
	 */
	private String name;
	
	/**
	 * Obsah miestnosti
	 */
	private List<Item> content;

	/**
	 * Kon�truktor
	 * @param name Meno miestnosti
	 */
	public Room(String name) {
		content = new ArrayList<Item>();
		this.name = name;
	}

	/**
	 * Prid� polo�ku do miestnosti
	 * @param item Polo�ka, ktor� chceme prida� do miestnosti
	 */
	public void addItem(Item item) {
		content.add(item);
	}
	
	/**
	 * Zresetuje miestnos�,
	 * to znamen� �e nastav� v�etky polo�ky ako nen�jden�
	 */
	public void reset() {
		for (Item i : content) {
			i.removeFromStock();
		}
	}

	
	/**
	 * Vr�ti meno miestnosti
	 * @return Meno miestnosti
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Vr�ti obsah miestnosti
	 * @return Obsah miestnosti
	 */
	public List<Item> getContentList() {
		return content;
	}

	/**
	 * Vr�ti po�et ch�baj�cich polo�iek
	 * @return Pocet ch�baj�cich polo�iek
	 */
	public int getMissingCount() {

		int count = 0;

		for (Item i : content) {
			if (!(i.isInStock())) {
				count++;
			}
		}
		return count;
	}
	

	/**
	 * Vr�ti po�et polo�iek na sklade
	 * @return Po�et polo�iek na sklade
	 */
	public int getInStockCount() {
		int count = 0;

		for (Item i : content) {
			if ((i.isInStock())) {
				count++;
			}
		}
		return count;
	}
}
