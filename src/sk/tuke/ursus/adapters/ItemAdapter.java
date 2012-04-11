package sk.tuke.ursus.adapters;

import java.util.List;

import sk.tuke.ursus.R;
import sk.tuke.ursus.ViewHolder;
import sk.tuke.ursus.entities.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

/**
 * Adapt�r polo�iek
 * @author Vlastimil Bre�ka
 *
 */
public class ItemAdapter extends ArrayAdapter<Item> implements Filterable {
	
	/**
	 * Biela farba
	 */
	private static final int WHITE = 0xFFFFFFFF;
	
	/**
	 * Tmavomodr� farba
	 */
	private static final int DARK_BLUE = 0xFF2B5BE5;
	
	/**
	 * Svetlomodr� farba
	 */
	private static final int LIGHT_BLUE = 0xFFDEDEEB;
	
	/**
	 * Siv� farba
	 */
	private static final int GRAY = 0xFF424242;
	
	
	/**
	 * Zoznam polo�iek
	 */
	private List<Item> items;
	
	/**
	 * M�d filtrovania, inicializovan� na - V�etky vidite�n�
	 */
	private int mode = 0;

	
	/**
	 * Kon�truktor
	 * @param context Kontext
	 * @param textViewResourceId ID pre .xml resource
	 * @param items Zoznam polo�iek
	 */
	public ItemAdapter(Context context, int textViewResourceId, List<Item> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}

	/**
	 * Met�da getView, je st�le volan� na danom listView.
	 * Ak je polo�ka na sklade, nastav� pozadie na tmavomodr� a farbu p�sma na bielu.
	 * Ak polo�ka nie je na sklade, nastav� sa pozadie na svetlomodr� a farbu p�sma na siv�
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.room_inventory_item, parent, false);

			holder = new ViewHolder();
			holder.textView = (TextView) convertView.findViewById(R.id.item_name_textview);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Item item = items.get(position);

		holder.textView.setText(item.getDesc().toLowerCase());

		if (item.isInStock()) {
			convertView.setBackgroundColor(DARK_BLUE);
			holder.textView.setTextColor(WHITE);
		} else if (!(item.isInStock())) {
			convertView.setBackgroundColor(LIGHT_BLUE);
			holder.textView.setTextColor(GRAY);
		}

		handleViewFilter(convertView, item);
		
		return convertView;
	}

	/**
	 * Reaguje na r�zne filtrovacie m�dy.
	 * M�dy s�:
	 * - 0 - V�etky vidite�n�.
	 * - 1 - Iba na sklade. 
	 * - 2 - Iba ch�baj�ce.
	 * @param view View
	 * @param item Polo�ka
	 */
	private void handleViewFilter(View view, Item item) {
		switch (mode) {
		case 0:
			if (view.getVisibility() != View.VISIBLE) {
				view.setVisibility(View.VISIBLE);
			}
			break;
		case 1:
			if (item.isInStock()) {
				view.setVisibility(View.INVISIBLE);
			} else {
				view.setVisibility(View.VISIBLE);
			}
			break;
		case 2:
			if (!(item.isInStock())) {
				view.setVisibility(View.INVISIBLE);
			} else {
				view.setVisibility(View.VISIBLE);
			}
			break;
		}
	}

	/**
	 * Nastav� filtrovac� m�d
	 * @param mode Filtrovac� m�d
	 */
	public void setFilteringMode(int mode) {
		this.mode = mode;
	}

}
