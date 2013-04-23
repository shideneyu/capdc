package com.example.capdc;

import android.R.array;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class parcellesbdd {

	private static final int VERSION_BDD = 9;
	private static final String NOM_BDD = "mabdd.db";

	private static final String TABLE_PARCELLES = "table_parcelles";
	private static final String COL_ID = "id";
	private static final int NUM_COL_ID = 0;
	private static final String COL_IDEXPLOITATION = "idexploitation";
	private static final int NUM_COL_IDEXPLOITATION = 1;
	private static final String COL_IDESPECE = "idespece";
	private static final int NUM_COL_IDESPECE = 2;
	private static final String COL_RENDEMENTPREVU = "rendementprevu";
	private static final int NUM_COL_RENDEMENTPREVU = 3;
	private static final String COL_RENDEMENTREALISE = "rendementrealise";
	private static final int NUM_COL_RENDEMENTREALISE = 4;
	private static final String COL_SURFACE = "surface";
	private static final int NUM_COL_SURFACE = 5;



	private static SQLiteDatabase bdd;

	private static mabasesqlite mabasesqlite;

	public parcellesbdd(Context context){
		//On cr�er la BDD et sa table
		mabasesqlite = new mabasesqlite(context, NOM_BDD, null, VERSION_BDD);
	}

	public static void open(){
		//on ouvre la BDD en �criture
		bdd = mabasesqlite.getWritableDatabase();
	}

	public void close(){
		//on ferme l'acc�s � la BDD
		bdd.close();
	}

	public SQLiteDatabase getBDD(){
		return bdd;
	}

	public long insertParcelle(parcelle parcelle){
		//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_IDEXPLOITATION, parcelle.getIdexploitation());
		values.put(COL_IDESPECE, parcelle.getIdespece());
		values.put(COL_RENDEMENTPREVU, parcelle.getRendementprevu());
		values.put(COL_RENDEMENTREALISE, parcelle.getRendementrealise());
		values.put(COL_SURFACE, parcelle.getSurface());

		//on ins�re l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_PARCELLES, null, values);
	}

	public int removeParcelleWithParcelleID(int id){
		//Suppression d'un livre de la BDD gr�ce � l'ID
		return bdd.delete(TABLE_PARCELLES, COL_ID + " = " +id, null);
	}

	public parcelle getparcelleswithid(int id){
		Cursor c = bdd.query(TABLE_PARCELLES, new String[] {COL_ID, COL_IDEXPLOITATION, COL_IDESPECE, COL_RENDEMENTPREVU, COL_RENDEMENTREALISE, COL_SURFACE}, COL_ID + " = \"" + id +"\"", null, null, null, null);
		return cursorToParcelle(c);
	}
	
	public parcelle getparcelleswithidexploitation(int id){
		Cursor c = bdd.query(TABLE_PARCELLES, new String[] {COL_ID, COL_IDEXPLOITATION, COL_IDESPECE, COL_RENDEMENTPREVU, COL_RENDEMENTREALISE, COL_SURFACE}, COL_IDEXPLOITATION + " = \"" + id +"\"", null, null, null, null);
		return cursorToParcelle(c);
	}
	
	public String[] getidexploitationfromdb() {
		Cursor c = bdd.query(TABLE_PARCELLES, new String[] {COL_IDEXPLOITATION}, "", null, null, null, null);
		String row[] = new String[c.getCount()];
		int i = -1;
		while(c.moveToNext()) {
			i++;
			row[i] = c.getString(0);
		}
		return row;
	}
	
	public int getColumnCount() {
		Cursor c = bdd.query(TABLE_PARCELLES, new String[] {COL_ID, COL_IDEXPLOITATION, COL_IDESPECE, COL_RENDEMENTPREVU, COL_RENDEMENTREALISE, COL_SURFACE}, "", null, null, null, null);
		return c.getColumnCount();
	}
	
	public String[] getidfromdb() {
		Cursor c = bdd.query(TABLE_PARCELLES, new String[] {COL_ID}, "", null, null, null, null);
		String row[] = new String[c.getCount()];
		
		int i = -1;
		while(c.moveToNext()) {
			i++;
			row[i] = c.getString(0);
		}
		return row;
	}
	

	private parcelle cursorToParcelle(Cursor c){
		if (c.getCount() == 0)
			return null;

		c.moveToFirst();
		parcelle parcelle = new parcelle();
		parcelle.setId(c.getInt(NUM_COL_ID));
		parcelle.setIdexploitation(c.getInt(NUM_COL_IDEXPLOITATION));
		parcelle.setIdespece(c.getInt(NUM_COL_IDESPECE));
		parcelle.setRendementprevu(c.getString(NUM_COL_RENDEMENTPREVU));
		parcelle.setRendementrealise(c.getString(NUM_COL_RENDEMENTREALISE));
		parcelle.setSurface(c.getString(NUM_COL_SURFACE));

		
		c.close();

		return parcelle;
	}
}