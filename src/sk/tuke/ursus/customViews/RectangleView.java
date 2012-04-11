package sk.tuke.ursus.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Vlastn� view, �tvorec, odr�ka pri tla�idle v resulte
 * @author Vlastimil Bre�ka
 *
 */
public class RectangleView extends View {

	/**
	 * Paint
	 */
	private Paint paint;
	
	/**
	 * Objekt obd�nika pre vykreslenie
	 */
	private Rect rect;
	
	/**
	 * Ve�kos� strany
	 */
	private int size;

	
	/**
	 * Kon�truktor, vypo��ta ve�kos� pre dan� rozl�enie
	 * @param context
	 * @param attrs
	 */
	public RectangleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int displayWidth = display.getWidth();
		
		size = (int)((40 / 480f) * displayWidth);
		
		init();
	}

	/**
	 * Met�da onDraw
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(rect, paint);

	}

	/**
	 * Inicializ�cia, nastav� farbu a vytvor� sa objekt obd�nika
	 */
	private void init() {
		paint = new Paint();
		paint.setColor(0xFF2B5BE5);
		rect = new Rect(0, 0, size, size);
	}

	/**
	 * Met�da onMeasure
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.setMeasuredDimension(size, size);
	}

}
