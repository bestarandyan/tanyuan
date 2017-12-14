/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tanyuan.app.ui;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.tanyuan.app.DemoHelper;
import com.tanyuan.app.R;
import com.hyphenate.exceptions.HyphenateException;
import com.tanyuan.app.request.RegisterUserInfoRequest;
import com.tanyuan.app.response.RegisterResponse;
import com.tanyuan.network.interfaces.RequestInterface;
import com.tanyuan.network.request.RequestManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * register screen
 * 
 */
public class RegisterActivity extends BaseActivity {
	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.em_activity_register);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		confirmPwdEditText = (EditText) findViewById(R.id.confirm_password);
	}

	public void register(View view) {
		final String username = userNameEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		String confirm_pwd = confirmPwdEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
			userNameEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(confirm_pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
			confirmPwdEditText.requestFocus();
			return;
		} else if (!pwd.equals(confirm_pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Two_input_password), Toast.LENGTH_SHORT).show();
			return;
		}

		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
			final ProgressDialog pd = new ProgressDialog(this);
			pd.setMessage(getResources().getString(R.string.Is_the_registered));
			pd.show();

			new Thread(new Runnable() {
				public void run() {
					try {
						// call method in SDK
						EMClient.getInstance().createAccount(username, pwd);
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								// save current user
								DemoHelper.getInstance().setCurrentUserName(username);
								Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
								registerRequest();
							}
						});
					} catch (final HyphenateException e) {
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								int errorCode=e.getErrorCode();
								if(errorCode==EMError.NETWORK_ERROR){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.USER_ALREADY_EXIST){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.USER_AUTHENTICATION_FAILED){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.USER_ILLEGAL_ARGUMENT){
								    Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name),Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				}
			}).start();

		}
	}

	private void registerRequest(){
		RegisterUserInfoRequest request =  new RegisterUserInfoRequest(getApplicationContext());
		request.setAvatar("http://qiuqiu-photo.oss-cn-hangzhou.aliyuncs.com/user/thumbnail/e87f81dde76843c98c53dda5c0267f8d.jpg");
		request.setSmall_avatar("http://qiuqiu-photo.oss-cn-hangzhou.aliyuncs.com/user/thumbnail/e87f81dde76843c98c53dda5c0267f8d.jpg");
		request.setBirthday("1990-01-01");
		request.setGender("1");
		request.setNickname("bestar1");
		request.setType("phone");
		request.setInvitation_code("");
		request.setPhone(userNameEditText.getText().toString());
		request.setPassword(passwordEditText.getText().toString());
		request.setOpen_id("");
		request.setLat("0");
		request.setLon("0");
		RequestManager.builder()
				.requestByPost(request)
				.setResponse(RegisterResponse.class)
				.setRequestListener(new RequestInterface<RegisterResponse>() {
					@Override
					public void onReceivedData(RegisterResponse response) {
						Log.e("注册结果",response.getMessage() + response.getResultCode());
					}

					@Override
					public void onErrorData(RegisterResponse response) {

					}
				});
	}

	public void back(View view) {
		finish();
	}

}
