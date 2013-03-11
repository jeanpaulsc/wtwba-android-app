package edu.harvad.law.librarylab.wtwba;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailsListAdapter extends BaseAdapter {
 
    private ArrayList<ItemDetailsInList> _data;
    Context _c;
    
    ItemDetailsListAdapter (ArrayList<ItemDetailsInList> data, Context c){
        _data = data;
        _c = c;
    }
   
    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }
    
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
    }
 
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
         View v = convertView;
         if (v == null)
         {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_details, null);
         }
 
           ImageView image = (ImageView) v.findViewById(R.id.icon);
           TextView titleView = (TextView)v.findViewById(R.id.title_list);
           TextView dueView = (TextView)v.findViewById(R.id.due_list);

           Log.w("idladapter", String.valueOf(_data.size()));
           Log.w("idladapter index", String.valueOf(position));
           ItemDetailsInList junk = _data.get(0);
           
           Log.w("idladapter message", junk.title);
           
           
           ItemDetailsInList msg = _data.get(position);
           Log.w("idladapter message in msg", msg.title);
           image.setImageResource(msg.icon);
           titleView.setText(msg.title);
           dueView.setText("Due on: "+msg.due);                      
                        
        return v;
}
}