Copy getLocation.java to the activity, after onCreate

dependency:     <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
Manifest permission:  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
Activity implements: GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener

in onCreate:          googleApiClient = new GoogleApiClient.Builder(this)
                        .addApi(LocationServices.API)
                        .enableAutoManage(this, this)
                        .addConnectionCallbacks(this)
                        .build();
