package sk.tuke.ursus.adapters;

import java.util.List;

import sk.tuke.ursus.R;
import sk.tuke.ursus.ViewHolder;
import sk.tuke.ursus.entities.Room;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapt�r miestnost�
 * @author Vlastimil Bre�ka
 *
 */
public class RoomAdapter extends ArrayAdapter<Room> {

	/**
	 * Zoznam miestnost�
	 */
	private List<Room> rooms;

	
	/**
	 * Kon�truktor
	 * @param context Kontext
	 * @param textViewResourceId ID .xml resource
	 * @param rooms Zoznam miestnost�
	 */
	public RoomAdapter(Context context, int textViewResourceId, List<Room> rooms) {
		super(context, textViewResourceId, rooms);
		this.rooms = rooms;
	}

	/**
	 * Met�da getView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.room_selection_item, parent, false);

			holder = new ViewHolder();
			holder.textView = (TextView) convertView.findViewById(R.id.room_name_textview);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Room room = rooms.get(position);
		holder.textView.setText(room.getName());

		return convertView;
	}

}
