package org.smartregister.chw.anc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.smartregister.chw.anc.contract.AncMemberProfileContract;
import org.smartregister.chw.anc.presenter.AncMemberProfilePresenter;
import org.smartregister.chw.anc.util.MemberObject;
import org.smartregister.chw.opensrp_chw_anc.R;
import org.smartregister.helper.ImageRenderHelper;
import org.smartregister.view.activity.BaseProfileActivity;

import timber.log.Timber;

public class BaseAncMemberProfileActivity extends BaseProfileActivity implements AncMemberProfileContract.View {
    private boolean isFromFamilyRegister = false;
    private TextView textViewTitle, text_view_anc_member_name;
    protected MemberObject MEMBER_OBJECT;

    public static void startMe(Activity activity, MemberObject memberObject) {
        Intent intent = new Intent(activity, BaseAncMemberProfileActivity.class);
        intent.putExtra("MemberObject", memberObject);
        activity.startActivity(intent);
    }

    protected void registerPresenter() {
        presenter = new AncMemberProfilePresenter(this, MEMBER_OBJECT);
    }

    @Override
    protected void onCreation() {
        setContentView(R.layout.activity_anc_member_profile);
        Toolbar toolbar = findViewById(R.id.collapsing_toolbar);
        textViewTitle = toolbar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            MEMBER_OBJECT = (MemberObject) getIntent().getSerializableExtra("MemberObject");
        }

        registerPresenter();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
            upArrow.setColorFilter(getResources().getColor(R.color.text_blue), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appBarLayout = findViewById(R.id.collapsing_toolbar_appbarlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setOutlineProvider(null);
        }
        imageRenderHelper = new ImageRenderHelper(this);

        initializePresenter();

        setupViews();
        setUpToolbar();

    }

    @Override
    protected void setupViews() {
        text_view_anc_member_name = findViewById(R.id.text_view_anc_member_name);
    }

    @Override
    protected void onResumption() {
        Timber.v("Empty onResumption");
    }

    @Override
    public void onClick(View v) {
        Timber.v("Empty onClick");
    }

    private void setUpToolbar() {
        if (isFromFamilyRegister) {
            textViewTitle.setText(getString(R.string.return_to_family_members));
        } else {
            textViewTitle.setText(getString(R.string.return_to_all_anc_women));
        }
    }

    @Override
    protected void initializePresenter() {
        registerPresenter();
        fetchProfileData();
    }

    @Override
    public void setMemberName(String memberName) {
        text_view_anc_member_name.setText(memberName);

    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {
        presenter().fetchProfileData();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public AncMemberProfileContract.Presenter presenter() {
        return (AncMemberProfileContract.Presenter) presenter;
    }

}