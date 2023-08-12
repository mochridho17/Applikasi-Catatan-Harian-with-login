// Nim : 10120139
// Nama : Mochamad Ridho
// Kelas : IF4
package com.example.akb_uas_10120139.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.akb_uas_10120139.R;
import com.example.akb_uas_10120139.auth.LoginActivity;
import com.example.akb_uas_10120139.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Button bLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Add logout action
        bLogout = root.findViewById(R.id.btn_logout);
        bLogout.setOnClickListener(v -> {
            // Logout
            FirebaseAuth.getInstance().signOut();

            // Back to login page
            Toast.makeText(getActivity(), "Already logged out",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().startActivity(intent);
            getActivity().finish();
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}