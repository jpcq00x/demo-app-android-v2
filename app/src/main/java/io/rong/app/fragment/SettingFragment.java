package io.rong.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

import io.rong.app.R;
import io.rong.app.activity.FriendListActivity;
import io.rong.app.activity.MainActivity;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.DispatchResultFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by Bob on 2015/3/27.
 */
public class SettingFragment extends DispatchResultFragment implements View.OnClickListener {
    private static final String TAG = SettingFragment.class.getSimpleName();
    private String targetId;
    private String targetIds;
    private Conversation.ConversationType mConversationType;
    private Button mDeleteBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.de_ac_friend_setting, container,false);
        mDeleteBtn= (Button) view.findViewById(R.id.de_fr_delete);
        init();
        return view;
    }

    private void init() {
        Intent intent = getActivity().getIntent();
        if (intent.getData() != null) {
            targetId = intent.getData().getQueryParameter("targetId");
            targetIds = intent.getData().getQueryParameter("targetIds");
            final String delimiter = intent.getData().getQueryParameter("delimiter");

            if (targetId != null) {
                mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
                if(mConversationType == Conversation.ConversationType.DISCUSSION){
                    mDeleteBtn.setVisibility(View.VISIBLE);
                    mDeleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(RongIM.getInstance()!=null){
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
                        }
                    });
                }else{
                    mDeleteBtn.setVisibility(View.GONE);
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
        switch (view.getId()){
            case R.id.de_fr_delete:
//                if(RongIM.getInstance()!=null){
//                    RongIM.getInstance().getRongClient().quitDiscussion(id, new RongIMClient.OperationCallback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError(RongIMClient.ErrorCode errorCode) {
//
//                        }
//                    });
//                }
                break;
        }
    }
}
