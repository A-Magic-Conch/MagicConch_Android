package com.android.magicconch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.imgBtn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // 로그인 성공
            String idToken = account.getIdToken();

            // 사용자 정보를 SharedPreferences에 저장
            saveUserInfo(account.getDisplayName(), account.getEmail());
            Log.d(TAG, "User info saved: " + account.getDisplayName() + ", " + account.getEmail());

            // MainPage(또는 대상 액티비티)로 이동
            Intent intent = new Intent(this, MainPage.class); // MainPageActivity는 대상 액티비티의 이름
            startActivity(intent);
            finish(); // 현재 액티비티를 종료하여 뒤로 가기 버튼을 누르면 로그인 화면이 나타나지 않도록 함

        } catch (ApiException e) {
            // 로그인 실패
            Toast.makeText(getApplicationContext(),"로그인 실패.",Toast.LENGTH_SHORT).show();
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void saveUserInfo(String displayName, String email) {
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("display_name", displayName);
        editor.putString("email", email);
        editor.apply();
    }
}