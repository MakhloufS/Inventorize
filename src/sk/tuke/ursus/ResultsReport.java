package sk.tuke.ursus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.tuke.ursus.entities.Item;
import sk.tuke.ursus.entities.Room;

/**
 * V�sledn� spr�va v�sledkov invent�ry
 * @author Vlastimil Bre�ka
 *
 */
public class ResultsReport {

	/**
	 * Zoznam pr�jmate�ov notifik�cie
	 */
	private ArrayList<String> recipients;
	
	/**
	 * Obsah miestnosti
	 */
	private List<Item> contentList;
	
	/**
	 * N�zov s�boru
	 */
	private String fileName;
	
	/**
	 * Aktu�lny datum
	 */
	private String currentDate;
	
	/**
	 * Aktu�lny d�tum, ktor� m��e by� vo validnej URL
	 */
	private String printableDate;
	
	/**
	 * Predmet
	 */
	private String subject;
	
	/**
	 * Samotn� spr�va
	 */
	private String report;
	
	/**
	 * Obsah spr�vy e-mailovej notifik�cie
	 */
	private String emailMessage;
	
	/**
	 * N�zov miestnosti
	 */
	private String roomName;
	
	/**
	 * Po�et v�etk�ch polo�iek
	 */
	private int total;
	
	/**
	 * Po�et ch�baj�cich polo�iek
	 */
	private int missing;

	
	/**
	 * Kon�truktor
	 * @param currentRoom Aktu�lna miestnos�
	 * @param recipients Zoznam pr�jmate�ov e-mailovej notifik�cie
	 */
	public ResultsReport(Room currentRoom, ArrayList<String> recipients) {
		this.subject = "Results of inventory lookup";
		this.contentList = currentRoom.getContentList();
		this.recipients = recipients;
		this.roomName = currentRoom.getName();
		this.total = currentRoom.getContentList().size();
		this.missing = currentRoom.getMissingCount();
		
		initCurrentDate();
		composeFileName();
		composeHtmlReport();
	}
	
	/**
	 * Inicializuje aktu�lny d�tum
	 */
	private void initCurrentDate() {
		currentDate = new SimpleDateFormat("dd.MM.yyyy - HH:mm").format(new Date());
		printableDate = new SimpleDateFormat("dd-MM-yyyy-HH.mm").format(new Date());
	}

	/**
	 * Zostroj� n�zov s�boru
	 */
	private void composeFileName() {
		this.fileName = roomName.toLowerCase().replace(" ", "_") + "_-_" + printableDate;
	}

	/**
	 * Zostroj� e-mailov� notifik�ciu
	 * @param response Odpove� servra, URL na ulo�en� spr�vu vo form�te HTML
	 */
	public void composeEmailNotification(String response) {
		StringBuilder sb = new StringBuilder();

		sb.append("Dear Sir,\n\ninventory lookup in room '");
		sb.append(roomName);
		sb.append("' was completed on ");
		sb.append(currentDate);
		sb.append(". View results here:\n\n");
		sb.append(response);
		sb.append("\n\nThank you");

		emailMessage = sb.toString();
	}

	/**
	 * Zostroj� spr�vu vo form�te HTML
	 */
	private void composeHtmlReport() {

		StringBuilder sb = new StringBuilder();
		StringBuilder header = new StringBuilder();
		StringBuilder main = new StringBuilder();
		StringBuilder all = new StringBuilder();
		StringBuilder miss = new StringBuilder();
		StringBuilder ins = new StringBuilder();
		StringBuilder footer = new StringBuilder();

		header.append("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"/mystyle.css\"/></head><body>");

		main.append("<div><table><tr class=\"header\"><td rowspan=\"2\"><img src=\"/inv_icon_small.png\"/></td><td>Miestnost</td><td>Vykonane</td><td>Pocet chybajucich</td><td>Zobraz</td></tr><tr><td>");
		main.append(roomName);
		main.append("</td><td>");
		main.append(currentDate);
		main.append("</td><td>");
		main.append(missing);
		main.append(" / ");
		main.append(total);
		main.append("</td><td class=\"menu\"><a href=\"all.html\" target=\"content\">Vsetky</a><br><a href=\"missing.html\" target=\"content\">Chybajuce</a><br><a href=\"instock.html\" target=\"content\">Na sklade</a><br></td></tr></table>");
		main.append("<iframe name=\"content\" src=\"all.html\"><p>Your browser does not support iframes.</p></iframe></div></body></html>");

		all.append("<table class=\"header\"><td>EVID.C.</td><td>Stare.C.</td><td>Opis</td><td>Kusov</td><td>Miestnost</td><td>Na sklade</td></tr>");
		miss.append(all);
		ins.append(all);

		for (Item i : contentList) {
			if (!(i.isInStock())) {
				StringBuilder tmp = new StringBuilder();
				tmp.append("<tr class=\"notinstock\"><td>");
				tmp.append(i.getID());
				tmp.append("</td><td>");
				tmp.append(i.getOldID());
				tmp.append("</td><td>");
				tmp.append(i.getDesc());
				tmp.append("</td><td>");
				tmp.append(i.getQuantity());
				tmp.append("</td><td>");
				tmp.append(i.getRoom());
				tmp.append("</td><td>");
				tmp.append("Nie");
				tmp.append("</td></tr>");

				miss.append(tmp);
				all.append(tmp);

			} else {
				StringBuilder tmp = new StringBuilder();
				tmp.append("<tr class=\"instock\"><td>");
				tmp.append(i.getID());
				tmp.append("</td><td>");
				tmp.append(i.getOldID());
				tmp.append("</td><td>");
				tmp.append(i.getDesc());
				tmp.append("</td><td>");
				tmp.append(i.getQuantity());
				tmp.append("</td><td>");
				tmp.append(i.getRoom());
				tmp.append("</td><td>");
				tmp.append("Ano");
				tmp.append("</td></tr>");

				ins.append(tmp);
				all.append(tmp);
			}
		}

		footer.append("</table></body></html>");

		sb.append("filename=");
		sb.append(fileName);

		sb.append("&main=");
		sb.append(header);
		sb.append(main);

		sb.append("&all=");
		sb.append(header);
		sb.append(all);
		sb.append(footer);

		sb.append("&miss=");
		sb.append(header);
		sb.append(miss);
		sb.append(footer);

		sb.append("&ins=");
		sb.append(header);
		sb.append(ins);
		sb.append(footer);

		this.report = sb.toString();

	}
	
	/**
	 * Vr�ti e-mailov� notifik�ciu
	 * @return E-mailov� notifik�cia
	 */
	public String getEmailMessage() {
		return emailMessage;
	}

	/**
	 * Vr�ti spr�vu
	 * @return Spr�va
	 */
	public String getReport() {
		return report;
	}

	/**
	 * Vr�ti n�zov s�boru
	 * @return N�zov s�boru
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Vr�ti e-mailov� adresy
	 * @return E-mailov� adresy
	 */
	public String[] getAddress() {
		return recipients.toArray(new String[recipients.size()]);
	}

	/**
	 * Vr�ti predmet spr�vy
	 * @return Predmet spr�vy
	 */
	public String getSubject() {
		return subject;

	}
	
	/**
	 * Vr�ti d�tum, ktor� m��e by� vo validnej URL
	 * @return D�tum
	 */
	public String getPrintableDate() {
		return printableDate;
	}

	/**
	 * Vr�ti aktu�lny d�tum
	 * @return Aktu�lny D�tum
	 */
	public String getCurrentDate() {
		return currentDate;
	}

}
