using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MGirlLaugh : MonoBehaviour {
	public Slider mLaughSlider;
	public Text mLaughText;

	public Slider mHeadTwistSlider;
	public Text mHeadTwistText;

	public Slider mHeadLRSlider;
	public Text mHeadLRText;

	private float[] mEmotions;

	// Use this for initialization
	void Start () { 
		mEmotions = new float[32];
	}

	void updateEmotionsFromSlider(){
		mEmotions [0] = mLaughSlider.normalizedValue;
		mLaughText.text = "laugh=" + mEmotions [0];
		mEmotions [1] = mHeadTwistSlider.normalizedValue;
		mHeadTwistText.text = "headtwist=" + mEmotions [1];
		mEmotions [2] = mHeadLRSlider.normalizedValue;
		mHeadLRText.text = "headLR=" + mEmotions [2];
	}

	// Update is called once per frame
	void Update () { 
		updateEmotionsFromSlider ();
		Update3DEmotion ();
	}

	public void SliderChange(){
//		updateEmotionsFromSlider ();
//		Update3DEmotion ();
	}

	void Update3DEmotion(){
		Animation anim = GetComponent<Animation> ();

		anim["LaughAnim"].speed = 0;
		anim["LaughAnim"].normalizedTime = mEmotions[0];

		anim ["HeadTwistAnim"].speed = 0;
		anim ["HeadTwistAnim"].normalizedTime = mEmotions[1];
		anim ["HeadTwistAnim"].blendMode = AnimationBlendMode.Additive;
		anim ["HeadTwistAnim"].wrapMode = WrapMode.ClampForever;
		anim ["HeadTwistAnim"].layer = 10;
		anim ["HeadTwistAnim"].weight = 1.0f;


		anim ["HeadLRAnim"].speed = 0;
		anim ["HeadLRAnim"].normalizedTime = mEmotions [2];
		anim ["HeadLRAnim"].blendMode = AnimationBlendMode.Additive;
		anim ["HeadLRAnim"].wrapMode = WrapMode.ClampForever;
		anim ["HeadLRAnim"].layer = 10;
		anim ["HeadLRAnim"].weight = 1.0f;

		anim.CrossFade ("LaughAnim");
		anim.CrossFade ("HeadTwistAnim");
		anim.CrossFade ("HeadLRAnim");
	}
}
