package sk.tuke.ursus.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TextView s vlastn�m fontom p�sma
 * @author Vlastimil Bre�ka
 *
 */
public class MyTextView extends TextView {

	/**
	 * Kon�truktor
	 * @param context Kontext
	 * @param attrs Atrib�ty
	 */
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/LABTOP__.ttf"));
	}

}
