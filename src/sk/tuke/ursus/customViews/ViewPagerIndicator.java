package sk.tuke.ursus.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * ViewPagerIndicator, indikuje na ktorej strane sa ViewPager aktu�lne nach�dza
 * @author Vlastimil Brecka
 *
 */
public class ViewPagerIndicator extends View {
	
	/**
	 * Farba aktu�lnej str�nky
	 */
	private static final int SELECTED_COLOR = 0xFF424242;
	
	/**
	 * Farba neaktu�lnej str�nky
	 */
	private static final int DESELECTED_COLOR = 0xFFBDBDBD;

	/**
	 * Paint
	 */
	private Paint paint;
	
	/**
	 * Pole obd�nikov
	 */
	private Rect[] array;
	
	/**
	 * Index aktu�lnej str�nky
	 */
	private int currentPageIndex;

	/**
	 * ��rka obd�nika
	 */
	private int width;
	
	/**
	 * V��ka obd�nika
	 */
	private int height;
	
	/**
	 * Odstup medzi obd�nikmi
	 */
	private int margin;
	
	/**
	 * Po�et obd�nikov
	 */
	private int count;

	/**
	 * Kon�truktor, vypo��ta rozmery pre dan� rozl�enie
	 * @param context Kontext
	 * @param attrs Atrib�ty
	 */
	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int displayHeight = display.getHeight();
		int displayWidth = display.getWidth();
		
		width = (int)((30 / 480f) * displayWidth);
		margin = (int)((10 / 480f) * displayWidth);
		height = (int)((5 / 800f) * displayHeight);
		
		paint = new Paint();
	}

	/**
	 * Vytvoria sa obd�niky
	 */
	private void initialize() {
		array = new Rect[count];
		for (int i = 0; i < count; i++) {
			array[i] = new Rect(i * (margin + width), 0, i * (margin + width) + width, height);
		}
		currentPageIndex = 0;
	}
	
	/**
	 * Obnovenie
	 * @param index Index aktu�lnej str�nky
	 */
	private void update(int index) {
		this.currentPageIndex = index;
		this.invalidate();
	}

	/**
	 * Met�da onDraw
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (int i = 0; i < count; i++) {
			if (i == currentPageIndex) {
				paint.setColor(SELECTED_COLOR);
			} else {
				paint.setColor(DESELECTED_COLOR);
			}
			canvas.drawRect(array[i], paint);
		}
	}

	/**
	 * Met�da onMesaure
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.setMeasuredDimension((width + margin) * count, height);
	}

	/**
	 * Nastav� viewPager
	 * @param pager ViewPager, na ktor� chceme da� indik�tor
	 */
	public void setViewPager(ViewPager pager) {
		//this.pager = pager;
		count = pager.getAdapter().getCount();

		initialize();

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				update(index);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
