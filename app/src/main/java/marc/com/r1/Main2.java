package marc.com.r1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannahxian on 2017/6/21.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class Main2 extends AppCompatActivity {

    private RecyclerView recyclerView;

    private CheckBox checkAll;

    private List<CheckBean> list;

    private int count = 0;

    private int priceCount = 0;

    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.myview);
        checkAll = (CheckBox)findViewById(R.id.checkall);
        totalPrice = (TextView)findViewById(R.id.price);

        initData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ItemAdapter my = new ItemAdapter(Main2.this,list);
        my.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, ItemAdapter.MyViewHolder holder, int position) {
                holder.check.setChecked(!holder.check.isChecked());

                CheckBean cb = list.get(position);

                cb.setCheck(holder.check.isChecked());
                cb.setCount(Integer.valueOf(holder.count.getText().toString()));

                if (holder.check.isChecked()&&(count<list.size())){
                    count++;
                }else if(!holder.check.isChecked() && (count>0)){
                    count--;
                }

                if(count == list.size()){
                    checkAll.setChecked(true);
                }else
                    checkAll.setChecked(false);

                calculate();
            }
        });
        my.setOnItemRemoveListner(new ItemAdapter.OnItemRemoveListner() {
            @Override
            public void OnItemRemoveListner(View view, int position) {
                list.remove(position);
                my.notifyDataSetChanged();
            }
        });

        my.setOnNumberChangeListner(new ItemAdapter.OnNumberChangeListner() {
            @Override
            public void OnNumberPlusListener(ItemAdapter.MyViewHolder holder, View view, int position) {
                int c = Integer.valueOf(holder.count.getText().toString());
                c++;
                holder.count.setText(c+ "");
                list.get(position).setCount(c);
                calculate();
            }

            @Override
            public void OnNumberMinusListener(ItemAdapter.MyViewHolder holder, View view, int position) {
                int c = Integer.valueOf(holder.count.getText().toString());
                if(c > 0)
                    c--;
                holder.count.setText(c+ "");
                list.get(position).setCount(c);
                calculate();
            }
        });


        recyclerView.setAdapter(my);


        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CheckBean checkBean : list) {
                    checkBean.setCheck(checkAll.isChecked());
                }
                calculate();
                my.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();

        for (int i=0;i<10;i++){
            list.add(new CheckBean(""+i,false,i));
        }
    }


    private void calculate(){
        double total = 0.0;
        for (CheckBean checkBean : list) {
            if(checkBean.getCheck())
                total += checkBean.getCount()*Double.valueOf(checkBean.getPrice());
        }

        totalPrice.setText(""+total);
    }
}

