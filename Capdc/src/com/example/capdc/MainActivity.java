package com.example.capdc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;


public class MainActivity extends Activity {
	final Context context = this;
    private BufferedReader mReader;
    private StringBuffer mBuffer;
	private String invalide = "";
	private String bon = "";
	private String mauvais = "";
	private String mauvaispacerelle = "";
	private String mauvaisexploitation = "";
	private String idparcelle = "";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        //Cr�ation d'une instance de ma classe especesbdd
        especesbdd especesbdd = new especesbdd(this);
		final parcellesbdd maparcellebdd = new parcellesbdd(this);

        // Cr�ation de plusieurs especes
        
        especesbdd.open();
        
        espece especesdelabdd = especesbdd.getespeceswithid(1);

  
        
        if (especesdelabdd != null) {
        }
        else
        {

        	espece narcissus = new espece("narcissus");
            espece lotus = new espece("lotus");

            especesbdd.insertEspece(narcissus);
            especesbdd.insertEspece(lotus);	
            especesbdd.close();

        }
        
        final Button loginButton = (Button) findViewById(R.id.enregistrer);
        loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Saisir_exploitation.class);
				// passer les donn�es ici
        		startActivity(intent);
			}
        });
        
        
        final Button sendButton = (Button) findViewById(R.id.envoyer);
        sendButton.setOnClickListener(new OnClickListener() {
        	public void checkvalidite(int id) {

	            String url = "http://192.168.0.222/php/verif.php?id=" + id;
	            htmlretriever retrieve = new htmlretriever();
	            try
	            {
	                String text = retrieve.grabSource(url);
	            	text = text.replaceAll("[^a-zA-Z0-9]","");
		            if ( text.equals("false")) {
		            	invalide = invalide + String.valueOf(id) + " ";
		            }

	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	                return;
	            }
			}
        	
			@Override
			public void onClick(View v) {
				// verification
				
				if (android.os.Build.VERSION.SDK_INT > 9) {
					StrictMode.ThreadPolicy policy = 
						new StrictMode.ThreadPolicy.Builder().permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}
				maparcellebdd.open();
				String idfromdb[] = new String[maparcellebdd.getidexploitationfromdb().length];
				idfromdb = maparcellebdd.getidexploitationfromdb();

				for(int i = 1; i <= maparcellebdd.getidexploitationfromdb().length; i++ ) {
					Log.v("Pinguin's mischevelousness", String.valueOf(idfromdb[i-1]) + ",");
					checkvalidite(Integer.parseInt(idfromdb[i-1]));
				}
				if (invalide == "") {
					envoyer();
				}
				else {					
					// Invalide, donc popup de confirmation
					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        switch (which){
					        case DialogInterface.BUTTON_POSITIVE:
								maparcellebdd.open();
					        	String[] arr=invalide.split(" ");

					        	for(int i = 1; i <= arr.length; i++ ) {
					        		// Récupération de l'idparcelle à partir de l'array, et suppression de la parcelle associée
					        		maparcellebdd.removeParcelleWithParcelleID(maparcellebdd.getparcelleswithidexploitation(Integer.valueOf(arr[i-1])).getId());
					        	}
					        	envoyer();
					        	break;

					        case DialogInterface.BUTTON_NEGATIVE:
					            //No button clicked
					            break;
					        }
							// Réinitialisation de la variable invalide, au cas où l'utilisateur refuse.
							invalide = "";

					    }
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setMessage("L'application ne peux continuer, les exploitations suivantes sont invalides : " + invalide + ". L'application va les supprimer pouvoir poursuivre l'opération").setNegativeButton("No", dialogClickListener).setPositiveButton("Yes", dialogClickListener).show();
				}
				maparcellebdd.close();

			}
			public boolean estunint(String str) {
			    try {
			        Integer.parseInt(str);
			        return true;
			    } catch (NumberFormatException nfe) {
			        return false;
			    }
			}
			
			public void envoyer() {
				try  {
					maparcellebdd.open();
					mReader = null;
					mBuffer = new StringBuffer();
					String idfromdb[] = new String[maparcellebdd.getidexploitationfromdb().length];
					idfromdb = maparcellebdd.getidexploitationfromdb();
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://192.168.0.222/php/post.php");
	
					// Request parameters and other properties.
					
					// Commencement de l'alienisation temporelle bouclénialle

					List<NameValuePair> params = new ArrayList<NameValuePair>(maparcellebdd.getColumnCount());
					
					// Initialisation du vortex
					int lengthidfromdb = maparcellebdd.getidfromdb().length;
					for(int i = 0; i < lengthidfromdb; i++ ) {
						params.add(new BasicNameValuePair("idexploitation", String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getIdexploitation())));
						params.add(new BasicNameValuePair("surface", String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getSurface())));
						params.add(new BasicNameValuePair("rprevu", String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getRendementprevu())));
						params.add(new BasicNameValuePair("rrealise", String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getRendementrealise())));
						params.add(new BasicNameValuePair("idespece", String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getIdespece())));

						httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						
						
						//Execute and get the response.
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						
						
						if (entity != null) {
						    mReader = new BufferedReader(new InputStreamReader(entity.getContent()));
				            String line = "";
				            while ((line = mReader.readLine()) != null)
				            {
				            	mBuffer.append(line);
								Log.v("mbuffer", mBuffer.toString());
								if (estunint(mBuffer.toString())) {
				            		idparcelle = mBuffer.toString();
				            	}
								else if (mBuffer.toString().replaceAll("[^a-zA-Z0-9]","").equals("true")) {
				            		bon = bon + String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getIdexploitation()) + "/" + idparcelle + " ";
				            	}
				            	else if (mBuffer.toString().replaceAll("[^a-zA-Z0-9]","").equals("")) {
				            	}
				            	else {
				            		if (String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getId()).equals(mauvaispacerelle)) {
				            		}
				            		else {
				            			if (mauvaisexploitation.equals(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getIdexploitation())) {
					            			mauvais = mauvais + String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getIdexploitation()) + " ";
				            			}
				            			else {
				            			}
				            		}
				            		mauvaispacerelle = String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getId());
				            		mauvaisexploitation = String.valueOf(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getIdexploitation());
				            	}
					            mBuffer.setLength(0);

				                
				            }
				            
					        // Removing parcelles from the starship
					        maparcellebdd.removeParcelleWithParcelleID(maparcellebdd.getparcelleswithid(Integer.valueOf(maparcellebdd.getidfromdb()[0])).getId());
							

						    try {
						    } finally {
						    }
						}
		            	

					}

					}
					catch(Exception e) {
						e.printStackTrace();
		                return;
					}
				
				// Finalisation et envoit d'un message récapitulatif
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}

				};
				Log.v("bon", bon + " ");
				Log.v("mauvais", mauvais + " ");

				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				if (bon != "") {
					bon = "Les exploitations/parcelles suivantes ont été envoyées avec succès dans la base de donnée: " + System.getProperty("line.separator") + bon + System.getProperty("line.separator");	
				}
				if (mauvais != "") {
					mauvais = "Les parcelles des exploitations suivantes n'ont pas pu être envoyées car une erreur est survenue: " + System.getProperty("line.separator") + mauvais + System.getProperty("line.separator");
				}
				String status = "Les parcelles ont été supprimées de la base de donnée locale.";
				if ((bon == "") && (mauvais == "")){
					status = "Il n'y a pas de parcelles enregistrées à envoyer sur le serveur distant.";
				}
				else {
					status = "Les parcelles ont été supprimées de la base de donnée locale.";
				}
				builder.setMessage(bon + mauvais + status).setPositiveButton("Très bien", dialogClickListener).show();
				bon = "";
				mauvais = "";
			}
        });


        
        //
        

        //
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
