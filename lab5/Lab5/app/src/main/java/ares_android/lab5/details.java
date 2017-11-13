package ares_android.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static ares_android.lab5.MainActivity.shoplistdata;

public class details extends AppCompatActivity {
    private Merchandise tempMerchandise;

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
                shoplistdata.add(tempMerchandise);
                Toast.makeText(details.this, "商品已添加到购物车",Toast.LENGTH_SHORT).show();
                Intent intentBroadcast = new Intent("com.lab5.MyDynamicFilter");
                intentBroadcast.putExtra("Merchandise", tempMerchandise);
                sendBroadcast(intentBroadcast);
                Intent intentBroadcast2 = new Intent("com.lab5.MyDynamicWidgetFilter");
                intentBroadcast2.putExtra("Merchandise", tempMerchandise);
                sendBroadcast(intentBroadcast2);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
