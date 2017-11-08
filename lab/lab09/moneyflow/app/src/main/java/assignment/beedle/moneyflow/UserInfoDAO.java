package assignment.beedle.moneyflow;

import android.arch.persistence.room.*;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Beedle on 8/11/2560.
 */

@Dao
interface UserInfoDAO {


    @Insert
    void insert(UserInfo recordInfo);

    @Query("SELECT * FROM RECORD")
    List<UserInfo> showAll();

    @Update
    void update(UserInfo recordInfo);

    @Query("SELECT * FROM RECORD WHERE TYPE = :type")
    List<UserInfo> queryByType(String type);

    @Delete
    void delete(UserInfo recordInfo);
}
