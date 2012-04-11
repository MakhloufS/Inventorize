package sk.tuke.ursus.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Tla�idlo s vlastn�m fontom pisma
 * @author Vlastimil Bre�ka
 *
 */
public class MyButton extends Button {

	/**
	 * Kon�truktor
	 * @param context Kontext
	 * @param attrs Atrib�ty
	 */
	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/LABTOP__.ttf"));
	}

}
