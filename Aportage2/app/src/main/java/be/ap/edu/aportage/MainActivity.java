package be.ap.edu.aportage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Creating a new child in "locations" */
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Location location = new Location("test", "test");
        String locationId = "test2";

        mDatabase.child("locations").child(locationId).setValue(location);
    }

}
