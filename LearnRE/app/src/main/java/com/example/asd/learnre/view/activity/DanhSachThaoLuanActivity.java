package com.example.asd.learnre.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.asd.learnre.R;
import com.example.asd.learnre.databinding.ActivityDanhSachThaoLuanBinding;
import com.example.asd.learnre.model.obj.ThaoLuan;
import com.example.asd.learnre.model.obj.User;
import com.example.asd.learnre.view.adapter.DanhSachThaoLuanAdapter;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DanhSachThaoLuanActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private ActivityDanhSachThaoLuanBinding binding;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;

    private User user;

    private Firebase mFirebase;

    private int idBaiHoc;

    private List<ThaoLuan> data;
    private DanhSachThaoLuanAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        mFirebase=new Firebase("https://learre-2c1e1.firebaseio.com/");
        binding= DataBindingUtil.setContentView(this, R.layout.activity_danh_sach_thao_luan);

        idBaiHoc= getIntent().getIntExtra("idBaiHoc", 0);

        data=new ArrayList<>();

        binding.rvThaoLuan.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);
        binding.rvThaoLuan.setLayoutManager(mLayoutManager);

        adapter=new DanhSachThaoLuanAdapter(data);
        binding.rvThaoLuan.setAdapter(adapter);

        init();
        getData();

        if(user!=null && user.getPhotoUrl()!=null) {
            Picasso.with(this).load(user.getPhotoUrl()).into(binding.ivMyAvatar);
        }

    }

    private void init() {
        //
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        //
        mFirebaseAuth= FirebaseAuth.getInstance();
        mFirebaseUser= mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser==null) {
            Intent intent=new Intent(DanhSachThaoLuanActivity.this, DangNhapActivity.class);
            intent.putExtra("idBaiHoc", idBaiHoc);
            startActivity(intent);
            finish();
            return;
        }
        else {
            user= new User();
            user.setDisplayName(mFirebaseUser.getDisplayName());
            user.setPhotoUrl(mFirebaseUser.getPhotoUrl().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_thao_luan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.btnSignOut) {
            mFirebaseAuth.signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            Intent intent=new Intent(this, DangNhapActivity.class);
            intent.putExtra("idBaiHoc", idBaiHoc);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void clickSend(View view) {
        ThaoLuan thaoLuan=new ThaoLuan();
        thaoLuan.setUser(user);
        thaoLuan.setBinhLuan(binding.edComment.getText().toString());

        mFirebase.child("BinhLuan").child("Bai"+idBaiHoc).push().setValue(thaoLuan);
        binding.edComment.setText("");
    }

    public void getData() {
        mFirebase.child("BinhLuan").child("Bai" + idBaiHoc).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ThaoLuan thaoLuan= dataSnapshot.getValue(ThaoLuan.class);
                data.add(thaoLuan);
                adapter.notifyDataSetChanged();
                binding.rvThaoLuan.scrollToPosition(data.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
