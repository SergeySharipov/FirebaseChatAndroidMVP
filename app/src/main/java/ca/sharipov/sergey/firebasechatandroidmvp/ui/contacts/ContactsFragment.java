package ca.sharipov.sergey.firebasechatandroidmvp.ui.contacts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ca.sharipov.sergey.firebasechatandroidmvp.R;
import ca.sharipov.sergey.firebasechatandroidmvp.data.User;
import ca.sharipov.sergey.firebasechatandroidmvp.databinding.FragmentContactsListBinding;

import static ca.sharipov.sergey.firebasechatandroidmvp.AppConstants.USERS_CHILD;

public class ContactsFragment extends Fragment implements ContactsContract.View {

    private ContactsContract.Presenter presenter;

    private FragmentContactsListBinding binding;

    public ContactsFragment() {
    } // Required empty public constructor

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactsPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts_list, container, false);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(USERS_CHILD);
        usersRef.keepSynced(true);

        binding.recyclerView.setAdapter(new ContactsRecyclerViewAdapter(usersRef));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.takeView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.dropView();
    }

    class ContactsRecyclerViewAdapter extends FirebaseRecyclerAdapter<User, ContactsRecyclerViewAdapter.ViewHolder> {

        public ContactsRecyclerViewAdapter(DatabaseReference ref) {
            super(User.class, R.layout.item_contact, ViewHolder.class, ref);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_contact, parent, false);
            return new ViewHolder(view);
        }

        @Override
        protected void populateViewHolder(ViewHolder viewHolder, User model, int position) {
            viewHolder.bind(getRef(position).getKey(), model);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            String id;
            User mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = view.findViewById(R.id.id);
                mContentView = view.findViewById(R.id.content);
            }

            void bind(final String id, User item) {
                this.id = id;
                mItem = item;

                mIdView.setText(mItem.getEmail());
                mContentView.setText(mItem.getUsername());

                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.addContactToMyContacts(id);
                    }
                });
            }
        }
    }
}