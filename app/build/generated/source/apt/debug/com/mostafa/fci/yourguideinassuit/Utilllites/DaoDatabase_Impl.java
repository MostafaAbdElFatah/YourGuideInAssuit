package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.mostafa.fci.yourguideinassuit.classes.Department;
import com.mostafa.fci.yourguideinassuit.classes.Place;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class DaoDatabase_Impl implements DaoDatabase {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPlace;

  private final EntityInsertionAdapter __insertionAdapterOfDepartment;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPlace;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDepartment;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPlace;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDepartment;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPlace;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllDeparment;

  public DaoDatabase_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlace = new EntityInsertionAdapter<Place>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Place`(`id`,`name`,`phone`,`address`,`type`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Place value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPhone());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAddress());
        }
        if (value.getType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getType());
        }
      }
    };
    this.__insertionAdapterOfDepartment = new EntityInsertionAdapter<Department>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Department`(`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Department value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__deletionAdapterOfPlace = new EntityDeletionOrUpdateAdapter<Place>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Place` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Place value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfDepartment = new EntityDeletionOrUpdateAdapter<Department>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Department` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Department value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPlace = new EntityDeletionOrUpdateAdapter<Place>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Place` SET `id` = ?,`name` = ?,`phone` = ?,`address` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Place value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPhone());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAddress());
        }
        if (value.getType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getType());
        }
        stmt.bindLong(6, value.getId());
      }
    };
    this.__updateAdapterOfDepartment = new EntityDeletionOrUpdateAdapter<Department>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Department` SET `id` = ?,`name` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Department value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllPlace = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Place";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllDeparment = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `Department`";
        return _query;
      }
    };
  }

  @Override
  public void insertOnlySinglePlace(Place place) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPlace.insert(place);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMultiplePlaces(List<Place> list) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPlace.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnlySingleDepermnet(Department department) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDepartment.insert(department);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMultipleDepartments(List<Department> departments) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDepartment.insert(departments);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePlace(Place place) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPlace.handle(place);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDeparment(Department department) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDepartment.handle(department);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePlace(Place place) {
    __db.beginTransaction();
    try {
      __updateAdapterOfPlace.handle(place);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateDepartment(Department department) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDepartment.handle(department);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllPlace() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPlace.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllPlace.release(_stmt);
    }
  }

  @Override
  public void deleteAllDeparment() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllDeparment.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllDeparment.release(_stmt);
    }
  }

  @Override
  public List<Place> fetchbyPlaceType(String placeType) {
    final String _sql = "SELECT * FROM Place WHERE type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (placeType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, placeType);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<Place> _result = new ArrayList<Place>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Place _item;
        _item = new Place();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _item.setPhone(_tmpPhone);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        _item.setAddress(_tmpAddress);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item.setType(_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Place> fetchbyPlaceDepID(String name) {
    final String _sql = "SELECT * FROM Place WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<Place> _result = new ArrayList<Place>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Place _item;
        _item = new Place();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _item.setPhone(_tmpPhone);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        _item.setAddress(_tmpAddress);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item.setType(_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Place> fetchAllPlaces() {
    final String _sql = "SELECT * FROM Place";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfPhone = _cursor.getColumnIndexOrThrow("phone");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<Place> _result = new ArrayList<Place>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Place _item;
        _item = new Place();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _item.setPhone(_tmpPhone);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        _item.setAddress(_tmpAddress);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item.setType(_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Department fetchbyDeparmentId(String id) {
    final String _sql = "SELECT * FROM `Department` WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final Department _result;
      if(_cursor.moveToFirst()) {
        _result = new Department();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Department> fetchAllDeparments() {
    final String _sql = "SELECT * FROM `Department`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final List<Department> _result = new ArrayList<Department>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Department _item;
        _item = new Department();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
