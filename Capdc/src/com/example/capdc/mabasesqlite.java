package com.example.capdc;
 
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import android.widget.Toast;
 
public class mabasesqlite extends SQLiteOpenHelper {
 
	private static final String TABLE_PARCELLES = "table_parcelles";
	private static final String COL_ID = "id";
	private static final String COL_IDEXPLOITATION = "idexploitation";
	private static final String COL_IDESPECE = "idespece";
	private static final String COL_RENDEMENTPREVU = "rendementprevu";
	private static final String COL_RENDEMENTREALISE = "rendementrealise";
	private static final String COL_SURFACE = "surface";

	
	private static final String TABLE_ESPECES = "table_especes";
	private static final String COL_EID = "id";
	private static final String COL_LIBELLE = "libelle";



 
	private static final String CREATE_PARCELLES_TABLE = "CREATE TABLE " + TABLE_PARCELLES + " ("
	+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_IDEXPLOITATION + " INTEGER NOT NULL, "
	+ COL_IDESPECE + " INTEGER NOT NULL, " + COL_RENDEMENTPREVU + " TEXT NOT NULL, " + COL_RENDEMENTREALISE +  " TEXT NOT NULL, " + COL_SURFACE + " TEXT NOT NULL);";
 
	private static final String CREATE_ESPECES_TABLE = "CREATE TABLE " + TABLE_ESPECES + " ("
			+ COL_EID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIBELLE + " TEXT NOT NULL);";
		 
	public mabasesqlite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {

			db.execSQL(CREATE_PARCELLES_TABLE);
			db.execSQL(CREATE_ESPECES_TABLE);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// renommer cette m�thode ou modifier le contenu pour faire que lorsqu'on met � jour, it should destroys everything
		db.execSQL("DROP TABLE " + TABLE_PARCELLES + ";");
		db.execSQL("DROP TABLE " + TABLE_ESPECES + ";");

		onCreate(db);
	}
 
}