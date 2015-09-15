package com.ufida.john.mygreendao;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import me.ufida.greendao.DaoMaster;
import me.ufida.greendao.DaoSession;
import me.ufida.greendao.Note;
import me.ufida.greendao.NoteDao;


public class NoteActivity extends ListActivity {
    private SQLiteDatabase db;
    private EditText editText;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;
    public  static  final String TAG ="DaoExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDatabase();

        getNoteDao();

        String textColumn = NoteDao.Properties.Text.columnName;
        String orderBy = textColumn +" COLLATE LOCALIZED ASC";
        cursor = db.query(getNoteDao().getTablename(),getNoteDao().getAllColumns(),null,null,null,null,orderBy);
        String[] from = { textColumn,NoteDao.Properties.Comment.columnName };
        int[] to = {android.R.id.text1,android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to);
        setListAdapter(adapter);

        editText = (EditText) findViewById(R.id.editTextNote);
    }

    private  void setupDatabase(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"notes-db",null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    private NoteDao getNoteDao(){
        return daoSession.getNoteDao();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private  void addNote(){
        String noteText = editText.getText().toString();
        editText.setText("");
        final DateFormat df  = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on" + df.format(new Date());

        Note note = new Note(null,noteText,comment,new Date());
        getNoteDao().insert(note);
        Log.d(TAG,"Inserted new note,ID:"+ note.getId());
        cursor.requery();
    }

    private  void search(){
        Query query = getNoteDao().queryBuilder()
                .where(NoteDao.Properties.Text.eq("Test1"))
                .orderAsc(NoteDao.Properties.Date)
                .build();

        //List notes = query.list();
        //cursor.requery();
        QueryBuilder.LOG_SQL  = true;
        QueryBuilder.LOG_VALUES = true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        getNoteDao().deleteByKey(id);
        Log.d(TAG, "Deleted note,ID:" + id);
        cursor.requery();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMyButtonClick(View view) {
        switch (view.getId())
        {
            case  R.id.buttonAdd:
                addNote();
                break;
            case  R.id.buttonSearch:
                search();
                break;
            default:
                Log.d(TAG,"what has gone wrong ?");
                break;
        }
    }
}
