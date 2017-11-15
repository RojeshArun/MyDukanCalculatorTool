package calc.mydukan.com.calculatortool.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rojesharunkumar on 11/11/17.
 */

public class FireBaseUtils {
    private static DatabaseReference ref;

    public static String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static DatabaseReference getMySchemesURL() {
        if (ref == null) {
            ref = FirebaseDatabase.getInstance()
                    .getReferenceFromUrl("https://otsystem-64e61.firebaseio.com/MySchemes/"
                            + FireBaseUtils.getUid());
        }
        return ref;
    }


}
