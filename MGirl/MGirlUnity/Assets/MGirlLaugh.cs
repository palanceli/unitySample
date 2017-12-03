using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MGirlLaugh : MonoBehaviour {
	public Animation mAnimation;
	public Slider mLaughSlider;
	public Text mTextInfo;

	// Use this for initialization
	void Start () {
		mAnimation = GetComponent<Animation> ();
		mAnimation.wrapMode = WrapMode.Loop;
		StartCoroutine ("FireLaugh");
		mAnimation ["Take 001"].speed = 0;
	}

	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown (KeyCode.G)) {
			Debug.Log ("key down G");
			StartCoroutine ("AFireLaugh", "0.30");
		}
	}

	IEnumerator FireLaugh(){
		Debug.Log ("FireLaugh...");

		mAnimation.Play (mAnimation.clip.name);
		Debug.Log (mAnimation.clip.name);
		Debug.Log (mAnimation.clip.length);
		yield return new WaitForSeconds (mAnimation.clip.length);
	}

	void AFireLaugh(string paramString){
		string[] paramArray = paramString.Split ('#');
		Debug.Log (paramString);
		if (paramArray.Length != 1)
			return;
		float openValue = float.Parse (paramArray [0]);
		mAnimation ["Take 001"].normalizedTime =  openValue;
		mTextInfo.text = "Value="+paramString;
	}

	public void SliderChange(){
		Debug.Log ("slider value=" + mLaughSlider.value);
//		mAnimation ["Take 001"].normalizedTime =  mLaughSlider.normalizedValue;
//		mAnimation ["Take 001"].speed = 0;
		mAnimation["Take 001"].normalizedTime = 0.240000f;
	}
}
