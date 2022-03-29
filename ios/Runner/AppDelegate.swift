import UIKit
import Flutter
import flutter_hl_vnpay



@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate{



  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }

    override func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any]) -> Bool {
      if url.absoluteString.contains("paymentback") {
          FlutterHlVnpayPlugin.init().application(app, open:url, options:options)
      } else {
        return super.application(app, open: url, options: options)
      }
      return true
    }
}
