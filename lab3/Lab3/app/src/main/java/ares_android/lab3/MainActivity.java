package ares_android.lab3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public  static String[] name = new String[] {
            "Arla Milk", "Borggreve", "Devondale Milk", "Enchated Forest",
            "Ferrero Rocher", "Kindle Oasis", "Lindt", "Maltesers",
            "Mcvities' 饼干", "waitrose 早餐麦片"
    };
    public  static double[] price = new double[]{
            59.00, 28.90, 79.00, 5.00, 132.59, 2399.00, 139.43, 141.43, 14.90, 179.00
    };
    public  static String[] info = new String[] {
            "产地 德国", "重量 640g", "产地 澳大利亚", "作者 Johanna Basford",
            "重量 300g", "版本 8GB", "重量 249g", "重量 118g",
            "产地 英国", "重量 2Kg"
    };
    public static int[] picname = new int[] {
            R.mipmap.arla, R.mipmap.borggreve, R.mipmap.devondale, R.mipmap.enchatedforest,
            R.mipmap.ferrero, R.mipmap.kindle, R.mipmap.lindt, R.mipmap.maltesers,
            R.mipmap.mcvitie, R.mipmap.waitrose
    };

    public static String current = "RecycleView";
    private MyAdapter mAdapter1;
    private MyAdapter2 mAdapter2;
    Context mcontext;
    public static ArrayList<Merchandise> merchandisedata = new ArrayList<>();
    public static ArrayList<Merchandise> shoplistdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoplistdata_initialization();
        merchandisedata = getData();
        mcontext = this;

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter1 = new MyAdapter(merchandisedata);

        mAdapter1.setmOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, details.class);
                Merchandise senddata = new Merchandise(merchandisedata.get(position).getName(),merchandisedata.get(position).getPrice(),
                                                       merchandisedata.get(position).getInfo(), merchandisedata.get(position).getPicname(),
                                                       merchandisedata.get(position).getMark());
                intent.putExtra("Merchandise", senddata);
                startActivityForResult(intent, 0);
            }

            @Override
            public boolean onLongClick(int position) {
                merchandisedata.remove(position);
                mAdapter1.updateData(merchandisedata);
                Toast.makeText(MainActivity.this, "第"+Integer.toString(position+1)+"个商品已被移除",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mRecyclerView.setAdapter(mAdapter1);
        //mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        final ListView mListView = (ListView) findViewById(R.id.shoppinglist);
        mAdapter2 = new MyAdapter2(this,getshoplistData());
        mListView.setAdapter(mAdapter2);
        mListView.setVisibility(View.GONE);

        if(current=="RecycleView") {
            mListView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        };
        if(current=="ListView") {
            mRecyclerView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        };

        final FloatingActionButton mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current=="RecycleView") {
                    current = "ListView";
                    mRecyclerView.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
                    mFloatingActionButton.setImageResource(R.mipmap.mainpage);
                }
                else {
                    current = "RecycleView";
                    mListView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mFloatingActionButton.setImageResource(R.mipmap.shoplist);
                };
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, details.class);
                    Merchandise senddata = new Merchandise(shoplistdata.get(i).getName(),shoplistdata.get(i).getPrice(),
                                                           shoplistdata.get(i).getInfo(), shoplistdata.get(i).getPicname() ,
                                                           shoplistdata.get(i).getMark());
                    intent.putExtra("Merchandise", senddata);
                    startActivityForResult(intent, 0);
                }
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                if(position>0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    AlertDialog dialog = builder.create();
                    builder.setTitle("移除商品");
                    builder.setMessage("从购物车移除"+shoplistdata.get(position).getName()+"？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            shoplistdata.remove(position);
                            mAdapter2.updateData(shoplistdata);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.setCancelable(true);
                    builder.show();
                }
                return true;
            }
        });

    }

    private ArrayList<Merchandise> getData() {
        if(merchandisedata.isEmpty()) {
            for (int i = 0; i <name.length; i++) {
                Merchandise tempMerchandise = new Merchandise(name[i], price[i], info[i], picname[i], false);
                merchandisedata.add(tempMerchandise);
            }
        }
        return merchandisedata;
    }

    private void shoplistdata_initialization() {
        if(shoplistdata.isEmpty()) {
            Merchandise tempMerchandise = new Merchandise("购物车", 0, "", 0, false);
            shoplistdata.add(tempMerchandise);
        }
    }

    private ArrayList<Merchandise> getshoplistData() {
        return shoplistdata;
    }


}
