package io.rong.app.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import io.rong.app.R;


/**
 * Created by Bob on 2015/3/18.
 */
public class FriendListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.de_ui_list_test);
        getSupportActionBar().setTitle(R.string.de_actionbar_set_conversation);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.de_ic_logo);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.hide();

    }


}
