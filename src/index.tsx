import { NativeModules } from 'react-native';

type KioskType = {
  fullscreen(): undefined;
  exitFullscreen(): undefined;
  moveToFront(): undefined;
};

const { Kiosk } = NativeModules;

export default Kiosk as KioskType;
