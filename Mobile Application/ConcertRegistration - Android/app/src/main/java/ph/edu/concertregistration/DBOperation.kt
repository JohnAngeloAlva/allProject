package ph.edu.concertregistration

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "MY DATABASE"
val TABLENAME = "Registration"
val COL_NAME = "name"
val COL_SEAT = "seat"
val COL_CATEGORY = "category"
val COL_PNUMBER = "pnumber"
val COL_TOTALPEOPLE = "total"
val COL_GMAIL = "gmail"
val COL_ID = "id"

class DBOperation (var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {

    //Create database and table
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLENAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_NAME VARCHAR(256),$COL_SEAT VARCHAR(256),$COL_CATEGORY VARCHAR(256),$COL_PNUMBER VARCHAR(256),$COL_TOTALPEOPLE INTEGER, $COL_GMAIL VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //Add record
    fun insertData(registration: Registration) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, registration.name)
        contentValues.put(COL_SEAT, registration.seat)
        contentValues.put(COL_CATEGORY, registration.category)
        contentValues.put(COL_PNUMBER, registration.pnumber)
        contentValues.put(COL_TOTALPEOPLE, registration.total)
        contentValues.put(COL_GMAIL, registration.gmail)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show()
        }
    }

    //Read records
    fun readData(): MutableList<Registration> {
        val list: MutableList<Registration> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val registration = Registration()
                registration.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                registration.name = result.getString(result.getColumnIndex(COL_NAME))
                registration.seat = result.getString(result.getColumnIndex(COL_SEAT))
                registration.category = result.getString(result.getColumnIndex(COL_CATEGORY))
                registration.pnumber = result.getString(result.getColumnIndex(COL_PNUMBER))
                registration.total = result.getString(result.getColumnIndex(COL_TOTALPEOPLE)).toInt()
                registration.gmail = result.getString(result.getColumnIndex(COL_GMAIL))
                list.add(registration)
            } while (result.moveToNext())
        }
        return list
    }

    //Search records base on name
    fun searchRecords (name: String): MutableList<Registration>{
        val list: MutableList<Registration> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME Where name like '%$name%'"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val registration = Registration()
                registration.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                registration.name = result.getString(result.getColumnIndex(COL_NAME))
                registration.seat = result.getString(result.getColumnIndex(COL_SEAT))
                registration.category = result.getString(result.getColumnIndex(COL_CATEGORY))
                registration.pnumber = result.getString(result.getColumnIndex(COL_PNUMBER))
                registration.total = result.getString(result.getColumnIndex(COL_TOTALPEOPLE)).toInt()
                registration.gmail = result.getString(result.getColumnIndex(COL_GMAIL))
                list.add(registration)
            } while (result.moveToNext())
        }
        return list
    }

    //Delete All Records
    fun deleteRecords (){
        val db = this.writableDatabase
        db.delete(TABLENAME,null,null)
        Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
        db.close()

    }

    //Delete One Record
    fun deleteOneRecords (registration: Registration){
        val db = this.writableDatabase
        db.delete(TABLENAME, "id="+registration.id,null)
        Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
        db.close()

    }

    //Edit Record
    fun editData(registration: Registration){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, registration.id)
        contentValues.put(COL_NAME, registration.name)
        contentValues.put(COL_SEAT, registration.seat)
        contentValues.put(COL_CATEGORY, registration.category)
        contentValues.put(COL_PNUMBER, registration.pnumber)
        contentValues.put(COL_TOTALPEOPLE, registration.total)
        contentValues.put(COL_GMAIL, registration.gmail)
        db.update(TABLENAME, contentValues, "id="+registration.id,null)
        Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()
        db.close()

    }

}