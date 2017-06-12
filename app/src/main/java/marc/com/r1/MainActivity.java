package marc.com.r1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	private CheckBox checkBox;

	private List<CheckBean> list;

	private int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView)findViewById(R.id.myview);
		checkBox = (CheckBox)findViewById(R.id.checkall);

		initData();

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		final MyAdapter my = new MyAdapter(MainActivity.this,list);
		my.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
			@Override
			public void OnItemClick(View view, MyAdapter.TViewHolder holder, int position) {
				holder.check.setChecked(!holder.check.isChecked());
				list.get(position).setCheck(holder.check.isChecked());
				if (holder.check.isChecked()&&(count<list.size())){
					count++;
				}else if(!holder.check.isChecked() && (count>0)){
					count--;
				}
				if(count == list.size()){
					checkBox.setChecked(true);
				}else
					checkBox.setChecked(false);
			}
		});
		recyclerView.setAdapter(my);


		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for (CheckBean checkBean : list) {
					checkBean.setCheck(checkBox.isChecked());
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
