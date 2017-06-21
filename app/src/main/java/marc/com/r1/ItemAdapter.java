package marc.com.r1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hannahxian on 2017/6/21.
 * Version:1.0
 * Email:wangchengda1990@gamil.com
 * Description:
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private Context context;

    private List<CheckBean> list;

    private ItemAdapter.OnItemClickListener onItemClickListener;

    private ItemAdapter.OnItemRemoveListner OnItemRemoveListner;

    private OnNumberChangeListner onNumberChangeListner;

    public ItemAdapter(android.content.Context context, List<CheckBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_adapter,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.price.setText(list.get(position).getPrice());
        holder.check.setChecked(list.get(position).getCheck());
        holder.count.setText(list.get(position).getCount()+"");

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

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onNumberChangeListner!=null)
                    onNumberChangeListner.OnNumberPlusListener(holder,v,position);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onNumberChangeListner!=null)
                    onNumberChangeListner.OnNumberMinusListener(holder,v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public void setOnItemClickListener(ItemAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public void setOnItemRemoveListner(ItemAdapter.OnItemRemoveListner onItemRemoveListner) {
        this.OnItemRemoveListner = onItemRemoveListner;
    }

    public void setOnNumberChangeListner(OnNumberChangeListner onNumberChangeListner) {
        this.onNumberChangeListner = onNumberChangeListner;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox check;
        TextView price;
        Button remove;
        EditText count;
        ImageButton plus,minus;
        public MyViewHolder(View itemView) {
            super(itemView);
            check = (CheckBox)itemView.findViewById(R.id.adapter_check);
            price = (TextView)itemView.findViewById(R.id.adapter_price);
            remove = (Button)itemView.findViewById(R.id.adapter_remove);
            count = (EditText)itemView.findViewById(R.id.adapter_count);
            plus = (ImageButton)itemView.findViewById(R.id.adapter_plus);
            minus = (ImageButton)itemView.findViewById(R.id.adapter_minus);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, ItemAdapter.MyViewHolder holder, int position);
    }

    public interface OnItemRemoveListner {
        void OnItemRemoveListner(View view,int position);
    }

    public interface  OnNumberChangeListner{
        void OnNumberPlusListener(MyViewHolder holder,View view,int position);
        void OnNumberMinusListener(MyViewHolder holder,View view,int position);
    }
}
