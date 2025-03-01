package com.example.seller_tapnbite_final.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.seller_tapnbite_final.OrdersMenu;
import com.example.seller_tapnbite_final.Reports; // Ensure this class exists!
import com.example.seller_tapnbite_final.R;

public class OrderMain extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_main, container, false);

        // Orders Card Intent
        CardView ordersCard = view.findViewById(R.id.ordersCard);
        if (ordersCard != null) {
            ordersCard.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), OrdersMenu.class);
                startActivity(intent);
            });
        }

        // Reports Card Intent - Fixed
        CardView reportCard = view.findViewById(R.id.reportCard); // Use correct variable name
        if (reportCard != null) { // Use the correct variable
            reportCard.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), Reports.class);
                startActivity(intent);
            });
        }

        return view;
    }
}
