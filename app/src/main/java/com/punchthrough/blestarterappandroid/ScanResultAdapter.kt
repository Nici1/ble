

package com.punchthrough.blestarterappandroid

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_scan_result.view.device_name
import kotlinx.android.synthetic.main.row_scan_result.view.mac_address
import kotlinx.android.synthetic.main.row_scan_result.view.signal_strength
import org.jetbrains.anko.layoutInflater


class ScanResultAdapter(
    private val items: List<ScanResult>,
    private val onClickListener: ((device: ScanResult) -> Unit)
) : RecyclerView.Adapter<ScanResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.context.layoutInflater.inflate(
            R.layout.row_scan_result,
            parent,
            false
        )
        return ViewHolder(view, onClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class ViewHolder(
        private val view: View,
        private val onClickListener: ((device: ScanResult) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        @SuppressLint("MissingPermission")
        fun bind(result: ScanResult) {

                view.device_name.text = result.device.name ?: "Unnamed"
                view.mac_address.text = result.device.address
                view.signal_strength.text = "${result.rssi} dBm"
                view.setOnClickListener { onClickListener.invoke(result) }
                return


        }

        private fun Context.hasPermission(permissionType: String): Boolean {
            return ContextCompat.checkSelfPermission(this, permissionType) ==
                PackageManager.PERMISSION_GRANTED
        }
    }
}
