//package io.rong.app.database;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import de.greenrobot.dao.AbstractDaoMaster;
//import de.greenrobot.dao.AbstractDaoSession;
//import io.rong.database.ConversationMaster;
//
///**
// * Created by Bob on 15/5/21.
// * database
// */
//public class DateContext {
//
//    private static DateContext mDateContext;
//    private Context mContext;
//    private AbstractDaoMaster daoMaster;
////    private NoteDao noteDao;
//
//    private AbstractDaoSession daoSession;
//    private Cursor cursor;
//
//    private SQLiteDatabase db;
//
//    private DateContext() {
//
//    }
//
//    public static DateContext getInstance() {
//        if (mDateContext == null) {
//            mDateContext = new DateContext();
//        }
//        return mDateContext;
//    }
//
//
//    private DateContext(Context context){
//        mContext = context;
//        ConversationMaster.DevOpenHelper helper = new ConversationMaster.DevOpenHelper(context,"test_dev",null);
//        db = helper.getWritableDatabase();
//
//
//    }
//
//
//    public static void init(Context context){
//        mDateContext = new DateContext(context);
//    }
//
//}
