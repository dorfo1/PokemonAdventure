package br.com.fiap.ui.Main.Fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import com.google.android.gms.maps.model.LatLng
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.utils.PermissionUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

import java.util.*


class MapaFragment : Fragment(),OnMapReadyCallback,LocationListener{

    private lateinit var map : GoogleMap
    private lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        PermissionUtils.validarPermissoes(
            listOf(Manifest.permission.ACCESS_FINE_LOCATION),activity,1
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_mapa,container,false)
        var mapFragment = childFragmentManager.findFragmentById(R.id.frame_mapa) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        var update = CameraUpdateFactory.newLatLngZoom(LatLng(-23.5505, -46.6333), 10f)
        map.moveCamera(update)
        }


    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,this)
    }




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(result in grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                Toast.makeText(context,"Permissão negada",Toast.LENGTH_LONG).show()
            }else{
                requestLocationUpdates()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        requestLocationUpdates()
    }


    override fun onLocationChanged(location: Location?) {
        map.addMarker(MarkerOptions().position(LatLng(location?.latitude!!,location.longitude)))
        var update = CameraUpdateFactory.newLatLngZoom(LatLng(location?.latitude!!,location.longitude), 15f)
        map.moveCamera(update)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Log.i("STATUS", "Provider " + provider + " has now status: " + status)
    }

    override fun onProviderEnabled(provider: String?) {
        Log.i("ENABLE", "Provider " + provider)
    }

    override fun onProviderDisabled(provider: String?) {
        Log.i("DISABLE", "Provider " + provider)
    }



}
