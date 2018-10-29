package nguyenphan.aprotrain.com.alertdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nguyenphan.aprotrain.com.alertdialog.database.DBHelper;
import nguyenphan.aprotrain.com.alertdialog.database.model.Note;
import nguyenphan.aprotrain.com.alertdialog.database.model.NoteModify;
import nguyenphan.aprotrain.com.alertdialog.view.customAdapter;


public class MainActivity extends AppCompatActivity {




    private DBHelper db;
    private NoteModify noteModify;
    private List<Note> notesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );





        db = new DBHelper(this);
        noteModify = new NoteModify( this );
       //  noteModify.insertNote( "Di thi Android" ); //khoi tao note dau tien
        //notesList.addAll(db.getAllNotes());
        final ListView lisView1 = (ListView)findViewById(R.id.listview1);

        Cursor c = db.SelectData();
        customAdapter customAdapter = new customAdapter( this,c );


        lisView1.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id ==R.id.action_cancel) {
            Toast.makeText( MainActivity.this,"Ban chon Exit",Toast.LENGTH_LONG
            ).show();
            MainActivity.this.finish();
        }
        if (id == R.id.action_settings) {
            final LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
            final View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);


           // AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( context )
            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilderUserInput.setView(view);
            alertDialogBuilderUserInput
                    .setCancelable(false)
                    .setPositiveButton( "Commit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final EditText edt = (EditText) view.findViewById( R.id.note );
                            String noidungadd = (String) edt.getText().toString();

                            noteModify.insertNote( noidungadd );
                            Intent restartIntent = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
                            restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(restartIntent);


                            // final View view = layoutInflaterAndroid.inflate(R.layout.activity_main, null);

                        }
                    } )
                    .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    } );




            final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
            alertDialog.show();
//
//            Button cancel = (Button) findViewById( R.id.cancel );
//            final Button commit1 = (Button) findViewById( R.id.commit );
//            final
//            commit1.setOnClickListener( new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                   //
//
//
//                }
//            } );
        }

        return super.onOptionsItemSelected( item );
    }



}
