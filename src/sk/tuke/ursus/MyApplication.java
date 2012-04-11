package sk.tuke.ursus;

import java.io.Serializable;
import java.util.ArrayList;

import sk.tuke.ursus.entities.Room;


import android.app.Application;

/**
 * Podtrieda Application, umo��uje dr�a� glob�lne premenn� v aplik�ci�
 * @author Vlastimil Bre�ka
 *
 */
public class MyApplication extends Application implements Serializable {
	
	/**
	 * ID pre serializ�ciu 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Aktu�lna miestnos�
	 */
	private Room currentRoom;
	
	/**
	 * Zoznam miestnost�
	 */
	private ArrayList<Room> roomsList;
	
	/**
	 * Zoznam e-mailov�ch adries pre e-mailov� notifik�ciu
	 */
	private ArrayList<String> emailAddresses;
	
	/**
	 * Cesta k .xml zdrojov�mu s�boru
	 */
	private String xmlURL;
	
	/**
	 * Cesta k .php exportovaciemu skriptu
	 */
	private String phpURL;
	
	/**
	 * �i je mo�n� za�a� invent�ru, 
	 * resp. �i s� v�etky cesty a aspo� jedna e-mailov� adresa, nastaven�
	 */
	private boolean isReadyToStart = false;
	
	
	/**
	 * N�zov s�boru na disku v ktorom s� ulo�en� nastavenia
	 */
	public static final String FILENAME = "settings.invsys";

	/**
	 * Vr�ti zoznam miestnost�
	 * @return Zoznam miestnost�
	 */
	public ArrayList<Room> getRoomsList() {
		return roomsList;
	}

	/**
	 * Nastavi zoznam miestnost�
	 * @param roomsList Zoznam miestnosti
	 */
	public void setRoomsList(ArrayList<Room> roomsList) {
		this.roomsList = roomsList;
	}

	/**
	 * Vr�ti aktu�lnu miestnost
	 * @return Aktu�lna miestnos� v ktorej prebieha invent�ra
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Nastav� aktu�lnu miestnos�
	 * @param room Aktu�lna miestnos�
	 */
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	/**
	 * Vr�ti e-mailov� adresy pre notifik�ciu
	 * @return E-mailov� adresy
	 */
	public ArrayList<String> getEmailAddresses() {
		return emailAddresses;
	}

	/**
	 * Nastav� e-mailov� adresy pre notifik�ciu
	 * @param emailAddresses Pre notifikaciu
	 */
	public void setEmailAddresses(ArrayList<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	/**
	 * Vr�ti cestu k .xml s�boru
	 * @return Cesta k .xml s�boru
	 */
	public String getXmlURL() {
		return xmlURL;
	}

	/**
	 * Vr�ti cestu k .php skriptu
	 * @return Cesta k .php skriptu
	 */
	public String getPhpURL() {
		return phpURL;
	}

	/**
	 * Nastav� cestu k .xml s�boru
	 * @param xmlURL Cesta k .xml s�boru
	 */
	public void setXmlURL(String xmlURL) {
		this.xmlURL = xmlURL;
	}

	/**
	 * Nastav� cestu k .php skriptu
	 * @param phpURL Cesta k .php skriptu
	 */
	public void setPhpURL(String phpURL) {
		this.phpURL = phpURL;
	}

	/**
	 * �i je mo�n� za�a� invent�ru. 
	 * Invent�ru je mo�n� za�a� ak je nastaven� .xml cesta, .php cesta a aspo� jedna e-mailov� adresa
	 * @return True ak je mo�n� za�a�, False ak nie
	 */
	public boolean isReadyToStart() {
		return isReadyToStart;
	}

	/**
	 * Nastav�, �i je mo�n� za�a� invent�ru
	 * @param isReady �i je mo�n� za�a�
	 */
	public void setReadyToStart(boolean isReady) {
		this.isReadyToStart = isReady;
	}
}
