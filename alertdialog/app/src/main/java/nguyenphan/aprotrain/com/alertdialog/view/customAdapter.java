package nguyenphan.aprotrain.com.alertdialog.view;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nguyenphan.aprotrain.com.alertdialog.R;


public class customAdapter extends CursorAdapter {

//    note = (TextView) findViewById(R.id.note);
//    dot = (TextView) findViewById(R.id.dot);
//    timestamp = (TextView) findViewById(R.id.timestamp);


    private Cursor cursor;

    public customAdapter(Context context, Cursor cur) {
        super(context, cur);
        cursor = cur;
        // TODO Auto-generated constructor stub
    }


    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {
        // TODO Auto-generated method stub

        // ColID
//            TextView txtID = (TextView) view.findViewById(R.id.noidung);
//            txtID.setText(cursor.getString(cursor.getColumnIndex("ID")));

        // ColCode
        TextView txtCode = (TextView) view.findViewById( R.id.timestamp);
        txtCode.setText(cursor.getString(cursor.getColumnIndex("note")));
        // Displaying dot from HTML character code
        TextView dot =(TextView) view.findViewById( R.id.dot);
        dot.setText( Html.fromHtml("&#8226;"));
        // Ngay
        TextView txtCountry = (TextView) view.findViewById( R.id.note);
        txtCountry.setText(formatDate((cursor.getString(cursor.getColumnIndex("timestamp")))));

    }
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate( R.layout.list_item, parent, false);
        bindView(view, context, cursor);
        return view;
    }




    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }




}

