package com.tm109.foodconnect.fragments.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.tm109.foodconnect.R;
import com.tm109.foodconnect.helper.AppConstant;
import com.tm109.foodconnect.helper.BaseFragment;
import com.tm109.foodconnect.helper.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.image_slider)
    SliderView image_slider;
    @BindView(R.id.rec_ngo_list)
    RecyclerView rec_ngo_list;
    @BindView(R.id.txt_user_name)
    TextView txt_user_name;
    @BindView(R.id.txt_list_nm)
    TextView txt_list_nm;
    ArrayList<Integer> sliderlist;
    ArrayList<String> lgoList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        sliderlist = new ArrayList<>();
        lgoList = new ArrayList<>();

        txt_user_name.setText(SecurePreferences.getStringPreference(activity,AppConstant.USER_NAME));
        sliderlist.add(R.drawable.slider_1);
        sliderlist.add(R.drawable.slider_2);
        sliderlist.add(R.drawable.slider_3);
        sliderlist.add(R.drawable.slider_4);
        sliderlist.add(R.drawable.slider_5);

        image_slider.setScrollTimeInSec(6);
        image_slider.setIndicatorUnselectedColor(Color.WHITE);
        image_slider.setSliderAdapter(new SliderAdapter());
        rec_ngo_list.setLayoutManager(new LinearLayoutManager(activity));
        rec_ngo_list.setAdapter(new NgoListAdapter());
    }

    public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
            return new SliderAdapterVH(inflate);
        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
            Integer model = sliderlist.get(position);
            viewHolder.iv_auto_image_slider.setImageResource(model);
//            Glide.with(viewHolder.itemView)
//                    .load(AppConstant.IMAGE_URL + model.getSliderImage()).into(viewHolder.imageViewBackground);
        }

        @Override
        public int getCount() {
            return sliderlist.size();
        }

        class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
            @BindView(R.id.iv_auto_image_slider)
            ImageView iv_auto_image_slider;
            public SliderAdapterVH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    class NgoListAdapter extends RecyclerView.Adapter<NgoListAdapter.NgoListHolder> {
        @NonNull
        @Override
        public NgoListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.row_ngo_list, parent, false);
            return new NgoListHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NgoListHolder holder, int position) {
//            String model = lgoList.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadFragmentFull(new NgoDetailFragment(), "NgoDetailFragment");
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class NgoListHolder extends RecyclerView.ViewHolder {
            public NgoListHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    @Override
    public void onBack() {
        getParentFragmentManager().popBackStack();
    }
}
