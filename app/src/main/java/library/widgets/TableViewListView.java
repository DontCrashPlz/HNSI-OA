package library.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.approval.widget.TableViewsActivity;
import com.hnsi.oa.hnsi_oa.application.beans.ApprovalWidgetEntity;

/**
 * Created by Zheng on 2018/1/25.
 */

public class TableViewListView extends LinearLayout {

    public TableViewListView(Context context) {
        super(context);
    }

    public TableViewListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TableViewListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), 150);
    }

    public void setupTableView(final ApprovalWidgetEntity entity){
        int tableSize= entity.getTableData().size();
        Log.e("tableSize", ""+tableSize);
        for (int i= 0; i< tableSize; i++){
            final int index= i;
            View view= LayoutInflater.from(getContext()).inflate(R.layout.item_tableview, this, false);
            ((TextView)view.findViewById(R.id.item_tableview_name)).setText(entity.getLabel()+ "(第" + (index+1) + "张)");
            view.findViewById(R.id.item_rly_tableview_panel).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getContext(), TableViewsActivity.class);
                    intent.putExtra("tableInfo", entity);
                    intent.putExtra("tableIndex", index);
                    getContext().startActivity(intent);
                }
            });
            addView(view);

            LinearLayout.LayoutParams params0=
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, 1);

            View devideView= new View(getContext());
            devideView.setLayoutParams(params0);
            devideView.setBackgroundColor(Color.rgb(173,173,173));
            if (i< (tableSize-1)){
                addView(devideView);
            }
        }
    }

}
