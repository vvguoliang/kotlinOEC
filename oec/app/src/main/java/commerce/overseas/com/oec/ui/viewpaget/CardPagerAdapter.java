package commerce.overseas.com.oec.ui.viewpaget;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import commerce.overseas.com.oec.R;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpage_adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view, container);
        CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view, ViewGroup container) {
        SimpleDraweeView new_image1 = view.findViewById(R.id.new_image1);
        SimpleDraweeView new_image2 = view.findViewById(R.id.new_image2);
        TextView new_text1 = view.findViewById(R.id.new_text1);
        TextView new_text2 = view.findViewById(R.id.new_text2);
        view.findViewById(R.id.new_lin1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(container.getContext(), "foot new_lin1", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.new_lin2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(container.getContext(), "foot new_lin2", Toast.LENGTH_SHORT).show();
            }
        });
        if (!TextUtils.isEmpty(item.getmTitleResource1())) {
            new_text1.setText(item.getmTitleResource1());
        } else {
            new_text1.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.getmTitleResource2())) {
            new_text2.setText(item.getmTitleResource2());
        } else {
            new_text2.setVisibility(View.GONE);
        }
        new_image1.setImageURI(item.getmTextResource1());
        new_image2.setImageURI(item.getmTextResource2());
    }

}
