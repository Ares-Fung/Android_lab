package ares_android.lab4;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static ares_android.lab4.MainActivity.rc_id;

public class details extends AppCompatActivity {
    private Merchandise tempMerchandise;
    private MyDynamicReceiver myDynamicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Intent intent = new Intent();
        intent.setClass(details.this, MainActivity.class);

        final ImageButton backbutton = (ImageButton) findViewById(R.id.back_button);
        backbutton.setImageResource(R.mipmap.back);
        backbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, 0);
                finish();
            }
        });

        rc_id++;
        IntentFilter dynamic_filter = new IntentFilter();
        dynamic_filter.addAction("com.lab4.MyDynamicFilter");
        myDynamicReceiver = new MyDynamicReceiver();
        registerReceiver(myDynamicReceiver, dynamic_filter);

        tempMerchandise = (Merchandise)getIntent().getSerializableExtra("Merchandise");
        final TextView Name = (TextView) findViewById(R.id.detail_name);
        Name.setText(tempMerchandise.getName());
        final TextView Price = (TextView) findViewById(R.id.detail_price);
        Price.setText("¥"+Double.toString(tempMerchandise.getPrice()));
        final TextView Info = (TextView) findViewById(R.id.detail_info);
        Info.setText(tempMerchandise.getInfo());

        final ImageView detail_image = (ImageView) findViewById(R.id.detail_image);
        detail_image.setImageResource(tempMerchandise.getPicname());

        final ImageView detail_star = (ImageView) findViewById(R.id.detail_star);
        if(!tempMerchandise.getMark())
            detail_star.setImageResource(R.mipmap.empty_star);
        else
            detail_star.setImageResource(R.mipmap.full_star);
        detail_star.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempMerchandise.setMark(!tempMerchandise.getMark());
                if(!tempMerchandise.getMark())
                    detail_star.setImageResource(R.mipmap.empty_star);
                else
                    detail_star.setImageResource(R.mipmap.full_star);
            }
        });

        final ImageButton shoppingcart = (ImageButton) findViewById(R.id.shoppingcart);
        shoppingcart.setImageResource(R.mipmap.shoplist);
        shoppingcart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(details.this, "商品已添加到购物车",Toast.LENGTH_SHORT).show();
                intent.putExtra("AddItem",tempMerchandise);
                Intent intentBroadcast = new Intent("com.lab4.MyDynamicFilter");
                intentBroadcast.putExtra("Merchandise", tempMerchandise);
                sendBroadcast(intentBroadcast);
            }
        });
    }

}
