// Nim : 10120139
// Nama : Mochamad Ridho
// Kelas : IF4
package com.example.akb_uas_10120139.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.akb_uas_10120139.R;


public class AboutPage3 extends Fragment {

    public AboutPage3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_page3, container, false);
    }
}