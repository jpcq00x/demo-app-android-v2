package io.rong.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import io.rong.app.R;
import io.rong.app.fragment.DeAdressListFragment;

/**
 * Created by Administrator on 2015/3/26.
 */
public class DeAdressListActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.de_ac_address_fragment);
        getSupportActionBar().setTitle(R.string.add_contacts);
    }

}
