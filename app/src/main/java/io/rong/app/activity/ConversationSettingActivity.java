package io.rong.app.activity;

import android.os.Bundle;
import android.view.MenuItem;

import io.rong.app.R;
import me.add1.exception.BaseException;
import me.add1.network.AbstractHttpRequest;

/**
 * Created by Bob on 2015/3/27.
 */
public class ConversationSettingActivity extends BaseActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.de_ac_setting);
        getSupportActionBar().setTitle(R.string.de_actionbar_set_conversation);

    }

}
