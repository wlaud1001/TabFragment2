package com.example.user.tabfragment;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.RelativeLayout;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    private ListView lv;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    private static String url = "https://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> contactList;

    /*
    ListView listView;
    myAdapter adapter;
    String[] fruits = {"A", "B", "C"};
    */


    public Fragment1() {
        // Required empty public constructor
    }

//리스트 눌렀을 때 화면전환

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        contactList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.ListView);
        new GetContacts().execute();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Data data = contactList.get(position);

                HashMap<String,String> data = contactList.get(position);


                Intent intent = new Intent(getActivity().getApplicationContext(),itemclickevent.class);

                intent.putExtra("name", data.get("name"));
                intent.putExtra("email", data.get("email"));
                intent.putExtra("mobile", data.get("mobile"));
                startActivity(intent);

            }
        });

        return view;
    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //show loading dialog

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if(jsonStr != null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    //getting json array node
                    JSONArray contacts = jsonObject.getJSONArray("contacts");

                    //looping through all contacts
                    for(int i=0; i<contacts.length(); i++)
                    {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");

                        //Phone node is JSON object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        HashMap<String, String> contact = new HashMap<>();

                        //adding each child node to hashmap
                        contact.put("id",id);
                        contact.put("name",name);
                        contact.put("email",email);
                        contact.put("mobile",mobile);

                        //adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e){
                    Log.e(TAG, "JSON parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),
                                    "JSON parsing error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Dismiss the dialog
            if(pDialog.isShowing()){
                pDialog.dismiss();
            }


            //update inf json data to listview
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), contactList,
                    R.layout.list_item, new String[]{"name", "email", "mobile"},
                    new int[] {R.id.name, R.id.email, R.id.mobile});

            lv.setAdapter(adapter);



        }
    }
/*
    class myAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return contactList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final int pos = position;
            final Context context = parent.getContext();

            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);

            }
            TextView name = (TextView) convertView.findViewById(R.id.name) ;
            TextView email = (TextView) convertView.findViewById(R.id.email) ;
            TextView mobile = (TextView) convertView.findViewById(R.id.mobile) ;

            contactList listviewitem = contactList.get(position);

            name.setText(listviewitem.get(name));

            return null;
        }

    }

*/



}