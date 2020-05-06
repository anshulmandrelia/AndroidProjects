package e.welcome.imagelabeling;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.ml.vision.common.FirebaseVisionImage;

public class MainActivity extends AppCompatActivity {
    FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
