package ares_android.lab2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static ares_android.lab2.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout myrootlayout = (LinearLayout)findViewById(R.id.traceroute_rootview);
        myrootlayout.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.traceroute_rootview:
                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        break;
                }

            }
        });

        final RadioGroup myradiogroup=(RadioGroup)findViewById(R.id.radioGroup);
        myradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkButton=(RadioButton) findViewById(checkedId);
                Snackbar snackbar = Snackbar.make(findViewById(R.id.main),"您选择了"+ checkButton.getText().toString(), Toast.LENGTH_SHORT);
                snackbar.setAction("确定",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();

            }
        });

        ImageView myimage = (ImageView)findViewById(R.id.imageView);
        myimage.setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               AlertDialog dialog = builder.create();
               final String [] Items={"拍摄","从相册选择"};
               builder.setTitle("上传头像");
               builder.setItems(Items, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface d, int i) {
                       Toast.makeText(MainActivity.this, "您选择了["+Items[i]+"]",Toast.LENGTH_SHORT).show();
                   }
               });
               builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                   @Override
                   public void onClick(DialogInterface d, int i) {
                       Toast.makeText(MainActivity.this, "您选择了[取消]",Toast.LENGTH_SHORT).show();
                   }
               });
                builder.setCancelable(true);
                builder.show();
            }
        });

        final EditText usernum = (EditText) findViewById(R.id.editText2);
        final EditText password = (EditText) findViewById(R.id.editText3);
        final TextInputLayout usernumlayout = (TextInputLayout) findViewById(R.id.textinputlayout);
        final TextInputLayout passwordlayout = (TextInputLayout) findViewById(R.id.textinputlayout2);

        Button btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkpassword = true;
                if(TextUtils.isEmpty(usernum.getText().toString())) {
                    usernumlayout.setErrorEnabled(true);
                    usernumlayout.setError("学号不能为空");
                    passwordlayout.setError("");
                    passwordlayout.setErrorEnabled(false);
                    checkpassword = false;
                }
                else {
                    usernumlayout.setError("");
                    usernumlayout.setErrorEnabled(false);
                    if (TextUtils.isEmpty(password.getText().toString())) {
                        passwordlayout.setErrorEnabled(true);
                        passwordlayout.setError("密码不能为空");
                        checkpassword = false;
                    }
                    else {
                        passwordlayout.setError("");
                        passwordlayout.setErrorEnabled(false);
                    }
                }

                if(checkpassword) {
                    usernumlayout.setError("");
                    usernumlayout.setErrorEnabled(false);
                    passwordlayout.setError("");
                    passwordlayout.setErrorEnabled(false);
                    boolean correctness = false;
                    if (usernum.getText().toString().equals("123456"))
                        if (password.getText().toString().equals("6666"))
                            correctness = true;
                            if(!correctness) {
                                Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "学号或密码错误", Toast.LENGTH_SHORT);
                                snackbar.setAction("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                snackbar.show();
                            }
                            else {
                                Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "登录成功", Toast.LENGTH_SHORT);
                                snackbar.setAction("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                snackbar.show();
                            }
                }

            }
        });

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedButtonid = myradiogroup.getCheckedRadioButtonId();
                RadioButton checkButton=(RadioButton) findViewById(checkedButtonid);
                final String words = "注册功能尚未启用";
                if(checkButton.getText().toString().equals("学生")) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main),checkButton.getText().toString()+ words, Toast.LENGTH_SHORT);
                    snackbar.setAction("确定",new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "Snackbar的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                        }
                    });
                    snackbar.show();
                }
                if(checkButton.getText().toString().equals("教职工")) {
                    Toast.makeText(MainActivity.this, checkButton.getText().toString()+words,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
