package hcmut.cse.ttcnpm.restapitutorial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnView;
    private EditText txtSid;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSid=(EditText)findViewById(R.id.txtSID);
        btnView=(Button)findViewById(R.id.btnView);
        lstView=(ListView)findViewById(R.id.lstView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ScheduleDownload().execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    class ScheduleDownload extends AsyncTask<Void,Void,Void>{
        String sid;
        ProgressDialog dlg;
        ScheduleInfo info;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Reader rd=API.getData("http://hungphongbk.tmp-technology.com/rest-tutorial/index.php/schedule/" + sid);
                info=new GsonBuilder().create().fromJson(rd,ScheduleInfo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sid=txtSid.getText().toString();
            dlg=new ProgressDialog(MainActivity.this);
            dlg.setCancelable(false);
            dlg.setMessage("Downloading...");
            dlg.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dlg.dismiss();
            if(info==null){
                AlertDialog err=new AlertDialog.Builder(MainActivity.this).create();
                err.setTitle("ERROR");
                err.setMessage("Network error expected!");
                err.setButton(DialogInterface.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                err.show();
            } else {
                ScheduleAdapter adapter=new ScheduleAdapter(MainActivity.this,R.layout.schedule_item,info.getTimetable());
                lstView.setAdapter(adapter);
            }
        }
    }
    class ScheduleAdapter extends ArrayAdapter<ScheduleItem>{

        private Context context;
        private int resource;
        private List<ScheduleItem> arr;
        public ScheduleAdapter(Context context, int resource, List<ScheduleItem> objects) {
            super(context, resource, objects);
            this.context=context;
            this.resource=resource;
            this.arr=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder")
            View rowView=inflater.inflate(resource, parent, false);
            TextView txtSubjectId=(TextView)rowView.findViewById(R.id.txtSubjectId);
            TextView txtSubjectName=(TextView)rowView.findViewById(R.id.txtSubjectName);
            txtSubjectId.setText(getItem(position).getSubjectId());
            txtSubjectName.setText(getItem(position).getSubjectName());

            return rowView;
        }
    }
}
