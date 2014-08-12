package com.example.test_wcf_service;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static boolean TESTING = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			final TextView te1 = (TextView) rootView.findViewById(R.id.CustomerId);
			final TextView te2 = (TextView) rootView.findViewById(R.id.Product);
			final TextView te3 = (TextView) rootView.findViewById(R.id.Expiration);
			final EditText te  = (EditText) rootView.findViewById(R.id.serverText);
			Button button = (Button) rootView.findViewById(R.id.Submit);
			
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String data = te.getText().toString();
					new GetUserData(te1, te2, te3).execute(data);
				}
			});
			
			new GetAllUsers(te1, te2, te3).execute();
			
			return rootView;
		}
	}
}



	
