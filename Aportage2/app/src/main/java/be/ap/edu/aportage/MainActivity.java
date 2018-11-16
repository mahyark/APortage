package be.ap.edu.aportage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private ArrayList<String> mLocations = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Creating a new child document in "locations" */
        mDatabase = FirebaseDatabase.getInstance().getReference("locations");


        // -- temp item --
        //Location location = new Location("test2 ", "tst");
        //String location = "test_location";
        //String locationId = "test";

        // -- add new item --
        //mDatabase.child(locationId).setValue(location);
        //Log.i("Add Item", "Success");

        /* Creating listView */
        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mLocations);
        mListView.setAdapter(arrayAdapter);

        /* Get data from database */
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Location value = dataSnapshot.child("test").getValue(Location.class);

                Iterable<DataSnapshot> locationChildren = dataSnapshot.getChildren();

                for (DataSnapshot location : locationChildren) {
                    Location l = location.getValue(Location.class);
                    Log.d("location: ", l.toString());
                    mLocations.add(l.toString());
                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Error", "Failed to read value.", error.toException());
            }
        });
    }
}
