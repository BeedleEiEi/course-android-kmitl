package assignment.beedle.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Beedle on 8/11/2560.
 */

@Database(entities = {UserInfo.class}, version = 1)
abstract class UserDB extends RoomDatabase {
    public abstract UserInfoDAO getRecordInfoDAO();

}