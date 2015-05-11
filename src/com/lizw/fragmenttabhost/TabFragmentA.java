package com.lizw.fragmenttabhost;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragmentA extends Fragment {
	// 缓存Fragment view
	private View rootView;
	// IActivityCallback的实例，用来使MainActivity做回调的
	private IActivityCallback mainActivityCallback;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e("==Fragmeng===", "FragmentA____onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("==Fragmeng===", "FragmentA____onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("==Fragmeng===", "FragmentA____onCreateView");
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.tab_a, container, false);
		}
		// 缓存的rootView需要判断是否已经被加过parent，
		// 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e("==Fragmeng===", "FragmentA____onActivityCreated");
		mainActivityCallback = (IActivityCallback) getActivity();
		mainActivityCallback.control("TabFragmentA");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e("==Fragmeng===", "FragmentA____onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e("==Fragmeng===", "FragmentA____onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e("==Fragmeng===", "FragmentA____onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.e("==Fragmeng===", "FragmentA____onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("==Fragmeng===", "FragmentA____onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("==Fragmeng===", "FragmentA____onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.e("==Fragmeng===", "FragmentA____onDetach");
	}
}
