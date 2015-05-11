package com.lizw.fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements IActivityCallback{
    /**
     * Called when the activity is first created.
     */
    private RadioGroup rgs;
    public List<Fragment> fragments = new ArrayList<Fragment>();

//    public String hello = "hello ";
    private TextView titleView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        titleView = (TextView) findViewById(R.id.view_title);
        fragments.add(new TabFragmentA());
        fragments.add(new TabFragmentB());
        fragments.add(new TabFragmentC());
        fragments.add(new TabFragmentD());
        fragments.add(new TabFragmengE());


        rgs = (RadioGroup) findViewById(R.id.tabs_rg);
        FragmentTabManager tabManager = new FragmentTabManager(this, fragments, R.id.tab_content, rgs);
        
        tabManager.setOnExtraCheckedChangedListener(new FragmentTabManager.OnExtraCheckedChangedListener(){
            @Override
            public void onExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                Log.e("==Fragmeng===","Extra---- " + index + " checked!!! ");
            }
        });

    }

	@Override
	public void control(String title) {
		titleView.setText(title);		
	}


}

