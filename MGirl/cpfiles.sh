currDir=$(cd "$(dirname "$0")"; pwd)

projectName="MGirl"
unityName=$projectName"Unity"
androidName=$projectName"Android"

cp -f $unityName/build/$projectName/libs/unity-classes.jar $androidName/app/libs/unity-classes.jar
cp -rf $unityName/build/$projectName/src/main/assets/bin/ $androidName/app/src/main/assets/bin/
cp -rf $unityName/build/$projectName/src/main/jniLibs/ $androidName/app/src/main/jniLibs/
#cp -f build/Roll\ a\ Ball/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java ../AndroidRollABall/app/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java
