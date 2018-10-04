package com.example.rxjavatutorial.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rxjavatutorial.R;
import com.example.rxjavatutorial.activities.BufferOperatorActivity;
import com.example.rxjavatutorial.activities.MapOperatorActivity;
import com.example.rxjavatutorial.activities.OperatorsActivity;

public class FragmentOperators extends Fragment {
    private OperatorsActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (OperatorsActivity) getActivity();
        View rootView = inflater.inflate(R.layout.fragment_operators, container, false);

        ListView operatorsLv = rootView.findViewById(R.id.operators_list);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.operators_list, android.R.layout.simple_list_item_1);
        operatorsLv.setAdapter(typeAdapter);

        operatorsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) replaceFragment(new FragmentFilter(), true, i);
                else if (i == 17) {
                    Intent intent = new Intent(getContext(), BufferOperatorActivity.class);
                    startActivity(intent);
                } else if (i == 20) {
                    Intent intent = new Intent(getContext(), MapOperatorActivity.class);
                    startActivity(intent);
                } else replaceFragment(new FragmentOperatorDetails(), false, i);
            }
        });
        return rootView;
    }

    private void replaceFragment(Fragment fragment, boolean isFilter, int position) {
        if (!isFilter)
            ((FragmentOperatorDetails) fragment).setPosition(position);
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.operators_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
