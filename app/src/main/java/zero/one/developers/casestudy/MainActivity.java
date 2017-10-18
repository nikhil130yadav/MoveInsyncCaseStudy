package zero.one.developers.casestudy;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zero.one.developers.casestudy.fragment.RandomNumberFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RandomNumberFragment mRandomNumberFragment = RandomNumberFragment.newInstance();
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.root_container,mRandomNumberFragment);
        mFragmentTransaction.commit();


    }
}
