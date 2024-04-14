package com.example.abc4;

import android.provider.BaseColumns;

public final class ParentContract {
    private ParentContract() {}

    public static class ParentEntry implements BaseColumns {
        public static final String TABLE_NAME = "parents";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_CHILD_NAME = "child_name";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CLASSROOM = "classroom";
    }
}
