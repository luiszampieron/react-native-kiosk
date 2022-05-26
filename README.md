# react-native-kiosk

A simple kiosk mode for Android in react native.

Does not work with `Expo` because of its native dependencies.

## Installation

```sh
npm install react-native-kiosk
```

## Android Installation

In `MainActivity.java` add the following code:

```java
//...
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.reactnativekiosk.Immersive;

public class MainActivity extends ReactActivity {
    //...

    @Override
    public void onBackPressed() {
        if( !Immersive.getActive() ) super.onBackPressed();
        else Immersive.fullscreen(getWindow());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Immersive.getActive()) Immersive.moveToFront(getTaskId(),(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Immersive.getActive()) Immersive.fullscreen(getWindow());
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if(Immersive.getActive()) {
        Immersive.moveToFront(getTaskId(), (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        Immersive.fullscreen(getWindow());
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(Immersive.getActive()) Immersive.fullscreen(getWindow());
        if (!hasFocus) {
        Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        sendBroadcast(closeDialog);
        }
    }
}
```

The next step is to put this here in the `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.REORDER_TASKS" />
```

And below in the `<intent-filter>`:
```xml
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.HOME" />
    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```

## Example in JS

```tsx
import Kiosk from "react-native-kiosk";

// ...
const App = () => {
  const handleFullscreen = () => {
    Kiosk.fullscreen();
  };

  const handleExitFullscreen = () => {
    Kiosk.exitFullscreen();
  };

  return (
    <View >
      <TouchableOpacity onPress={handleFullscreen} >
        <Text>Enter</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={handleExitFullscreen} >
        <Text>Exit</Text>
      </TouchableOpacity>

    </View>
  );
};

```

---

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
