currDir=$(cd "$(dirname "$0")"; pwd)

projectName="MGirl"
unityName=$projectName"Unity"

androidLibsDir=$projectName"Android/app/libs"
if [ ! -d "$androidLibsDir" ]; then
  mkdir $androidLibsDir
fi
cp -f $unityName/build/$projectName/libs/unity-classes.jar $androidLibsDir/unity-classes.jar

androidAssetsDir=$projectName"Android/app/src/main/assets"
if [ ! -d "$androidAssetsDir" ]; then
  mkdir $androidAssetsDir
fi
cp -rf $unityName/build/$projectName/src/main/assets/bin/ $androidAssetsDir/bin/

androidJniLibsDir=$projectName"Android/app/src/main/jniLibs"
if [ ! -d "$androidJniLibsDir" ]; then
  mkdir $androidJniLibsDir
fi
cp -rf $unityName/build/$projectName/src/main/jniLibs/ $androidJniLibsDir/
#cp -f build/Roll\ a\ Ball/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java ../AndroidRollABall/app/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java
