package org.unam.dgsa.example.agenda;

import java.util.LinkedList;
import java.util.List;

import org.unam.dgsa.example.agenda.db.Contacto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Esta clase facilita el acceso a una base de datos de SQLite.
 * @author xel666
 *
 */
public class ContactosDbAdapter {
	private static DataBaseHelper helper;
	// referencia a a la base de datos
	private static SQLiteDatabase db;
	
	private ContactosDbAdapter() {};
	
	/**
	 * Esta clase se encarga de crear, actualizar, abrir y cerrar
	 * la base de datos.
	 */
	private static class DataBaseHelper extends SQLiteOpenHelper {
		private static final int DB_VERSION = 1;
		private static final String DB_NAME = "contactos";
		private static final String CONTACTOS_TABLE_CREATE =
				"CREATE TABLE " + Contacto.class.getSimpleName() +
				" (" + Contacto.COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					Contacto.COL_NOMBRE + " TEXT, "+
					Contacto.COL_APELLIDO + " TEXT)";
					

		public DataBaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CONTACTOS_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db,
				int oldVersion,
				int newVersion) {
			// borrar la base de datos vieja
			db.execSQL("DROP TABLE IF EXISTS "+Contacto.class.getSimpleName());
			onCreate(db);
		}
		
	}
	
	/**
	 * 
	 * @return lista con todos los contactos
	 */
	public static List<Contacto> listContactos() {
		LinkedList<Contacto> contactos=new LinkedList<Contacto>();
		Cursor c = db.query(Contacto.class.getSimpleName(),
				null, null, null, null, null, null);
		while(c.moveToNext())
			contactos.add(new Contacto(c));
		c.close();
		return contactos;
	}
	
	public static void insert(Contacto c) {
		db.beginTransaction();
		try {
			ContentValues values = new ContentValues(2);
			values.put(Contacto.COL_NOMBRE, c.getNombre());
			values.put(Contacto.COL_APELLIDO, c.getApellido());
			db.insert(Contacto.class.getSimpleName(), null, values);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
	
	static void open(Context context) {
		helper = new DataBaseHelper(context);
		db = helper.getWritableDatabase();
	}
	
	static void close() {
		if(helper == null) return;
		db.close();
		helper.close();
		helper=null;
		db=null;
	}
	
	public SQLiteDatabase getDB() {
		return db;
	}

}
