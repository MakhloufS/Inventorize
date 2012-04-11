package sk.tuke.ursus.entities;

import java.io.Serializable;

/**
 * Polo�ka skladu
 * @author Vlastimil Bre�ka
 *
 */
public class Item implements Serializable {

	/**
	 * ID pre serializ�ciu
	 */
	private static final long serialVersionUID = 2773543494487194304L;
	
	/**
	 * �i je polo�ka na sklade
	 */
	private boolean inStock = false;
	
	/**
	 * ID polo�ky
	 */
	private String ID;
	
	/**
	 * Star� ID polo�ky
	 */
	private String oldID;
	
	/**
	 * Opis polo�ky
	 */
	private String desc;
	
	/**
	 * Kvantita
	 */
	private String quantity;
	
	/**
	 * Miestnos� v ktorej sa polo�ka nach�dza
	 */
	private String room;

	/**
	 * Kon�truktor
	 * @param ID ID polozky 
	 * @param oldID Stare ID polozky 
	 * @param desc Opis polozky
	 * @param quantity Kvantita polozky
	 * @param room Miestnost v ktorej sa nachadza polozka
	 */
	public Item(String ID, String oldID, String desc, String quantity, String room) {
		this.ID = ID;
		this.oldID = oldID;
		this.desc = desc;
		this.quantity = quantity;
		this.room = room;
	}

	/**
	 * �i je na sklade
	 * @return True ak polo�ka je, False ak nie je
	 */
	public boolean isInStock() {
		return inStock;
	}

	/**
	 * Ozna�� polo�ku ako n�jdenu
	 */
	public void putInStock() {
		if (inStock == false) {			
			inStock = true;
		}
	}

	/**
	 * Ozna�� polo�ku ako nen�jdenu
	 */
	public void removeFromStock() {
		if (inStock == true)
			inStock = false;
	}
	
	/**
	 * Vr�ti ID polo�ky
	 * @return ID polo�ky
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Vr�ti stare ID polo�ky
	 * @return Star� ID polo�ky
	 */
	public String getOldID() {
		return oldID;
	}

	/**
	 * Vr�ti popis polo�ky 
	 * @return Popis polo�ky 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Vr�ti kvantitu polo�ky
	 * @return Kvantita polo�ky
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Vr�ti miestnos�
	 * @return Miestnos�
	 */
	public String getRoom() {
		return room;
	}
}
