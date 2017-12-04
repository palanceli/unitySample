using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Idle : MonoBehaviour {
	public Slider mHeadTwistSlider;
	public Slider mHeadLRSlider;
	public Slider mHeadUDSlider;
	private Dictionary<string, float> mEmotionDict;

	// Use this for initialization
	void Start () {
		mEmotionDict = new Dictionary<string, float> ();
		Animation anim = GetComponent<Animation> ();
		anim ["IdleAnim"].wrapMode = WrapMode.Loop;
		anim ["HeadLRAnim"].blendMode = AnimationBlendMode.Additive;
	
		anim ["HeadTwistAnim"].blendMode = AnimationBlendMode.Additive;
		anim ["HeadTwistAnim"].wrapMode = WrapMode.ClampForever;
		anim ["HeadTwistAnim"].layer = 10;
		anim ["HeadTwistAnim"].weight = 1.0f;
		anim ["HeadTwistAnim"].enabled = true;
		anim ["HeadTwistAnim"].speed = 0;

		anim ["HeadLRAnim"].blendMode = AnimationBlendMode.Additive;
		anim ["HeadLRAnim"].wrapMode = WrapMode.ClampForever;
		anim ["HeadLRAnim"].layer = 10;
		anim ["HeadLRAnim"].weight = 1.0f;
		anim ["HeadLRAnim"].enabled = true;
		anim ["HeadLRAnim"].speed = 0;

		anim ["HeadUDAnim"].blendMode = AnimationBlendMode.Additive;
		anim ["HeadUDAnim"].wrapMode = WrapMode.ClampForever;
		anim ["HeadUDAnim"].layer = 10;
		anim ["HeadUDAnim"].weight = 1.0f;
		anim ["HeadUDAnim"].enabled = true;
		anim ["HeadUDAnim"].speed = 0;

		SliderChange ();
	}

	public void SliderChange(){
		mEmotionDict["HeadTwist"] = mHeadTwistSlider.normalizedValue;
		mEmotionDict["HeadLR"] = mHeadLRSlider.normalizedValue;
		mEmotionDict["HeadUD"] = mHeadUDSlider.normalizedValue;
	}
	
	// Update is called once per frame
	void Update () {
		Animation anim = GetComponent<Animation> ();
		anim ["HeadTwistAnim"].normalizedTime = mEmotionDict["HeadTwist"];
		anim ["HeadLRAnim"].normalizedTime = mEmotionDict["HeadLR"];
		anim ["HeadUDAnim"].normalizedTime = mEmotionDict["HeadUD"];
	}

	void ControlByAndroid(string paramString){
		string[] paramList = paramString.Split ('#');
		for (int i = 0; i < paramList.Length; i++) {
			string[] kv = paramList [i].Split ('=');
			mEmotionDict [kv [0]] = float.Parse(kv [1]);
		}
	}
}
