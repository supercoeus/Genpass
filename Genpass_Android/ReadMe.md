## Android

### Install Android SDK

Mac OS: `brew install android-sdk`

Arch Linux:
`yaourt -S aur/android-sdk`

Other: Download Android SDK and install


### Install Ant (Android build tools)

Mac OS: `brew install ant`

Arch Linux: `yaourt -S aur/apache-ant-antro`

Other: Download Ant package and install


### Configure SDK

Change ${SDKDIR} to your sdk path in ./Genpass/local.properties


### Build

`cd Genpass && ant debug`


### Install
