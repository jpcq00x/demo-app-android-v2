package io.rong.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.rong.app.activity.PersonalDetailActivity;
import io.rong.app.model.Friend;
import io.rong.imkit.widget.AsyncImageView;
import me.add1.resource.Resource;

public class BlackMultiChoiceAdapter extends BlackListAdapter {

    private static final String TAG = BlackMultiChoiceAdapter.class.getSimpleName();
    private ArrayList<Friend> mFriends;


    public BlackMultiChoiceAdapter(Context context, List<Friend> friends) {
        super(context, friends);
        this.mFriends = (ArrayList<Friend>) friends;
    }

    @Override
    protected void bindView(View v, int partition, List<Friend> data, int position) {
        super.bindView(v, partition, data, position);

        ViewHolder holder = (ViewHolder) v.getTag();
        TextView name = holder.name;
        AsyncImageView photo = holder.photo;

        Friend friend = data.get(position);
        name.setText(friend.getNickname());

        Resource res = new Resource(friend.getPortrait());

        photo.setDefaultDrawable(mContext.getResources().getDrawable(io.rong.imkit.R.drawable.rc_default_portrait));
        photo.setResource(res);

        String userId = friend.getUserId();
        holder.userId = userId;

    }

    @Override
    protected void newSetTag(View view, ViewHolder holder, int position, List<Friend> data) {
        super.newSetTag(view, holder, position, data);
    }

    @Override
    public void onItemClick(String friendId) {

        Intent intent = new Intent(getContext(), PersonalDetailActivity.class);
        intent.putExtra("PERSONAL", friendId);
        getContext().startActivity(intent);

    }


}
