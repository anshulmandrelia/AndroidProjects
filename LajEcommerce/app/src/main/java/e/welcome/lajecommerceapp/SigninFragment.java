package e.welcome.lajecommerceapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {


    public SigninFragment() {
        // Required empty public constructor
    }
     private TextView donthaveaaacoouunt;
    private FrameLayout parentframeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        donthaveaaacoouunt=view.findViewById(R.id.donthaveanaccount);
        parentframeLayout=getActivity().findViewById(R.id.fragmenthold);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        donthaveaaacoouunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignupFragment());
            }
        });
    }
    private void setFragment(Fragment fragment)
        {
            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().
                    beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_out_from_left);
            fragmentTransaction.replace(parentframeLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }

