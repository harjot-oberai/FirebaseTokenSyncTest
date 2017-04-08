# Firebase FCM Token synchronization
The project aims at synchronizing the FCM token used for push notifications with an app-server.

# Approach
The token is refreshed in one of the following cases : 
- The app deletes Instance ID
- The app is restored on a new device
- The user uninstalls/reinstall the app
- The user clears app data.

The `onTokenRefresh()` method is invoked whenever anyone of the above situation arises. So we can get the new token by calling `FirebaseInstanceId.getInstance().getToken()` and starting a synchronization routine to update the token in the app-server.

(Since there is no app-server, Firebase realtime database was used instead to store the token)

In case the user is not connected to internet and the token needs to be refreshed then the app will not generate a new token unless a connection is established. Once the connection is active the synchronization is done automatically.
