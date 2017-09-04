package fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.guest.myapplication.MainActivity;
import com.example.guest.myapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import constants.ApiConstants;
import dto.Appendix;
import dto.Flight;
import utils.ApiUtils;
import utils.Utils;

/**
 * Created by Gaurav on 9/2/17.
 */

public class FlightSearchFragment extends BaseFragment {

    private static final String LOG_TAG = "FlightSearchFragment";

    private RecyclerView mRecyclerView;

    private MyAdapter mMyAdapter;

    private ArrayList<Flight> mFlightList;

    private String mQuery;

    private boolean loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFlightList = new ArrayList<>();

//        Handling Orientation changes. This also avoids memory leaks due to asynctask
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_search, container, false);
        findViews(view);
        bindViews();
        startSearching(mQuery);
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    private void bindViews() {
        mMyAdapter = new MyAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMyAdapter);
    }

    @Override
    public String getFragmentTag() {
        return FlightSearchFragment.class.getName();
    }

    /**
     *
     * @param query - Flight query(Not using it right now)
     */
    private void startSearching(String query) {
        if (loading) {
            Log.d(LOG_TAG, "Already loading..");
            return;
        }
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                ApiUtils.ApiResponseListener apiResponseListener = new ApiUtils.ApiResponseListener() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        if (!isAdded()) {
                            return;
                        }
                        loading = false;
                        Appendix.getInstance().fromJsonObject(jsonObject.optJSONObject(ApiConstants.APPENDIX));
                        mFlightList = new Flight().fromJsonArray(jsonObject.optJSONArray(ApiConstants.FLIGHTS));
                        Log.d(LOG_TAG, "Total items: " + mFlightList.size());
                        Utils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });

                    }

                    @Override
                    public void onFailure(VolleyError volleyError) {

                    }
                };
                loading = true;
                ApiUtils.getSearchResult(apiResponseListener);
                return null;
            }
        };

        task.execute();
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.flight_search_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ItemViewHolder) holder).bindViews(mFlightList.get(position));
        }

        @Override
        public int getItemCount() {
            return mFlightList.size();
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTvFlightCode;

        private TextView mTvFlightPrice;

        private TextView mTvFlightTime;

        private TextView mTvFlightClass;

        private LinearLayout mLlContainer;

        ItemViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mTvFlightCode = (TextView) itemView.findViewById(R.id.tv_flight_code);
            mTvFlightPrice = (TextView) itemView.findViewById(R.id.tv_flight_price);
            mTvFlightTime = (TextView) itemView.findViewById(R.id.tv_flight_time);
            mTvFlightClass = (TextView) itemView.findViewById(R.id.tv_flight_class);
            mLlContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);
        }

        void bindViews(Flight flight) {
            mTvFlightCode.setText(Appendix.getInstance().getAirlines().getAirlineName(flight.getAirlineCode()));
            if (!flight.getFareList().isEmpty()){
                mTvFlightPrice.setText(getString(R.string.Rs) + flight.getFareList().get(0).getFare());
            }
            Date arrival = new Date(Long.valueOf(flight.getArrivalTime()));
            Date departure = new Date(Long.valueOf(flight.getDepartureTime()));
            mTvFlightTime.setText(arrival.getHours() + " : " + arrival.getMinutes() + " -> " + departure.getHours() + " : " + departure.getMinutes());
            mTvFlightCode.setText(Appendix.getInstance().getAirlines().getAirlineName(flight.getAirlineCode()));
            mTvFlightClass.setText(flight.getAirlineClass());
            mLlContainer.setTag(flight);
            mLlContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_container:
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(ApiConstants.FARE, ((Flight) v.getTag()).getFareList());
                    ((MainActivity) getActivity()).startFragmentTransition(FlightDetailFragment.getInstance(bundle));
                    break;
            }
        }
    }
}
