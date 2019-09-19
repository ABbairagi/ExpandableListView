package com.example.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

class ExpandableListSetAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> _listDataHeader;
    HashMap<String, List<String>> _listDataChild;
    public ExpandableListSetAdapter(MainActivity mainActivity, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this.context=mainActivity;
        this._listDataHeader=listDataHeader;
        this._listDataChild=listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPossition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPossition)).size();
    }

    @Override
    public Object getGroup(int groupPossition) {
        return this._listDataHeader.get(groupPossition);
    }

    @Override
    public Object getChild(int groupPosition, int childPossition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPossition);
    }

    @Override
    public long getGroupId(int groupPossition) {
        return groupPossition;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        final String headerTitle=(String) getGroup(groupPosition);

        if(view==null)
        {
            LayoutInflater infalInflater =(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=infalInflater.inflate(R.layout.list_item,null);
        }

        TextView txtListChild =(TextView)view.findViewById(R.id.lblListItem);
        txtListChild.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPossition, int childPossition, boolean isLastChild, View convertview, ViewGroup viewGroup) {
        final String childText=(String) getChild(groupPossition,childPossition);

        if(convertview==null)
        {
            LayoutInflater infalInflater =(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview=infalInflater.inflate(R.layout.list_item,null);
        }

        TextView txtListChild =(TextView)convertview.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return convertview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
