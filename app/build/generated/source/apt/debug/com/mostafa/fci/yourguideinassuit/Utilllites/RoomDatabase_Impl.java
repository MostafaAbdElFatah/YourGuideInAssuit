package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class RoomDatabase_Impl extends RoomDatabase {
  private volatile DaoDatabase _daoDatabase;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Department` (`id` INTEGER NOT NULL, `name` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Place` (`id` INTEGER NOT NULL, `name` TEXT, `phone` TEXT, `address` TEXT, `type` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"780b2080dc43809aa59290f6d00127e2\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Department`");
        _db.execSQL("DROP TABLE IF EXISTS `Place`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDepartment = new HashMap<String, TableInfo.Column>(2);
        _columnsDepartment.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDepartment.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDepartment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDepartment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDepartment = new TableInfo("Department", _columnsDepartment, _foreignKeysDepartment, _indicesDepartment);
        final TableInfo _existingDepartment = TableInfo.read(_db, "Department");
        if (! _infoDepartment.equals(_existingDepartment)) {
          throw new IllegalStateException("Migration didn't properly handle Department(com.mostafa.fci.yourguideinassuit.classes.Department).\n"
                  + " Expected:\n" + _infoDepartment + "\n"
                  + " Found:\n" + _existingDepartment);
        }
        final HashMap<String, TableInfo.Column> _columnsPlace = new HashMap<String, TableInfo.Column>(5);
        _columnsPlace.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPlace.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsPlace.put("phone", new TableInfo.Column("phone", "TEXT", false, 0));
        _columnsPlace.put("address", new TableInfo.Column("address", "TEXT", false, 0));
        _columnsPlace.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlace = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlace = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlace = new TableInfo("Place", _columnsPlace, _foreignKeysPlace, _indicesPlace);
        final TableInfo _existingPlace = TableInfo.read(_db, "Place");
        if (! _infoPlace.equals(_existingPlace)) {
          throw new IllegalStateException("Migration didn't properly handle Place(com.mostafa.fci.yourguideinassuit.classes.Place).\n"
                  + " Expected:\n" + _infoPlace + "\n"
                  + " Found:\n" + _existingPlace);
        }
      }
    }, "780b2080dc43809aa59290f6d00127e2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Department","Place");
  }

  @Override
  public DaoDatabase daoDatabase() {
    if (_daoDatabase != null) {
      return _daoDatabase;
    } else {
      synchronized(this) {
        if(_daoDatabase == null) {
          _daoDatabase = new DaoDatabase_Impl(this);
        }
        return _daoDatabase;
      }
    }
  }
}
