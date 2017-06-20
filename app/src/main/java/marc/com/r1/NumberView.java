package marc.com.r1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/6/20
 * Time: 11:02
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class NumberView extends LinearLayout {

	private ImageButton minus,plus;

	private EditText number;

	private int miusResourceId,plusResourceId;

	public NumberView(Context context) {
		this(context,null);
	}

	public NumberView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public NumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LayoutInflater.from(context).inflate(R.layout.numberview,this,true);

		minus = (ImageButton)findViewById(R.id.minus);
		plus = (ImageButton)findViewById(R.id.plus);
		number = (EditText)findViewById(R.id.number);

		TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.NumberView);
		miusResourceId = array.getResourceId(R.styleable.NumberView_mius_background,R.drawable.minus);
		plusResourceId = array.getResourceId(R.styleable.NumberView_plus_background,R.drawable.plus);
		array.recycle();

		Drawable d = getResources().getDrawable(miusResourceId,null);

//		minus.setImageBitmap();


		minus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int count = Integer.valueOf(number.getText().toString());
				if(count > 0) {
					count--;
					number.setText(count+"");
				}

			}
		});

		plus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int count = Integer.valueOf(number.getText().toString());
				count++;
				number.setText(count+"");
			}
		});
	}


}
