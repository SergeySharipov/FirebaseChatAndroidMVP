package ca.sharipov.sergey.firebasechatandroidmvp.main.contacts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.sharipov.sergey.firebasechatandroidmvp.R;
import ca.sharipov.sergey.firebasechatandroidmvp.main.dummy.DummyContent;
import ca.sharipov.sergey.firebasechatandroidmvp.main.dummy.DummyContent.DummyItem;

public class ContactsFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }

    public ContactsFragment() {
    }

//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static ChatsFragment newInstance(int columnCount) {
//        ChatsFragment fragment = new ChatsFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ContactsRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.navigation, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.action_settings:
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
}
class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final ContactsFragment.OnListFragmentInteractionListener mListener;

    public ContactsRecyclerViewAdapter(List<DummyItem> items, ContactsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_chats, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        public void bind(DummyItem item) {

        }
    }
}