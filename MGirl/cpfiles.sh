currDir=$(cd "$(dirname "$0")"; pwd)

unityName="MGirlUnity"
androidName="MGirlAndroid"

cp -f $unityName/build/$unityName/libs/unity-classes.jar $androidName/libs/unity-classes.jar
cp -rf $unityName/build/$unityName/src/main/assets/bin/ $androidName/app/src/main/assets/bin/
cp -rf $unityName/build/$unityName/src/main/jniLibs/ $androidName/app/src/main/jniLibs/
#cp -f build/Roll\ a\ Ball/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java ../AndroidRollABall/app/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java
