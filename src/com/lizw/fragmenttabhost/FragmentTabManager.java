package com.lizw.fragmenttabhost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.List;

public class FragmentTabManager implements RadioGroup.OnCheckedChangeListener {
	private List<Fragment> fragments; // 一个tab页面对应一个Fragment
	private RadioGroup rgs; // 用于切换tab
	private FragmentActivity fragmentActivity; // Fragment所属的Activity
	private int fragmentContentId; // Activity中所要被替换的区域的id

	private int currentTab; // 当前Tab页面索引

	private OnExtraCheckedChangedListener onExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能

	public FragmentTabManager(FragmentActivity fragmentActivity,List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
		this.fragments = fragments;
		this.rgs = rgs;
		this.fragmentActivity = fragmentActivity;
		this.fragmentContentId = fragmentContentId;

		// 默认显示第一页
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
		//替换一个已经存在的Fragment，在替换时候同时调用remove在该容器中已经添加的Fragment
		ft.replace(fragmentContentId, fragments.get(0));
//		//可以重复添加View到Fragment容器中
//		ft.add(fragmentContentId, fragments.get(0));
		ft.commit();

		rgs.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		for (int i = 0; i < rgs.getChildCount(); i++) {
			if (rgs.getChildAt(i).getId() == checkedId) {
				Fragment fragment = fragments.get(i);
				FragmentTransaction ft = obtainFragmentTransaction(i);

				getCurrentFragment().onPause(); // 暂停当前tab
				// getCurrentFragment().onStop(); // 暂停当前tab

				if (fragment.isAdded()) {
					// fragment.onStart(); // 启动目标tab的onStart()
					fragment.onResume(); // 启动目标tab的onResume()
				} else {
					ft.add(fragmentContentId, fragment);
				}
				showTab(i); // 显示目标tab
				ft.commit();

				// 如果设置了切换tab额外功能接口回调
				if (null != onExtraCheckedChangedListener) {
					onExtraCheckedChangedListener.onExtraCheckedChanged(radioGroup, checkedId, i);
				}

			}
		}

	}

	/**
	 * 切换tab
	 * 
	 * @param idx
	 */
	private void showTab(int idx) {
		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			FragmentTransaction ft = obtainFragmentTransaction(idx);

			if (idx == i) {
				ft.show(fragment);
			} else {
				ft.hide(fragment);
			}
			ft.commit();
		}
		currentTab = idx; // 更新目标tab为当前tab
	}

	/**
	 * 获取一个带动画的FragmentTransaction
	 * 
	 * @param index
	 * @return
	 */
	private FragmentTransaction obtainFragmentTransaction(int index) {
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
		// 设置切换动画
		if (index > currentTab) {
			ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
		} else {
			ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
		}
		return ft;
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public Fragment getCurrentFragment() {
		return fragments.get(currentTab);
	}
	

	public OnExtraCheckedChangedListener getOnExtraCheckedChangedListener() {
		return onExtraCheckedChangedListener;
	}

	public void setOnExtraCheckedChangedListener(OnExtraCheckedChangedListener onExtraCheckedChangedListener) {
		this.onExtraCheckedChangedListener = onExtraCheckedChangedListener;
	}

	/**
	 * 切换tab额外功能功能接口
	 */
	interface OnExtraCheckedChangedListener {
		public void onExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) ;
	}

}
