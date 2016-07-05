package upplic.com.angelavto.presentation.adapters;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.Collections;
import java.util.List;

import upplic.com.angelavto.presentation.adapters.view_binders.AbstractBinder;
import upplic.com.angelavto.presentation.adapters.view_binders.AbstractExpannableBinder;

public class MultyExListViewAdapter<G extends Expannable<C>, C> extends BaseExpandableListAdapter {

    private List<G> mData;
    private AbstractExpannableBinder<G, C> mBinder;

    public MultyExListViewAdapter(AbstractExpannableBinder<G, C> binder) {
        mData = Collections.emptyList();
        mBinder = binder;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition)
                .getExpannableList()
                .size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getExpannableList().get(childPosition);
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        G data = getGroup(groupPosition);
        return mBinder.bind(convertView, data);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        C data = getGroup(groupPosition)
                .getExpannableList()
                .get(childPosition);
        return mBinder.bindChild(convertView, data);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void loadData(List<G> data) {
        if (data != null) {
            mData = data;
            notifyDataSetChanged();
        }
    }

    public List<G> getData() {
        return mData;
    }
}
