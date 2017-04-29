package com.mapps.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ScheduleFragment extends Fragment {

    Button Schedule;
    EditText name, rollNumber, facultyName, Topic;

    public ScheduleFragment() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        name = (EditText) view.findViewById(R.id.name);
        rollNumber = (EditText) view.findViewById(R.id.rollNumber);
        facultyName = (EditText) view.findViewById(R.id.facultyName);
        Topic = (EditText) view.findViewById(R.id.Topic);

        Schedule = (Button) view.findViewById(R.id.scheduleButton);

        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/Appointments");
                Appointment appointment = new Appointment(name.getText().toString(),rollNumber.getText().toString(),
                        facultyName.getText().toString(),Topic.getText().toString());
                databaseReference.child(facultyName.getText().toString()+name.getText().toString()).setValue(appointment);
                Toast.makeText(getActivity(), "Appointment Requested to Faculty!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Main2Activity.class));

            }
        });

        return view;
    }
}
