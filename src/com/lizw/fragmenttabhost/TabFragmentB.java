package com.lizw.fragmenttabhost;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class TabFragmentB extends Fragment {
	// 缓存Fragment view
	private View rootView;
	// IActivityCallback的实例，用来使MainActivity做回调的
	private IActivityCallback mainActivityCallback;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e("==Fragmeng===", "FragmentB____onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("==Fragmeng===", "FragmentB____onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("==Fragmeng===", "FragmentB____onCreateView");
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.tab_b, container, false);
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
		mainActivityCallback = (IActivityCallback) getActivity();
		mainActivityCallback.control("TabFragmentB");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e("==Fragmeng===", "FragmentB____onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e("==Fragmeng===", "FragmentB____onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e("==Fragmeng===", "FragmentB____onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.e("==Fragmeng===", "FragmentB____onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("==Fragmeng===", "FragmentB____onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("==Fragmeng===", "FragmentB____onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.e("==Fragmeng===", "FragmentB____onDetach");
	}

}
