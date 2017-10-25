package com.gym.shancai.activity;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.gym.shancai.R;
import com.gym.shancai.customview.ViewPagerFixed;

import java.util.Arrays;
import java.util.List;

import me.relex.photodraweeview.OnViewTapListener;
import me.relex.photodraweeview.PhotoDraweeView;


public class BasePhotoviewActivity extends Activity  {
    private ViewPagerFixed mViewPager;
    private List<String> mImgUrls;
    private PhotoViewAdapter mAdapter;
    int SCREEN_WIDTH,SCREEN_HEIGHT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_photo_viewpager);
        setupView();
        setupData();
    }

    private void setupView(){
        mViewPager = (ViewPagerFixed) findViewById(R.id.view_pager);
    }

    private void setupData(){
        Fresco.initialize(this);

        int mCurrentUrl = getIntent().getIntExtra("position",0);
        mImgUrls = Arrays.asList(getIntent().getStringArrayExtra("images"));
        mAdapter = new PhotoViewAdapter();
        mViewPager.setAdapter(mAdapter);
        //设置当前需要显示的图片
        mViewPager.setCurrentItem(mCurrentUrl);
    }



    class PhotoViewAdapter extends PagerAdapter implements  OnViewTapListener {

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            View view = View.inflate(BasePhotoviewActivity.this,
                    R.layout.item_photo_view,null);
            final PhotoDraweeView ivPhoto = (PhotoDraweeView) view.findViewById(R.id.photo);
            ivPhoto.setOnViewTapListener(this);

            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(Uri.parse( mImgUrls.get(position)));
            controller.setOldController(ivPhoto.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null) {
                        return;
                    }
                    ivPhoto.update(imageInfo.getWidth(), imageInfo.getHeight());
                }
            });
            ivPhoto.setController(controller.build());

            try {
                viewGroup.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return view;
//            container.addView(view);
//            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return mImgUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void onViewTap(View view, float x, float y) {
            finish();
        }
    }

}
