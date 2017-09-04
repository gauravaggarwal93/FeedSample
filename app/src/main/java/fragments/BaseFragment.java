package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Gaurav on 9/2/17.
 */

public abstract class BaseFragment extends Fragment {

    public Activity mActivity;

    public abstract String getFragmentTag();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

}
