currDir=$(cd "$(dirname "$0")"; pwd)
# curl： -C断点续传; -O使用URL中默认的文件名

cp -f build/Roll\ a\ Ball/libs/unity-classes.jar ../AndroidRollABall/app/libs/unity-classes.jar
cp -rf build/Roll\ a\ Ball/src/main/assets/bin/ ../AndroidRollABall/app/src/main/assets/bin/
cp -rf build/Roll\ a\ Ball/src/main/jniLibs/ ../AndroidRollABall/app/src/main/jniLibs/
cp -f build/Roll\ a\ Ball/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java ../AndroidRollABall/app/src/main/java/com/sample/palance/rollaball/UnityPlayerActivity.java
