package com.jalilurrahman.expandable;

import android.support.annotation.IntDef;

import static com.jalilurrahman.expandable.UserStatusType.ACTIVE;
import static com.jalilurrahman.expandable.UserStatusType.AWAY;
import static com.jalilurrahman.expandable.UserStatusType.DO_NOT_DISTURB;
import static com.jalilurrahman.expandable.UserStatusType.INVISIBLE;

/**
 * Created by Murtuza Rahman on 2019-05-20.
 */
@IntDef({ACTIVE, DO_NOT_DISTURB, INVISIBLE, AWAY})
public @interface UserStatusType {
    int ACTIVE = 1;
    int DO_NOT_DISTURB = 2;
    int INVISIBLE = 3;
    int AWAY = 4;
}
