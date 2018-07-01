package com.example.raji.journal.Data;

import android.provider.BaseColumns;

public class JournalContract {
    private JournalContract(){

    }

        public class JournalContractSchema implements BaseColumns{
            public static final String TABLE_NAME = "Journal";
            public static final String _ID = BaseColumns._ID;
            public static final String TITLES = "Tiles";
            public static final String NOTE = "note";
        }
}
