package calc.mydukan.com.calculatortool.Utils;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rojesharunkumar on 11/11/17.
 */

public class FireBaseUtils {

    public static String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
