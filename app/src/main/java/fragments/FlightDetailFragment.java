package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guest.myapplication.R;

import java.util.ArrayList;

import constants.ApiConstants;
import dto.Appendix;
import dto.Fare;

/**
 * Created by Gaurav on 9/2/17.
 *
 * This fragment is used for showing flight detail view by showing fare prices from different providers.
 */

public class FlightDetailFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    private FlightDetailAdapter mFlightDetailAdapter;

    private ArrayList<Fare> fareList;

    public static FlightDetailFragment getInstance(Bundle bundle) {
        FlightDetailFragment flightDetailFragment = new FlightDetailFragment();
        flightDetailFragment.setArguments(bundle);
        return flightDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fareList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_detail, container, false);
        fareList = getArguments().getParcelableArrayList(ApiConstants.FARE);
        findViews(view);
        bindViews();

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    private void bindViews() {
        mFlightDetailAdapter = new FlightDetailAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mFlightDetailAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public String getFragmentTag() {
        return FlightDetailFragment.class.getName();
    }


    private class FlightDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.flight_detail_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ItemViewHolder) holder).bindViews(fareList.get(position));
        }

        @Override
        public int getItemCount() {
            return fareList.size();
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvProvider;

        private TextView mTvFlightPrice;

        ItemViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mTvProvider = (TextView) itemView.findViewById(R.id.tv_provider);
            mTvFlightPrice = (TextView) itemView.findViewById(R.id.tv_flight_price);
        }

        void bindViews(Fare fare) {

            mTvFlightPrice.setText(fare.getFare());
            mTvProvider.setText(Appendix.getInstance().getProviders().getProviderName(fare.getProviderId()));
        }
    }

}
