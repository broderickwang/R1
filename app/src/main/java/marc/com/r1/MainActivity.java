package marc.com.r1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	private CheckBox checkBox;

	private List<CheckBean> list;

	private int count = 0;

	private int priceCount = 0;

	private TextView price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView)findViewById(R.id.myview);
		checkBox = (CheckBox)findViewById(R.id.checkall);
		price = (TextView)findViewById(R.id.price);

		initData();

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		final MyAdapter my = new MyAdapter(MainActivity.this,list);
		my.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
			@Override
			public void OnItemClick(View view, MyAdapter.TViewHolder holder, int position) {
				holder.check.setChecked(!holder.check.isChecked());
				list.get(position).setCheck(holder.check.isChecked());

				if(price.getText()!=null){
					priceCount = Integer.valueOf(price.getText().toString());
				}

				if (holder.check.isChecked()&&(count<list.size())){
					count++;
					priceCount += Integer.valueOf(list.get(position).getPrice());
				}else if(!holder.check.isChecked() && (count>0)){
					count--;
					priceCount -= Integer.valueOf(list.get(position).getPrice());
				}
				price.setText(""+priceCount);
				if(count == list.size()){
					checkBox.setChecked(true);
				}else
					checkBox.setChecked(false);

			}
		});
		my.setOnItemRemoveListner(new MyAdapter.OnItemRemoveListner() {
			@Override
			public void OnItemRemoveListner(View view, int position) {
				list.remove(position);
				my.notifyDataSetChanged();
			}
		});
		recyclerView.setAdapter(my);


		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for (CheckBean checkBean : list) {
					checkBean.setCheck(checkBox.isChecked());
				}
				if(checkBox.isChecked()){
					priceCount = 0;
					for (CheckBean checkBean : list) {
						priceCount += Integer.valueOf(checkBean.getPrice());
					}
					price.setText(priceCount+"");
				}else{
					price.setText("0");
				}
				my.notifyDataSetChanged();
			}
		});
	}

	private void initData() {
		list = new ArrayList<>();

		for (int i=0;i<10;i++){
			list.add(new CheckBean(""+i,false));
		}
	}


}
