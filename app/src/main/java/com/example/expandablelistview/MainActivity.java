package com.example.expandablelistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expListView;
    ExpandableListSetAdapter expListAdapter;
    //ExpandableListAdapter expListAdapter;
    List<String> listDataHeader;
    HashMap<String,List<String >> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the listview
        expListView=findViewById(R.id.lvExp);

        //preparing list data for header and child
        prepareListData();

        //Invoking the adapter to transfer the data on expandableListView
        expListAdapter=new ExpandableListSetAdapter(this,listDataHeader,listDataChild);
        expListView.setAdapter(expListAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Group clicked"+listDataHeader.get(i), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(i)+"group Expanded", Toast.LENGTH_SHORT).show();
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(i)+"Group collapse", Toast.LENGTH_SHORT).show();
            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //Toast.makeText(getApplicationContext(), listDataHeader.get(i)+"child clicked", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(listDataHeader.get(i));
                builder.setMessage(listDataChild.get(listDataHeader.get(i)).get(i1));
                builder.setCancelable(true);
                builder.show();
                return false;
            }
        });

    }

    private void prepareListData() {

        listDataHeader=new ArrayList<>();
        listDataChild=new HashMap<>();

        //adding header data
        listDataHeader.add("Topics");
        listDataHeader.add("TopicsCovered");
        listDataHeader.add("Topics Not Covered");
        //listDataHeader.add("Switch to StartActivity For Result");

        //adding child data
        List<String> top=new ArrayList<String>();
        top.add("A");
        top.add("B");
        top.add("C");
        top.add("D");

        List<String> topCov=new ArrayList<String>();
        topCov.add("A1");
        topCov.add("B1");
        topCov.add("C1");
        topCov.add("D1");

        List<String> topNotCov=new ArrayList<String>();
        topNotCov.add("A2");
        topNotCov.add("B2");
        topNotCov.add("C2");
        topNotCov.add("D2");

        listDataChild.put(listDataHeader.get(0),top);
        listDataChild.put(listDataHeader.get(1),topCov);
        listDataChild.put(listDataHeader.get(2),topNotCov);




    }
}
