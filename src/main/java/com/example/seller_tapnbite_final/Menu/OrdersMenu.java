package com.example.seller_tapnbite_final.Menu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seller_tapnbite_final.R;

public class OrdersMenu extends AppCompatActivity {

    // Variables to track order counts
    private int pendingCount = 1;
    private int preparingCount = 2;
    private int readyCount = 4;

    // TextViews to display counts
    private TextView pendingCountText;
    private TextView preparingCountText;
    private TextView readyCountText;

    // Order card views
    private CardView orderCard1;
    private CardView orderCard2;

    // Status labels
    private TextView statusLabel1;
    private TextView statusLabel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_menu);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        getWindow().setStatusBarColor(ContextCompat.getColor(OrdersMenu.this, R.color.primary));

        pendingCountText = findViewById(R.id.pendingCountText);
        preparingCountText = findViewById(R.id.preparingCountText);
        readyCountText = findViewById(R.id.readyCountText);

        orderCard1 = findViewById(R.id.orderCard1);
        orderCard2 = findViewById(R.id.orderCard2);

        statusLabel1 = findViewById(R.id.statusLabel1);
        statusLabel2 = findViewById(R.id.statusLabel2);

        statusLabel1.setText("PENDING");
        statusLabel1.setTextColor(ContextCompat.getColor(this, R.color.orange));

        statusLabel2.setText("PREPARING");
        statusLabel2.setTextColor(ContextCompat.getColor(this, R.color.primary));

        updateCountDisplay();

        Button acceptBtn = findViewById(R.id.acceptButton);
        Button rejectBtn = findViewById(R.id.rejectButton);

        Button markReadyBtn = findViewById(R.id.markReadyButton);

        acceptBtn.setOnClickListener(v -> acceptOrder(orderCard1));
        rejectBtn.setOnClickListener(v -> rejectOrder(orderCard1));
        markReadyBtn.setOnClickListener(v -> markOrderReady(orderCard2));
    }

    /**
     * Updates the status counts displayed in the card views at the top
     */
    private void updateCountDisplay() {
        pendingCountText.setText(String.valueOf(pendingCount));
        preparingCountText.setText(String.valueOf(preparingCount));
        readyCountText.setText(String.valueOf(readyCount));
    }

    /**
     * Accept an order - moves from Pending to Preparing
     * @param orderCard The card view representing the order
     */
    private void acceptOrder(CardView orderCard) {
        if (pendingCount > 0) {
            pendingCount--;
            preparingCount++;
            updateCountDisplay();

            orderCard.findViewById(R.id.acceptButton).setVisibility(View.GONE);
            orderCard.findViewById(R.id.rejectButton).setVisibility(View.GONE);

            Button markReadyBtn = new Button(this);
            markReadyBtn.setText("Mark Ready");
            markReadyBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.primary));
            markReadyBtn.setOnClickListener(v -> markOrderReady(orderCard));

            ((View) orderCard.findViewById(R.id.buttonContainer)).setVisibility(View.VISIBLE);

            statusLabel1.setText("PREPARING");
            statusLabel1.setTextColor(ContextCompat.getColor(this, R.color.primary));

            Toast.makeText(this, "Order accepted and moved to Preparing", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Reject an order - removes it from Pending
     * @param orderCard The card view representing the order
     */
    private void rejectOrder(CardView orderCard) {
        if (pendingCount > 0) {
            pendingCount--;
            updateCountDisplay();

            statusLabel1.setText("REJECTED");
            statusLabel1.setTextColor(ContextCompat.getColor(this, R.color.primary));

            orderCard.findViewById(R.id.acceptButton).setVisibility(View.GONE);
            orderCard.findViewById(R.id.rejectButton).setVisibility(View.GONE);

            Toast.makeText(this, "Order rejected", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Mark an order as ready - moves from Preparing to Ready
     * @param orderCard The card view representing the order
     */
    private void markOrderReady(CardView orderCard) {
        if (preparingCount > 0) {
            preparingCount--;
            readyCount++;
            updateCountDisplay();

            Button markReadyBtn = orderCard.findViewById(R.id.markReadyButton);
            if (markReadyBtn != null) {
                markReadyBtn.setVisibility(View.GONE);
            }

            if (orderCard.getId() == R.id.orderCard1) {
                statusLabel1.setText("READY");
                statusLabel1.setTextColor(ContextCompat.getColor(this, R.color.orange));
            } else if (orderCard.getId() == R.id.orderCard2) {
                statusLabel2.setText("READY");
                statusLabel2.setTextColor(ContextCompat.getColor(this, R.color.primary));
            }

            Toast.makeText(this, "Order marked as ready", Toast.LENGTH_SHORT).show();
        }
    }
}