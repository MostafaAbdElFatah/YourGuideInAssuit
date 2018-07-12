package com.mostafa.fci.yourguideinassuit.Utilllites;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.mostafa.fci.yourguideinassuit.R;
import java.util.HashMap;
import java.util.List;


public class ExpAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> main_titles;
    private HashMap<String,List<String>> child_titles;

    public ExpAdapter(Context context,List<String> head_titles,HashMap<String,List<String>> child_titles){
        this.context = context;
        this.main_titles = head_titles;
        this.child_titles = child_titles;
    }

    @Override
    public int getGroupCount() {
        return main_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child_titles.get(main_titles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return main_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get(main_titles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_item,null);
        }
        TextView textView = convertView.findViewById(R.id.namePlaceParentItem);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/Sane Font.ttf");
        textView.setTypeface(typeface);
        textView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title = (String) this.getChild(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_item,null);
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/AD-Rsail.otf");
        TextView nameTextView   = convertView.findViewById(R.id.namePlaceChildItem);
        nameTextView.setText(title);

        nameTextView.setTypeface(typeface);
        TextView addressTextView = convertView.findViewById(R.id.addressPlaceChildItem);
        Typeface typeface2 = Typeface.createFromAsset(context.getAssets(),"fonts/Neckar Font Regular.ttf");
        addressTextView.setTypeface(typeface2);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
