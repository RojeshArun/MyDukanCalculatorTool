package calc.mydukan.com.calculatortool.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import calc.mydukan.com.calculatortool.Helper.DeviceHelper;
import calc.mydukan.com.calculatortool.R;
import calc.mydukan.com.calculatortool.models.Device;

/**
 * Created by rojesharunkumar on 20/10/17.
 */

public class DevicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Device> deviceList;

    public DevicesAdapter() {
        deviceList = DeviceHelper.getInstance().getDeviceList();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.calculaor_header, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DeviceViewHolder deviceViewHolder = (DeviceViewHolder) holder;
        Device device = deviceList.get(position);

        deviceViewHolder.txtModel.setText(device.getModel());
        deviceViewHolder.txtPercent.setText(device.getPercent());
        deviceViewHolder.txtValue.setText(device.getValue());
        deviceViewHolder.txtIncentiveAmount.setText(device.getIncentiveAmount());
        deviceViewHolder.txtDp.setText(device.getDp());
        deviceViewHolder.txtQuantity.setText(device.getQuantity());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView txtModel;
        TextView txtTarget;
        TextView txtQuantity;
        TextView txtDp;
        TextView txtIncentiveAmount;
        TextView txtValue;
        TextView txtPercent;


        public DeviceViewHolder(View itemView) {
            super(itemView);
            txtModel = itemView.findViewById(R.id.lbl_model);
            txtTarget = itemView.findViewById(R.id.lbl_target);
            txtQuantity = itemView.findViewById(R.id.lbl_qty);
            txtDp = itemView.findViewById(R.id.lbl_dp_withvat);
            txtIncentiveAmount = itemView.findViewById(R.id.lbl_incentiveamount);
            txtValue = itemView.findViewById(R.id.lbl_value);
            txtPercent = itemView.findViewById(R.id.lbl_schemeperhandset);
        }
    }
}
