import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import Kiosk from 'react-native-kiosk';

const App: React.FC = () => {
  const handleFullscreen = () => {
    Kiosk.fullscreen();
  };

  const handleExitFullscreen = () => {
    Kiosk.exitFullscreen();
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Kiosk Mode</Text>

      <TouchableOpacity
        style={styles.containerButton}
        onPress={handleFullscreen}
      >
        <Text style={styles.text}>Enter</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={styles.containerButton}
        onPress={handleExitFullscreen}
      >
        <Text style={styles.text}>Exit</Text>
      </TouchableOpacity>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },

  title: {
    fontSize: 40,
    fontWeight: 'bold',
  },

  containerButton: {
    backgroundColor: '#035afc',
    width: '60%',
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 5,
    borderRadius: 10,
    marginBottom: 10,
  },

  text: {
    color: '#FFFFFF',
    fontSize: 20,
  },
});
