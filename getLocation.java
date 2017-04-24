 @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkAndRequestLocationPermission();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        String rawlatitude = String.valueOf(latitude);
        String rawlongitude = String.valueOf(longitude);
        Log.v("BAMBI", rawlatitude + " "  + rawlongitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

       switch (requestCode) {
           case LOCATION_REQUEST_CODE:
               if (grantResults.length !=0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   startLocationServices();
               }
               else {
                   Log.v("BAMBI", "User has denied permission");
                   this.finish();
               }
       }
    }

    public void checkAndRequestLocationPermission() {
        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_REQUEST_CODE);
        }
        else {
            startLocationServices();
        }
    }

    public void startLocationServices(){
        try {
            LocationRequest req = new LocationRequest().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,req,this);
        }
        catch (SecurityException e) {
            e.printStackTrace();
            Log.v("BAMBI", "security exception, user denied permission");
        }

    }
