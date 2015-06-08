package io.rong.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import io.rong.app.R;
import io.rong.app.activity.FriendListActivity;
import io.rong.app.activity.MainActivity;
import io.rong.app.activity.UpdateDiscussionActivity;
import io.rong.app.utils.Constants;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.DispatchResultFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Discussion;

/**
 * Created by Bob on 2015/3/27.
 */
public class SettingFragment extends DispatchResultFragment implements View.OnClickListener {
    private static final String TAG = SettingFragment.class.getSimpleName();
    private String targetId;
    private String targetIds;
    private Conversation.ConversationType mConversationType;
    private Button mDeleteBtn;
    private RelativeLayout mChatRoomRel;
    private TextView mChatRoomName;
    private android.support.v4.app.Fragment mAddNumberFragment;
    private android.support.v4.app.Fragment mToTopFragment;
    private String mDiscussionName;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.de_ac_friend_setting, container, false);
        mDeleteBtn = (Button) view.findViewById(R.id.de_fr_delete);
        mChatRoomRel = (RelativeLayout) view.findViewById(R.id.de_set_chatroom_name);
        mChatRoomName = (TextView) view.findViewById(R.id.de_chatroom_name);
        mAddNumberFragment = getChildFragmentManager().findFragmentById(R.id.de_fr_add_friend);
        mToTopFragment = getChildFragmentManager().findFragmentById(R.id.de_fr_to_top);

        init();
        return view;
    }

    private void init() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Intent intent = getActivity().getIntent();
        mDeleteBtn.setOnClickListener(this);
        mChatRoomRel.setOnClickListener(this);


        if (intent.getData() != null) {
            targetId = intent.getData().getQueryParameter("targetId");
            targetIds = intent.getData().getQueryParameter("targetIds");

            if (targetId != null) {
                mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
                if (mConversationType.equals(Conversation.ConversationType.DISCUSSION)) {
                    mDeleteBtn.setVisibility(View.VISIBLE);
                    mChatRoomRel.setVisibility(View.VISIBLE);
                    RongIM.getInstance().getRongIMClient().getDiscussion(targetId, new RongIMClient.ResultCallback<Discussion>() {
                        @Override
                        public void onSuccess(Discussion discussion) {
                            mDiscussionName = discussion.getName();

                            mChatRoomName.setText(mDiscussionName);
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });

                } else if (mConversationType.equals(Conversation.ConversationType.PRIVATE)) {

                } else if (mConversationType.equals(Conversation.ConversationType.CHATROOM)) {
                    fragmentTransaction.hide(mAddNumberFragment);
                    fragmentTransaction.hide(mToTopFragment);
                    fragmentTransaction.commit();

                } else if (mConversationType.equals(Conversation.ConversationType.GROUP)) {
                    fragmentTransaction.hide(mAddNumberFragment);
                    fragmentTransaction.commit();
                } else if (mConversationType.equals(Conversation.ConversationType.CUSTOMER_SERVICE)) {
                    fragmentTransaction.hide(mAddNumberFragment);
                    fragmentTransaction.hide(mToTopFragment);
                    fragmentTransaction.commit();
                }

            } else if (targetIds != null) {
                mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
                Log.e(TAG, "----  targetId----:" + targetId + ",targetIds----" + targetIds + ",mConversationType--" + mConversationType);

            }

            RongContext.getInstance().setOnMemberSelectListener(new RongIM.OnSelectMemberListener() {
                @Override
                public void startSelectMember(Context context, Conversation.ConversationType conversationType, String s) {
                    if (targetId != null)
                        mConversationType = Conversation.ConversationType.valueOf(getActivity().getIntent().getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

                    startActivity(new Intent(getActivity(), FriendListActivity.class));
                }
            });
        }
    }


    @Override
    protected void initFragment(Uri uri) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.de_fr_delete:

                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().getRongIMClient().quitDiscussion(targetId, new RongIMClient.OperationCallback() {
                        @Override
                        public void onSuccess() {
                            RongIM.getInstance().getRongIMClient().removeConversation(Conversation.ConversationType.DISCUSSION, targetId, new RongIMClient.ResultCallback<Boolean>() {
                                @Override
                                public void onSuccess(Boolean aBoolean) {

                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                    getActivity().finish();
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {

                                }
                            });
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }
                break;
            case R.id.de_set_chatroom_name:
                Intent intent = new Intent(getActivity(), UpdateDiscussionActivity.class);
                intent.putExtra("DEMO_DISCUSSIONIDS", targetId);
                intent.putExtra("DEMO_DISCUSSIONNAME", mDiscussionName.toString());
                startActivityForResult(intent, 21);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Constants.FIX_DISCUSSION_NAME:
                if (data != null) {
                    mChatRoomName.setText(data.getStringExtra("UPDATA_DISCUSSION_RESULT"));
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
}
