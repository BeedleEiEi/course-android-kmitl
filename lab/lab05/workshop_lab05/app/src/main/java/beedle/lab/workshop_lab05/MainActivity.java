package beedle.lab.workshop_lab05;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import beedle.lab.workshop_lab05.fragment.FirstFragment;
import beedle.lab.workshop_lab05.fragment.MainFragment;
import beedle.lab.workshop_lab05.fragment.SecondFragment;
import beedle.lab.workshop_lab05.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        //สร้าง Fragment newInstance เพื่อใส่ Message เข้าไป


        Button accessFragment = (Button) findViewById(R.id.accessFragment);
        Button accessFrstPage = (Button) findViewById(R.id.accessFragmentFirst);
        Button accessSecondPage = (Button) findViewById(R.id.accessFragmentSecond);
        Button accessThirdPage = (Button) findViewById(R.id.accessFragmentThird);
        Button accesSecondInFirst = (Button) findViewById(R.id.fragmentInFirst);


        if(savedInstanceState == null) {
            initialFragment();
        }

        accessFrstPage.setOnClickListener(this);
        accessSecondPage.setOnClickListener(this);
        accessThirdPage.setOnClickListener(this);

    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new MainFragment().newInstance("Access from Activity")).commit();
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        if (R.id.accessFragmentFirst == v.getId()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new FirstFragment().newInstance("FirstPage"))
                    .addToBackStack(null)
                    .commit();

        }
        else if (R.id.accessFragmentSecond == v.getId()){
            viewFragment(new SecondFragment().newInstance("Second"));
        }
        else if (R.id.accessFragmentThird == v.getId()){
            viewFragment(new ThirdFragment().newInstance("Third"));
        }
    }
}
