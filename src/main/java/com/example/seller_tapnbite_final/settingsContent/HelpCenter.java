package com.example.seller_tapnbite_final.settingsContent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.seller_tapnbite_final.R;

public class HelpCenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_help_center);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize expandable sections
        setupExpandableSection(R.id.listingProducts, R.id.content_listingProducts, R.id.arrow_listingProducts);
        setupExpandableSection(R.id.header_manage_orders, R.id.content_manage_orders, R.id.arrow_manage_orders);
        setupExpandableSection(R.id.header_update_menu, R.id.content_update_menu, R.id.arrow_update_menu);
        setupExpandableSection(R.id.header_receive_payments, R.id.content_receive_payments, R.id.arrow_receive_payments);
        setupExpandableSection(R.id.header_sales_performance, R.id.content_sales_performance, R.id.arrow_sales_performance);
        setupExpandableSection(R.id.header_technical_issues, R.id.content_technical_issues, R.id.arrow_technical_issues);
        setupExpandableSection(R.id.header_report_fraud, R.id.content_report_fraud, R.id.arrow_report_fraud);
        setupExpandableSection(R.id.header_selling_policies, R.id.content_selling_policies, R.id.arrow_selling_policies);
        setupExpandableSection(R.id.header_outside_payments, R.id.content_outside_payments, R.id.arrow_outside_payments);
    }

    private void setupExpandableSection(int headerId, int contentId, int arrowId) {
        LinearLayout header = findViewById(headerId);
        LinearLayout content = findViewById(contentId);
        ImageView arrow = findViewById(arrowId);

        header.setOnClickListener(v -> {
            if (content.getVisibility() == View.GONE) {
                content.setVisibility(View.VISIBLE);
                arrow.setImageResource(R.drawable.ic_arrow_up); // Assuming you have an arrow_up drawable
            } else {
                content.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.ic_arrow_down);
            }
        });
    }
}