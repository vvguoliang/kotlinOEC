package commerce.overseas.com.oec.kotlinpermissions


interface PermissionCallBack {

    fun permissionGranted() {
    }

    fun permissionDenied() {
    }
}