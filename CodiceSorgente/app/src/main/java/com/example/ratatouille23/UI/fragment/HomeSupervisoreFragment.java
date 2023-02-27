package com.example.ratatouille23.UI.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeSupervisoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeSupervisoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeSupervisoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeSupervisoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeSupervisoreFragment newInstance(String param1, String param2) {
        HomeSupervisoreFragment fragment = new HomeSupervisoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_home_supervisore, container, false);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //Provvisorio: fatto per non dover riavviare l'applicazione per tornare alla LoginActivity
                Intent intent = new Intent(getContext(), LoginActivity.class);
                HomeSupervisoreFragment.this.startActivity(intent);
                //Esce dall'applicazione come il bottone centrale di Android
//                getActivity().moveTaskToBack(true);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return inflatedView;
    }
}