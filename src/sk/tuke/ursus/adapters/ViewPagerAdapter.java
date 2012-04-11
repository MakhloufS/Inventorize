package sk.tuke.ursus.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Adapt�r ViewPageru
 * @author Vlastimil Bre�ka
 *
 */
public class ViewPagerAdapter extends PagerAdapter {
	
	/**
	 * Zoznam polo�iek
	 */
	private ArrayList<LinearLayout> viewsList;

	
	/**
	 * Kon�truktor
	 * @param context Kontext
	 * @param viewsList ID .xml resource
	 */
	public ViewPagerAdapter(Context context, ArrayList<LinearLayout> viewsList) {
		this.viewsList = viewsList;

	}

	/**
	 * Met�da destroyItem
	 */
	@Override
	public void destroyItem(View view, int arg1, Object object) {
		((ViewPager) view).removeView((LinearLayout) object);
	}

	/**
	 * Met�da finishUpdate
	 */
	@Override
	public void finishUpdate(View arg0) {

	}

	/**
	 * Vr�ti po�et prvkov
	 */
	@Override
	public int getCount() {
		return viewsList.size();
	}

	/**
	 * Met�da instatiateItem
	 */
	@Override
	public Object instantiateItem(View view, int position) {
		View myView = viewsList.get(position);
		((ViewPager) view).addView(myView);
		return myView;
	}

	/**
	 * Met�da isViewFromObject
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	/**
	 * Met�da restoreState
	 */
	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	/**
	 * Met�da saveState
	 */
	@Override
	public Parcelable saveState() {
		return null;
	}

	/**
	 * Met�da startUpdate
	 */
	@Override
	public void startUpdate(View arg0) {

	}
}
