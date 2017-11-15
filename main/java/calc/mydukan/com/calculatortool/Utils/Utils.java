package calc.mydukan.com.calculatortool.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.text.DateFormatSymbols;

/**
 * Created by rojesharunkumar on 16/10/17.
 */

public class Utils {

    public static String getMonthString(int mnt){
        return new DateFormatSymbols().getMonths()[mnt];
    }

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
