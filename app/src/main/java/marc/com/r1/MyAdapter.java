package marc.com.r1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/6/12
 * Time: 10:00
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.TViewHolder>{

	private Context context;

	private List<CheckBean> list;

	private OnItemClickListener onItemClickListener;

	private OnItemRemoveListner OnItemRemoveListner;

	public MyAdapter(Context context, List<CheckBean> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public MyAdapter.TViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View v = LayoutInflater.from(context).inflate(R.layout.adapter_view,parent,false);
		MyAdapter.TViewHolder th = new MyAdapter.TViewHolder(v);

		return th;
	}

	@Override
	public void onBindViewHolder(final MyAdapter.TViewHolder holder, final int position) {
		holder.price.setText(list.get(position).getPrice());
		holder.check.setChecked(list.get(position).getCheck());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onItemClickListener.OnItemClick(v,holder,position);
				notifyItemChanged(position);
			}
		});

		holder.remove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(OnItemRemoveListner !=null){
					OnItemRemoveListner.OnItemRemoveListner(v,position);
				}
			}
		});

		holder.check.setOnClickListener(null);
		/*holder.check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onItemClickListener.OnItemClick(v,holder,position);
				notifyItemChanged(position);
			}
		});*/
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	public void checkAll(){
		for (int i=0;i<this.getItemCount();i++){

		}
	}

	class TViewHolder extends RecyclerView.ViewHolder{
		CheckBox check;
		TextView price;
		Button remove;
		public TViewHolder(View itemView) {
			super(itemView);
			check = (CheckBox)itemView.findViewById(R.id.checkalla);
			price = (TextView)itemView.findViewById(R.id.pricea);
			remove = (Button)itemView.findViewById(R.id.remove);
		}
	}

	public interface OnItemClickListener {
		void OnItemClick(View view, MyAdapter.TViewHolder holder, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener){
		this.onItemClickListener = listener;
	}

	public interface OnItemRemoveListner {
		void OnItemRemoveListner(View view,int position);
	}

	public void setOnItemRemoveListner(OnItemRemoveListner onItemRemoveListner) {
		this.OnItemRemoveListner = onItemRemoveListner;
	}
}
